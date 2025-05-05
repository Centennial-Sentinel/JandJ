package it326.financialtracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import it326.financialtracker.Model.Goal;
import it326.financialtracker.Model.User;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    Goal findByid(Long id);
    void deleteByid(Long id);

    // Find all goals by User
    List<Goal> findByMyuser(User myuser);

    // Find goal by User and ID
    Optional<Goal> findByIdAndMyuser(Long id, User myuser);
}