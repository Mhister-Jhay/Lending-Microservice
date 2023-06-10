package jhay.auth.common.security.jwt;

import io.micrometer.observation.ObservationFilter;
import jhay.auth.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    JwtToken findByAccessToken(String accessToken);
    JwtToken findByUser(User user);

    boolean existsByUser(User user);

    List<JwtToken> findAllByUser(User user);

    Optional<JwtToken> findJwtTokenByAccessToken(String accessToken);

}
