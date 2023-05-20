package org.jhay.domain.repository;

import org.jhay.domain.model.User;
import org.jhay.dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
