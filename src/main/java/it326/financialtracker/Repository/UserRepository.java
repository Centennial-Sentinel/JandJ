package it326.financialtracker.Repository;

//import java.util.Optional;
//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it326.financialtracker.Model.User;
//import it326.financialtracker.Model.UserProfile;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{



    
}
