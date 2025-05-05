package it326.financialtracker.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it326.financialtracker.Model.LoanDeadline;
import it326.financialtracker.Model.User;

@Repository
public interface LoanDeadlineRepository extends JpaRepository<LoanDeadline, Long>{
    LoanDeadline findByid(Long id);
    void deleteByid(Long id);

    // Find all LoanDeadlines by User
    List<LoanDeadline> findByMyuser(User myuser);

    // Find goal by User and ID
    Optional<LoanDeadline> findByIdAndMyuser(Long id, User myuser);
}
