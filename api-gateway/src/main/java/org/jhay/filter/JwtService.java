package org.jhay.filter;

import lombok.RequiredArgsConstructor;
import org.jhay.domain.repository.AuthRepository;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class JwtService {
    private final AuthRepository authRepository;
    public void isTokenValid(String token){
       authRepository.findByAccessToken(token)
               .orElseThrow(()-> new RuntimeException("Invalid Token"));
    }

}