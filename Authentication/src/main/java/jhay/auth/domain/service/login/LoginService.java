package jhay.auth.domain.service.login;

import jakarta.transaction.Transactional;
import jhay.auth.application.model.AuthResponse;
import jhay.auth.application.model.LoginRequest;

public interface LoginService {
    AuthResponse loginUser(LoginRequest loginRequest);

    String forgotPassword(String email);

    @Transactional
    String resetPassword(String email, String password);
}
