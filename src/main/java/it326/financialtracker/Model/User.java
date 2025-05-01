package it326.financialtracker.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "myuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "myuser_id")
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private UserProfile profile;
    @OneToMany(mappedBy = "myuser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();
    @OneToMany(mappedBy = "myuser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();
    @OneToMany(mappedBy = "myuser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanDeadline> loanDeadlines = new ArrayList<>();

    // public User(UserProfile profile, List<Transaction> transactions) {
    //     this.profile = profile;
    //     this.transactions = transactions;
    // }


    public UserProfile getProfile() {
        return profile;
    }
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    // public List<Goal> getGoals() {
    //     return goals;
    // }
    // public void setGoals(List<Goal> goals) {
    //     this.goals = goals;
    // }
    
    
    
}
