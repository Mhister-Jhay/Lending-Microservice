package org.jhay.application.controller;

import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.ApiResponse;
import org.jhay.application.dto.request.LoanRequest;
import org.jhay.application.dto.response.DataResponse;
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
    public ResponseEntity<ApiResponse<LoanResponse>> requestForLoan(@RequestBody LoanRequest request){
        ApiResponse<LoanResponse> response = new ApiResponse<>(loanService.requestLoan(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/view-loans/{loanId}")
    public ResponseEntity<ApiResponse<LoanResponse>> viewLoan(@PathVariable Long loanId){
        ApiResponse<LoanResponse> response = new ApiResponse<>(loanService.getLoan(loanId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/approve/{loanId}")
    public ResponseEntity<ApiResponse<DataResponse>> approveLoan(@PathVariable Long loanId){
        ApiResponse<DataResponse> response = new ApiResponse<>(loanService.approveLoan(loanId));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/verify-transaction")
    public ResponseEntity<ApiResponse<LoanResponse>> verifyPayment(@RequestParam("reference") String reference,
                                                                   @RequestParam("trxref") String transfer_reference){
        ApiResponse<LoanResponse> apiResponse = new ApiResponse<>(loanService.verifyPayment(reference));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
