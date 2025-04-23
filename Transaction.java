import java.util.*;
import java.time.LocalDate;

//Captures the information of a transaction
public class Transaction {

    private int ID;
    private TransactionType type;
    private double amount;
    private LocalDate date;
    private List<String> items;
    private User user;

    public Transaction (int ID, TransactionType type, double amount, LocalDate date, List<String> items, User user) {
        this.ID = ID;
        transactionSetter(type, amount, date, items);
        this.user = user;
    }

    public void editTransaction(TransactionType type, double amount, LocalDate date, List<String> items) {
        transactionSetter(type, amount, date, items);
    }

    private void transactionSetter(TransactionType type, double amount, LocalDate date, List<String> items) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.items = items;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<String> items) {
        this.items = new ArrayList<>(items);
    }

    public void addItem(String item) {
        this.items.add(item);
    }

    public void removeItem(String item) {
        this.items.remove(item);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}