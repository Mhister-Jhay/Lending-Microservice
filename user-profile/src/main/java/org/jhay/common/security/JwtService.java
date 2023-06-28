package org.jhay.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import org.jhay.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserRepository userRepository;

    private String generateSecret(){
        return DatatypeConverter.printBase64Binary(new byte[512/8]);
    }
    private Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(generateSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private <T> T extractSingleClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Date extractExpiration(String token){
        return extractSingleClaim(token,Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).after(new Date());
    }
    public String extractUsername(String token){
        return extractSingleClaim(token, Claims::getSubject);
    }
    public boolean isTokenValid(String token){
        String username = extractUsername(token);
        if(!userRepository.existsByEmail(username)){
            return false;
        }
        return isTokenExpired(token);
    }
}
