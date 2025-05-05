package it326.financialtracker.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it326.financialtracker.Model.Goal;
import it326.financialtracker.Model.Transaction;
import it326.financialtracker.Model.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Transaction findByid(Long id);
    void deleteByid(Long id);
    List<Transaction> findByMyuser(User user);

    Optional<Goal> findByIdAndMyuser(Long id, User myuser);
    
    // Find transactions by Amount
    List<Transaction> findByMyuserAndAmount(User myuser, Double amount);
    
    // Find transactions by Description
    List<Transaction> findByMyuserAndDescriptionContainingIgnoreCase(User myuser, String description);
    
    // Find transactions by Tag
    List<Transaction> findByMyuserAndTagContainingIgnoreCase(User myuser, String tag);
    
    // Find transactions by Type
    List<Transaction> findByMyuserAndType(User myuser, String type);
    
    // Find transactions by Date
    List<Transaction> findByMyuserAndDate(User myuser, LocalDate date);
}
