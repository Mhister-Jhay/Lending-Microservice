package org.jhay.domain.service;

import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.request.LoanRequest;
import org.jhay.application.dto.response.LoanResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {
    LoanResponse requestLoan(LoanRequest request);

    LoanResponse approveLoan(Long loanId);

    LoanResponse getLoan(Long loanId);
}
