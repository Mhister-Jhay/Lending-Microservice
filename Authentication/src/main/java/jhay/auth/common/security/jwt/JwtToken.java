package jhay.auth.common.security.jwt;

import jakarta.persistence.*;
import jhay.auth.domain.model.User;
import jhay.auth.common.utils.DateUtils;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jwt_token")
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String accessToken;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private boolean isExpired;
    @Column(nullable = false)
    private boolean isRevoked;
    public JwtToken(String accessToken){
        this.accessToken = accessToken;
        this.isExpired = false;
        this.isRevoked = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JwtToken jwtToken = (JwtToken) o;
        return getId() != null && Objects.equals(getId(), jwtToken.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
