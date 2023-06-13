package org.jhay.application.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.jhay.application.model.request.AddressRequest;
import org.jhay.application.model.request.EmploymentRequest;
import org.jhay.application.model.response.AddressResponse;
import org.jhay.application.model.response.ApiResponse;
import org.jhay.application.model.response.EmploymentResponse;
import org.jhay.domain.api.model.request.VerifyAccountRequest;
import org.jhay.domain.api.model.response.SaveAccountResponse;
import org.jhay.domain.api.services.AccountApiService;
import org.jhay.domain.service.address.AddressService;
import org.jhay.domain.service.employment.EmploymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final AccountApiService accountApiService;
    private final AddressService addressService;
    private final EmploymentService employmentService;

    @PostMapping("/{userId}/save-account")
    public ResponseEntity<ApiResponse<SaveAccountResponse>> saveUserAccount(@PathVariable Long userId,
                                                                            @RequestBody VerifyAccountRequest request)
            throws ParseException {
        ApiResponse<SaveAccountResponse> apiResponse = new ApiResponse<>(accountApiService.saveUserAccount(userId, request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/save-address")
    public ResponseEntity<ApiResponse<AddressResponse>> saveUserAddress(@PathVariable Long userId,
                                                                        @RequestBody AddressRequest request) {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.saveUserAddress(userId, request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/get-address")
    public ResponseEntity<ApiResponse<AddressResponse>> getUserAddress(@PathVariable Long userId) {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.getUserAddress(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{userId}/edit-address/{addressId}")
    public ResponseEntity<ApiResponse<String>> editUserAddress(@PathVariable Long userId, @PathVariable Long addressId,
                                                               @RequestBody AddressRequest request) {
        ApiResponse<String> apiResponse = new ApiResponse<>(addressService.editUserAddress(userId,addressId, request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/{userId}/save-employment")
    public ResponseEntity<ApiResponse<EmploymentResponse>> saveUserEmployment(@PathVariable Long userId,
                                                                              @RequestBody EmploymentRequest request){
        ApiResponse<EmploymentResponse> apiResponse = new ApiResponse<>(employmentService.saveEmployment(userId,request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/{userId}/edit-employment/{employmentId}")
    public ResponseEntity<ApiResponse<String>> editUserEmployment(@PathVariable Long userId, @PathVariable Long employmentId,
                                                                  @RequestBody EmploymentRequest request){
        ApiResponse<String> apiResponse = new ApiResponse<>(employmentService.updateEmployment(userId,employmentId,request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
