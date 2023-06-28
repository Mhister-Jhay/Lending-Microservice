package org.jhay.application.controller;

import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.request.LoanRequest;
import org.jhay.application.dto.response.LoanResponse;
import org.jhay.domain.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/request")
    public ResponseEntity<LoanResponse> requestForLoan(@RequestBody LoanRequest request){
        return new ResponseEntity<>(loanService.requestLoan(request), HttpStatus.CREATED);
    }

    @GetMapping("/view-loans/{loanId}")
    public ResponseEntity<LoanResponse> viewLoan(@PathVariable Long loanId){
        return new ResponseEntity<>(loanService.getLoan(loanId), HttpStatus.OK);
    }
    @PutMapping("/approve/{loanId}")
    public ResponseEntity<LoanResponse> approveLoan(@PathVariable Long loanId){
        return new ResponseEntity<>(loanService.approveLoan(loanId),HttpStatus.OK);
    }
}
