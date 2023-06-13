package org.jhay.domain.repository;

import org.jhay.domain.model.Employment;
import org.jhay.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    boolean existsByUser(User user);

    Optional<Employment> findByUser(User user);

}
