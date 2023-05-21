package org.jhay.domain.api.repository;

import org.jhay.domain.api.model.Banks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Banks,Long> {
}
