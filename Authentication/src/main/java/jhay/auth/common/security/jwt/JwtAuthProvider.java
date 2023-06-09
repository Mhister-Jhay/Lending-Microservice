package jhay.auth.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import jhay.auth.common.utils.DateUtils;
import jhay.auth.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtAuthProvider {
    private final JwtTokenRepository jwtTokenRepository;
    private String generateSecret(){
        return DatatypeConverter.printBase64Binary(new byte[512/8]);
    }
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.getExpirationDate())
                .signWith(getSignatureKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    public void revokeAllUserTokens(User user){
        List<JwtToken> jwtTokens = jwtTokenRepository.findAllByUser(user);
        jwtTokens.forEach(jwtToken -> {
            jwtToken.setExpired(true);
            jwtToken.setRevoked(true);
        });
        jwtTokenRepository.saveAll(jwtTokens);
    }
    private Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(generateSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String jwtToken){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
    private <T> T extractSingleClaim(String jwtToken, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }
    private Date extractExpiration(String jwtToken){
        return extractSingleClaim(jwtToken, Claims::getExpiration);
    }
    public boolean isTokenExpired(String jwtToken){
        return extractExpiration(jwtToken).before(new Date());
    }
    public String extractUsername(String jwtToken){
        return extractSingleClaim(jwtToken, Claims::getSubject);
    }
    public boolean isTokenValid(String jwtToken, UserDetails userDetails){
        final String username = extractUsername(jwtToken);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

}
