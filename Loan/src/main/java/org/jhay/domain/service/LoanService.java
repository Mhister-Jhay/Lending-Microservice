package org.jhay.domain.service;

import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.request.LoanRequest;
import org.jhay.application.dto.response.DataResponse;
import org.jhay.application.dto.response.LoanResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {
    LoanResponse requestLoan(LoanRequest request);

    DataResponse approveLoan(Long loanId);

    LoanResponse verifyPayment(String reference);

    LoanResponse getLoan(Long loanId);
}
