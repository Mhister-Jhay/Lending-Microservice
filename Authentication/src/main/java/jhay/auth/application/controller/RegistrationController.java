package jhay.auth.application.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
public class RegistrationController {
    private final RegistrationServiceImpl registrationService;
    private final LoginServiceImpl loginService;
    private final TokenValidationServiceImpl tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody
                                                                RegistrationRequest registerRequest,
                                                        HttpServletRequest request){
        return new ResponseEntity<>(registrationService.registerUser(registerRequest,request), HttpStatus.CREATED);
    }
    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyUserEmail(@RequestParam("token") String token,
                                                  HttpServletRequest request){
        return new ResponseEntity<>(tokenService.validateToken(token,request),HttpStatus.OK);
    }
    @GetMapping("/request-new-verification-token")
    public ResponseEntity<String> requestNewToken(@RequestParam("email") String email,
                                                  HttpServletRequest request){
        return new ResponseEntity<>(tokenService.requestNewVerificationToken(email,request), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(loginService.loginUser(loginRequest), HttpStatus.OK);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> userForgotPassword(@RequestParam("email") String email,
                                                     HttpServletRequest request){
        return new ResponseEntity<>(loginService.forgotPassword(email,request), HttpStatus.OK);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("email") String email,
                                                @RequestParam("password") String password){
        return new ResponseEntity<>(loginService.resetPassword(email,password),HttpStatus.OK);
    }
    @GetMapping("/verify-reset-password")
    public ResponseEntity<String> verifyPasswordToken(@RequestParam("token") String token,
                                                      HttpServletRequest request) {
        return new ResponseEntity<>(tokenService.validatePasswordToken(token, request), HttpStatus.OK);
    }
}
