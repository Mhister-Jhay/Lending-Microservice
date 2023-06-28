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

    @PostMapping("/save-account")
    public ResponseEntity<ApiResponse<SaveAccountResponse>> saveUserAccount(@RequestBody VerifyAccountRequest request)
            throws ParseException {
        ApiResponse<SaveAccountResponse> apiResponse = new ApiResponse<>(accountApiService.saveUserAccount(request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/save-address")
    public ResponseEntity<ApiResponse<AddressResponse>> saveUserAddress(@RequestBody AddressRequest request) {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.saveUserAddress(request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get-address")
    public ResponseEntity<ApiResponse<AddressResponse>> getUserAddress() {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.getUserAddress());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/edit-address/{addressId}")
    public ResponseEntity<ApiResponse<AddressResponse>> editUserAddress(@PathVariable Long addressId,
                                                               @RequestBody AddressRequest request) {
        ApiResponse<AddressResponse> apiResponse = new ApiResponse<>(addressService.editUserAddress(addressId, request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/save-employment")
    public ResponseEntity<ApiResponse<EmploymentResponse>> saveUserEmployment(@RequestBody EmploymentRequest request){
        ApiResponse<EmploymentResponse> apiResponse = new ApiResponse<>(employmentService.saveEmployment(request));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/edit-employment/{employmentId}")
    public ResponseEntity<ApiResponse<EmploymentResponse>> editUserEmployment(@PathVariable Long employmentId,
                                                                  @RequestBody EmploymentRequest request){
        ApiResponse<EmploymentResponse> apiResponse = new ApiResponse<>(employmentService.updateEmployment(employmentId,request));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/get-employment")
    public ResponseEntity<ApiResponse<EmploymentResponse>> getUserEmployment(){
        ApiResponse<EmploymentResponse> apiResponse = new ApiResponse<>(employmentService.getUserEmployment());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
