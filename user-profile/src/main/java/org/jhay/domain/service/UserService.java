package org.jhay.domain.service;

import org.jhay.application.model.request.UserRequest;
import org.jhay.application.model.response.UserSavedResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserSavedResponse saveUser(UserRequest userRequest);
}
