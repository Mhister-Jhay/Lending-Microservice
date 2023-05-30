package org.jhay.domain.repository;

import org.jhay.domain.model.Address;
import org.jhay.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    boolean existsByUser(User user);

    Optional<Address> findByUser(User user);

}
