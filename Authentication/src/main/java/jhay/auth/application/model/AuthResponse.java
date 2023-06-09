package jhay.auth.application.model;

import jhay.auth.common.security.jwt.JwtToken;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private String assignedTo;
    private boolean isExpired;
    private boolean isRevoked;

    public AuthResponse(JwtToken jwtToken){
        this.accessToken = jwtToken.getAccessToken();
        this.assignedTo = jwtToken.getUser().getEmail();
        this.isExpired = jwtToken.isExpired();
        this.isRevoked = jwtToken.isRevoked();
    }
}
