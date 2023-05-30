package org.jhay.application.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.jhay.application.model.request.AddressRequest;
import org.jhay.application.model.response.AddressResponse;
import org.jhay.application.model.response.ApiResponse;
import org.jhay.domain.api.model.request.VerifyAccountRequest;
import org.jhay.domain.api.model.response.SaveAccountResponse;
import org.jhay.domain.api.services.AccountApiService;
import org.jhay.domain.service.address.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final AccountApiService accountApiService;
    private final AddressService addressService;

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

    @PutMapping("/{userId}/edit-address")
    public ResponseEntity<ApiResponse<AddressResponse>> editUserAddress(@PathVariable Long userId,
                                                                        @RequestBody AddressRequest request) {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.editUserAddress(userId, request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
