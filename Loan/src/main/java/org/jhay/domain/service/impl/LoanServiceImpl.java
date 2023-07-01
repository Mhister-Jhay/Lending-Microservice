package org.jhay.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.request.LoanRequest;
import org.jhay.application.dto.request.PaymentRequest;
import org.jhay.application.dto.response.DataResponse;
import org.jhay.application.dto.response.LoanResponse;
import org.jhay.application.dto.response.PaymentResponse;
import org.jhay.application.dto.response.VerificationResponse;
import org.jhay.common.exception.*;
import org.jhay.common.utils.SecurityUtils;
import org.jhay.domain.api.ApiConnection;
import org.jhay.domain.model.Loan;
import org.jhay.domain.model.Payment;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.LoanRepository;
import org.jhay.domain.repository.PaymentRepository;
import org.jhay.domain.repository.UserRepository;
import org.jhay.domain.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final ApiConnection apiConnection;

    @Override
    public LoanResponse requestLoan(LoanRequest request){
        String email = SecurityUtils.extractUserEmail();

        Double flatRate = 0.15;
        Double durationRate = (request.getDurationInMonths()/12)/100.0;
        Double interestRate = flatRate + durationRate;
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Does not Exist"));
        if(!user.getIsAccountSaved() && !user.getIsAddressSaved() && !user.getIsEmploymentSaved()){
            throw new ProfileNotCompleteException("Please Update Your Profile before Requesting for loan");
        }
        Loan loan = loanRepository.save(Loan.builder()
                .amountBorrowed(request.getAmountBorrowed())
                .durationInMonths(request.getDurationInMonths())
                .purpose(request.getPurpose())
                .amountToRepay(request.getAmountBorrowed()+(request.getAmountBorrowed()*interestRate))
                .isApproved(false)
                .amountLeftToPay(request.getAmountBorrowed()*interestRate)
                .isPaymentInitiated(false)
                .isPaymentCompleted(false)
                .paymentUpdated(LocalDateTime.now())
                .user(user)
                .build());
        return modelMapper.map(loan, LoanResponse.class);
    }

    @Override
    public DataResponse approveLoan(Long loanId){
        String email = SecurityUtils.extractUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Does not Exist"));
        if(!user.getIsAccountSaved() && !user.getIsAddressSaved() && !user.getIsEmploymentSaved()){
            throw new ProfileNotCompleteException("Please Update Your Profile before Requesting for loan");
        }
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan does not exist"));
        User borrower = loan.getUser();
        if(loan.getIsApproved()){
            throw new LoanAlreadyApprovedException("Loan has been approved already");
        }
        if(borrower.equals(user)){
            throw new UnauthorizedException("Cannot Approve Loan Requested By Same User");
        }
        String url = "https://api.paystack.co/transaction/initialize";
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .email(user.getEmail())
                .amount(loan.getAmountBorrowed())
                .subaccount(borrower.getSubAccountId())
                .callback_url("http://localhost:8083/verify-payment")
                .build();
        PaymentResponse response = apiConnection.connectAndPost(paymentRequest,url,PaymentResponse.class);
        DataResponse data = response.getData();
        paymentRepository.save(Payment.builder()
                        .amount(loan.getAmountBorrowed())
                        .reference(data.getReference())
                        .paidBy(user)
                        .loan(loan)
                        .paidTo(borrower)
                .build());

        return response.getData();
    }
    @Override
    public LoanResponse verifyPayment(String reference){
        Payment payment = paymentRepository.findByReference(reference)
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));
        String url = "https://api.paystack.co/transaction/verify/"+payment.getReference();
        VerificationResponse response = apiConnection.connectAndGet(url, VerificationResponse.class);
        payment.setChannel(response.getChannel());
        payment.setPaid_at(response.getPaid_at());
        payment.setCreated_at(response.getCreated_at());
        Loan loan =  paymentRepository.save(payment).getLoan();
        loan.setIsApproved(true);
        loan.setApprovedBy(payment.getPaidBy());
        return modelMapper.map(loanRepository.save(loan), LoanResponse.class);
    }
    @Override
    public LoanResponse getLoan(Long loanId){
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan does not exist"));
        return modelMapper.map(loan, LoanResponse.class);
    }
}
