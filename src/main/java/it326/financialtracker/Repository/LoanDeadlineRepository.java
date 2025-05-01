package it326.financialtracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it326.financialtracker.Model.LoanDeadline;

@Repository
public interface LoanDeadlineRepository extends JpaRepository<LoanDeadline, Long>{
    LoanDeadline findByid(Long id);
    void deleteByid(Long id);
}
