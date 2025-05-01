package it326.financialtracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it326.financialtracker.Model.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long>{
    Goal findByid(Long id);
    void deleteByid(Long id);
}
