package jhay.auth.domain.service.registration;

import jhay.auth.application.model.RegistrationRequest;

public interface RegistrationService {
    String registerUser(RegistrationRequest registerRequest);
}
