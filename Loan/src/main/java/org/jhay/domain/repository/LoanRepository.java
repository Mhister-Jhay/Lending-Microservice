package org.jhay.domain.repository;

import org.jhay.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {

}
