package it326.financialtracker.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Captures the information of a transaction
@Entity
@Table(name = "loandeadline")
public class LoanDeadline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "amount")
    private double amount;
    @Column(name = "ld_date")
    private LocalDate dueDate;
    @ManyToOne
    @JoinColumn(name = "myuser_id")
    private User myuser;

    public LoanDeadline (long loanId, double amount, LocalDate dueDate, User user) {
        this.id = loanId;
        loanSetter(amount, dueDate);
        this.myuser = user;
    }

    public void editLoanDeadline(double amount, LocalDate date) {
        loanSetter(amount, date);
    }

    private void loanSetter(double amount, LocalDate dueDate) {
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public long getLoanID() {
        return id;
    }

    public void setLoanID(long loanId) {
        this.id = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getUser() {
        return myuser;
    }

    public void setUser(User user) {
        this.myuser = user;
    }
}
