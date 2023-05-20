package org.jhay.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.jhay.application.model.request.UserRequest;
import org.jhay.application.model.response.UserSavedResponse;
import org.jhay.domain.enums.Gender;
import org.jhay.domain.exceptions.UserAlreadyExistException;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.UserRepository;
import org.jhay.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserSavedResponse saveUser(UserRequest userRequest){
        verifyUserExistence(userRequest.getEmail());
        verifyUserExistence(userRequest.getPhoneNumber());
        User user = userRepository.save(User.builder()
                        .firstName(userRequest.getFirstName())
                        .lastName(userRequest.getLastName())
                        .phoneNumber(userRequest.getPhoneNumber())
                        .email(userRequest.getEmail())
                        .build());
        return UserSavedResponse.builder()
                .id(user.getId())
                .fullName(user.getFirstName()+" "+user.getLastName())
                .email(user.getEmail())
                .build();
    }

    private void verifyUserExistence(String details){
        if(userRepository.existsByEmail(details)){
            throw new UserAlreadyExistException(details);
        }
    }
}
