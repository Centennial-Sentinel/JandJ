package it326.financialtracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it326.financialtracker.Model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Transaction findByid(Long id);
    void deleteByid(Long id);
}
