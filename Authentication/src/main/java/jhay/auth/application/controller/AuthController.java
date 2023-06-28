package jhay.auth.application.controller;

import jakarta.validation.Valid;
import jhay.auth.application.model.ApiResponse;
import jhay.auth.application.model.LoginRequest;
import jhay.auth.application.model.RegistrationRequest;
import jhay.auth.application.model.AuthResponse;
import jhay.auth.domain.service.login.LoginServiceImpl;
import jhay.auth.domain.service.registration.RegistrationServiceImpl;
import jhay.auth.domain.service.token.TokenValidationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RegistrationServiceImpl registrationService;
    private final LoginServiceImpl loginService;
    private final TokenValidationServiceImpl tokenService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerNewUser(@Valid @RequestBody
                                                                RegistrationRequest registerRequest){
        ApiResponse<String> apiResponse = new ApiResponse<>(registrationService.registerUser(registerRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/verify-email")
    public ResponseEntity<ApiResponse<String>> verifyUserEmail(@RequestParam("token") String token){
        ApiResponse<String> apiResponse = new ApiResponse<>(tokenService.validateToken(token));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/request-new-verification-token")
    public ResponseEntity<ApiResponse<String>> requestNewToken(@RequestParam("email") String email){
        ApiResponse<String> apiResponse = new ApiResponse<>(tokenService.requestNewVerificationToken(email));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> loginUser(@RequestBody LoginRequest loginRequest){
        ApiResponse<AuthResponse> apiResponse = new ApiResponse<>(loginService.loginUser(loginRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> userForgotPassword(@RequestParam("email") String email){
        ApiResponse<String> apiResponse = new ApiResponse<>(loginService.forgotPassword(email));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestParam("email") String email,
                                                @RequestParam("password") String password){
        ApiResponse<String> apiResponse = new ApiResponse<>(loginService.resetPassword(email,password));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/verify-reset-password")
    public ResponseEntity<ApiResponse<String>> verifyPasswordToken(@RequestParam("token") String token) {
        ApiResponse<String> apiResponse = new ApiResponse<>(tokenService.validatePasswordToken(token));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
