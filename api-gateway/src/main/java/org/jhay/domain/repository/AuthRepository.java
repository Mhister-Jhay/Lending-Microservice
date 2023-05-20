package org.jhay.domain.repository;

import org.jhay.domain.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByAccessToken(String token);

    boolean existsByAssignedTo(String assignedTo);

    Auth findByAssignedTo(String assignedTo);

}
