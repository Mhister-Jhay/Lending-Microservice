package jhay.auth.common.security.jwt;

import jhay.auth.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    JwtToken findByAccessToken(String accessToken);
    JwtToken findByUser(User user);

    boolean existsByUser(User user);

    List<JwtToken> findAllByUser(User user);

}
