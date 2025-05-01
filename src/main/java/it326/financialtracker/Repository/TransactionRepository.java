package it326.financialtracker.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it326.financialtracker.Model.Transaction;
import it326.financialtracker.Model.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Transaction findByid(Long id);
    void deleteByid(Long id);

    // Find all Transactions by User
    List<Transaction> findByMyuser(User myuser);

    // Find Transaction by User and ID
    Optional<Transaction> findByIdAndMyuser(Long id, User myuser);
}
