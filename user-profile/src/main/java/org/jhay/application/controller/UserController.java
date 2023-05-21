package org.jhay.application.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.jhay.application.model.response.ApiResponse;
import org.jhay.domain.api.model.request.SaveAccountRequest;
import org.jhay.domain.api.model.response.SaveAccountResponse;
import org.jhay.domain.api.services.AccountApiServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final AccountApiServiceImpl accountApiService;

    @PostMapping("/save-account")
    public ResponseEntity<ApiResponse<SaveAccountResponse>> saveUserAccount(@RequestBody SaveAccountRequest request) throws ParseException {
        ApiResponse<SaveAccountResponse> apiResponse = new ApiResponse<>(accountApiService.saveUserAccount(request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
