package it326.financialtracker.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it326.financialtracker.Model.Goal;
import it326.financialtracker.Model.LoanDeadline;
import it326.financialtracker.Model.Transaction;
import it326.financialtracker.Model.User;
import it326.financialtracker.Model.UserProfile;
import it326.financialtracker.Repository.GoalRepository;
import it326.financialtracker.Repository.LoanDeadlineRepository;
import it326.financialtracker.Repository.TransactionRepository;
import it326.financialtracker.Repository.UserProfileRepository;
import it326.financialtracker.Repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class UserService{
    
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserProfileRepository profRepository;
    @Autowired
    private TransactionRepository transRepository;
    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private LoanDeadlineRepository LDRepository;
    
    public UserProfile loadByUsername(String username) {
        

        UserProfile j = profRepository.findByUsername(username);
        return j;
    }
    public void init() {
    	UserProfile j = new UserProfile();
        j.setEmail("email@email.com");
        j.setPassword("pa$$word");
        j.setUsername("frog");
        profRepository.save(j);
        User something = new User();
        something.setProfile(j);
        Transaction trans1 = new Transaction();
        trans1.setAmount(4.0);
        trans1.setDate(LocalDate.now());
        trans1.setDescription("trans1");
        trans1.setTag("not important 1");
        //trans1.setUserId(something);
        transRepository.save(trans1);
        
        Transaction trans2 = new Transaction();
        trans2.setAmount(6.0);
        trans2.setDate(LocalDate.now());
        trans2.setDescription("trans2");
        trans2.setTag("not important 2");
        //trans2.setUserId(something);
        transRepository.save(trans2);
        
        Transaction trans3 = new Transaction();
        trans3.setAmount(8.0);
        trans3.setDate(LocalDate.now());
        trans3.setDescription("trans3");
        trans3.setTag("not important 3");
        //trans3.setUserId(something);
        transRepository.save(trans3);
        
        List<Transaction> txlist = new ArrayList<>();
        txlist.add(trans1);
        txlist.add(trans2);
        txlist.add(trans3);
        something.setTransactions(txlist);

        Goal goal1 = new Goal();
        goal1.setCurrentAmount(250);
        goal1.setTargetAmount(500);
        goal1.setDescription("goal1");
        //goal1.setUserId(something);
        goalRepository.save(goal1);

        Goal goal2 = new Goal();
        goal2.setCurrentAmount(500);
        goal2.setTargetAmount(750);
        goal2.setDescription("goal2");
        //goal2.setUserId(something);
        goalRepository.save(goal1);

        Goal goal3 = new Goal();
        goal3.setCurrentAmount(750);
        goal3.setTargetAmount(1000);
        goal3.setDescription("goal3");
        //goal3.setUserId(something);
        goalRepository.save(goal1);

        List<Goal> gllist = new ArrayList<>();
        gllist.add(goal1);
        gllist.add(goal2);
        gllist.add(goal3);
        something.setGoals(gllist);

        LoanDeadline ld1 = new LoanDeadline();
        ld1.setAmount(250);
        ld1.setDueDate(LocalDate.now());
        //ld1.setUserId(something);
        LDRepository.save(ld1);

        LoanDeadline ld2 = new LoanDeadline();
        ld1.setAmount(500);
        ld1.setDueDate(LocalDate.now());
        //ld1.setUserId(something);
        LDRepository.save(ld2);

        LoanDeadline ld3 = new LoanDeadline();
        ld1.setAmount(750);
        ld1.setDueDate(LocalDate.now());
        //ld1.setUserId(something);
        LDRepository.save(ld3);

        List<LoanDeadline> ldlist = new ArrayList<>();
        ldlist.add(ld1);
        ldlist.add(ld2);
        ldlist.add(ld3);
        something.setGoals(gllist);
        
        repository.save(something);
    }
    public void del() {
    	repository.deleteById((long) 1);
    	profRepository.deleteById((long) 1);
    	transRepository.deleteById((long) 1);
    }

    
    
    
    
}
