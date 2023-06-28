package org.jhay.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.request.LoanRequest;
import org.jhay.application.dto.response.LoanResponse;
import org.jhay.common.exception.*;
import org.jhay.common.utils.SecurityUtils;
import org.jhay.domain.model.Loan;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.LoanRepository;
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
    private final ModelMapper modelMapper;

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
                .amountToRepay(request.getAmountBorrowed()*interestRate)
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
    public LoanResponse approveLoan(Long loanId){
        String email = SecurityUtils.extractUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Does not Exist"));
        if(!user.getIsAccountSaved() && !user.getIsAddressSaved() && !user.getIsEmploymentSaved()){
            throw new ProfileNotCompleteException("Please Update Your Profile before Requesting for loan");
        }
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan does not exist"));
        if(loan.getIsApproved()){
            throw new LoanAlreadyApprovedException("Loan has been approved already");
        }
        if(loan.getUser().equals(user)){
            throw new UnauthorizedException("Cannot Approve Loan Requested By Same User");
        }
        loan.setIsApproved(true);
        loan.setApprovedBy(user);
        return modelMapper.map(loanRepository.save(loan), LoanResponse.class);
    }
    @Override
    public LoanResponse getLoan(Long loanId){
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan does not exist"));
        return modelMapper.map(loan, LoanResponse.class);
    }
}
