package org.jhay.event;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.jhay.domain.model.Auth;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.AuthRepository;
import org.jhay.dto.AuthResponse;
import org.jhay.dto.UserResponse;
import org.jhay.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthEventHandler{
    private static final Gson GSON = new Gson();
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    public void handleUserRegistration(String userDetails){
        UserResponse userResponse = GSON.fromJson(userDetails, UserResponse.class);
        if(userRepository.existsByEmail(userResponse.getEmail())){
            User user = userRepository.findByEmail(userResponse.getEmail());
            System.out.println("User Already Exists");
        }else{
            userRepository.save(User.builder()
                    .email(userResponse.getEmail())
                    .lastName(userResponse.getLastName())
                    .firstName(userResponse.getFirstName())
                    .phoneNumber(userResponse.getPhoneNumber())
                    .gender(userResponse.getGender())
                    .build());
        }
        System.out.println("User Registered Successfully");

    }
    public void handleUserLogin(String loginDetails){
        AuthResponse authResponse = GSON.fromJson(loginDetails,AuthResponse.class);
        if(authRepository.existsByAssignedTo(authResponse.getAssignedTo())){
            Auth auth = authRepository.findByAssignedTo(authResponse.getAssignedTo());
            auth.setAccessToken(auth.getAccessToken());
            System.out.println(auth.getAccessToken()+"  Access Token");
            auth.setValidTill(authResponse.getValidTill());
            authRepository.save(auth);
        }else{
            authRepository.save(Auth.builder()
                    .accessToken(authResponse.getAccessToken())
                    .assignedTo(authResponse.getAssignedTo())
                    .validTill(authResponse.getValidTill())
                    .build());
        }
        System.out.println("Token saved successfully");
    }
}
