import java.util.*;
import java.time.LocalDate;

public class User {

    private UserProfile userProfile;
    private List<Transaction> transactions;
    private List<Goal> goals;
    private List<LoanDeadline>;
    private Portfolio portfolio;
    private int transactionCounter;
    private int goalCounter;
    private int loanDeadlineCounter;

    private User (String username, String password, String email) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        UserProfile userProfile = new UserProfile(username, hashed, email);
        transactions = new ArrayList<>();
        goals = new ArrayList<>();
        loanDeadlines = new ArrayList<>();
        portfolio = new Portfolio();
        transactionCounter = 0;
        goalCounter = 0;
        loanDeadlineCounter = 0
    }

    //maybe move this function
    public void generateInfographics() {
        //TODO: get all of this working when we have our graph generator
    }

    public void viewPortfolio() {
        //TODO: finish when portfolio is done
    }

    public void addTransaction(Transaction newTransaction) {
        transactions.add(newTransaction);
        transactionCounter++;
    }

    public void addGoal(Goal newGoal) {
        goals.add(newGoal);
        goalCounter++;
    }

    public void addLoanDeadline(LoanDeadline newLoanDeadline) {
        loanDeadlines.add(newLoanDeadline);
        loanDeadlineCounter++;
    }

    public Transaction getTransaction(int ID) {
        for (Transaction obj : transactions) {
            if (obj.getID() == ID) {
                return obj;
            }
        }
        throw new NoSuchElementException("Transaction with ID " + ID + " not found.");
    }

    public Goal getGoal(int ID) {
        for (Goal obj : goals) {
            if (obj.getID() == ID) {
                return obj;
            }
        }
        throw new NoSuchElementException("Goal with ID " + ID + " not found.");
    }

    public LoanDeadline getLoanDeadline(int ID) {
        for (LoanDeadline obj : loanDeadlines) {
            if (obj.getID() == ID) {
                return obj;
            }
        }
        throw new NoSuchElementException("Loan Deadline with ID " + ID + " not found.");
    }

    public void editTransaction(Transaction transaction, TransactionType type, double amount, LocalDate date, List<String> items) {
        transaction.editTransaction(type, amount, date, items);
    }

    public void editGoal(Goal goal, String description, double targetAmount, double currentAmount) {
        goal.editGoal(description, targetAmount, currentAmount);
    }

    public void editLoanDeadlinel(LoanDeadline loanDeadline, double amount, LocalDate dueDate) {
        loanDeadline.editLoanDeadline(amount, dueDate);
    }

    public void deleteTransaction(int ID) {
        for (Transaction obj : transactions) {
            if (obj.getID() == ID) {
                transactions.remove(ID);
                return;
            }
        }
        throw new NoSuchElementException("Transaction with ID " + ID + " not found.");
    }

    public void deleteGoal(int ID) {
        for (Goal obj : goals) {
            if (obj.getID() == ID) {
                goals.remove(ID)
                return;
            }
        }
        throw new NoSuchElementException("Goal with ID " + ID + " not found.");
    }

    public void deleteLoanDeadline(int ID) {
        for (LoanDeadline obj : loanDeadlines) {
            if (obj.getID() == ID) {
                loanDeadlines.remove(ID);
                return;
            }
        }
        throw new NoSuchElementException("Loan Deadline with ID " + ID + " not found.");
    }

}