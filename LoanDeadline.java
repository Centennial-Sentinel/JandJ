import java.util.*;
import java.time.LocalDate;

//Captures the information of a transaction
public class LoanDeadline {

    private int loanID;
    private double amount;
    private LocalDate dueDate;
    private User user;

    public LoanDeadline (int loanID, double amount, LocalDate dueDate, User user) {
        this.loanID = loanID;
        loanSetter(amount, dueDate);
        this.user = user;
    }

    public void editLoanDeadline(TransactionType type, double amount, LocalDate date, List<String> items) {
        loanSetter(type, amount, date, items);
    }

    private void loanSetter(double amount, LocalDate dueDate) {
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
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
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
