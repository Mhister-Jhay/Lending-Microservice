package org.jhay.filter;

import lombok.RequiredArgsConstructor;
import org.jhay.domain.repository.AuthRepository;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class JwtService {
    private final AuthRepository authRepository;
    public void isTokenValid(String token){
        var isTokenValid = authRepository.findByAccessToken(token)
                .map(t-> !t.isExpired() && !t.isRevoked())
                .orElse(false);
       if(!isTokenValid){
           throw new RuntimeException("Invalid credentials");
       }
    }

}