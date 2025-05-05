package it326.financialtracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it326.financialtracker.Model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	UserProfile findByUsername(String username);
    UserProfile findByEmail(String email);
    UserProfile findById(long id);

}
