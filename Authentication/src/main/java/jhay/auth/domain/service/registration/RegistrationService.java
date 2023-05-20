package jhay.auth.domain.service.registration;

import jakarta.servlet.http.HttpServletRequest;
import jhay.auth.application.model.AuthResponse;
import jhay.auth.application.model.RegistrationRequest;

public interface RegistrationService {
    String registerUser(RegistrationRequest registerRequest, HttpServletRequest request);
}
