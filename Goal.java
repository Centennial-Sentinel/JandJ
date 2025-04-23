import java.util.*;

//Captures the information of a transaction
public class Goal {

    private int goal;
    private String description;
    private double targetAmount;
    private double currentAmount;
    private User user;

    public Goal (int theGoal, String desc, double trgtAmount, double curAmount, User theUser) {
        this.goal = theGoal;
        transactionSetter(desc, trgtAmount, curAmount);
        this.user = user;
    }

    public void editGoal(String desc, double trgtAmount, double curAmount) {
        goalSetter(desc, trgtAmount, curAmount);
    }

    private void goalSetter(String desc, double trgtAmount, double curAmount) {
        this.description = desc;
        this.targetAmount = trgtAmount;
        this.currentAmount = curAmount;
    }

    public int getGoalID() {
        return goal;
    }

    public void setGoalID(int ID) {
        this.goal = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double trgtAmount) {
        this.targetAmount = trgtAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double curAmount) {
        this.currentAmount = curAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
