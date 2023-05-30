package jhay.auth.domain.service.token;

import org.springframework.transaction.annotation.Transactional;

public interface TokenValidationService {
    @Transactional
    String validateToken(String token);

    @Transactional
    String requestNewVerificationToken(String email);

    @Transactional
    String requestForgotPasswordToken(String email);

    String validatePasswordToken(String token);

}
