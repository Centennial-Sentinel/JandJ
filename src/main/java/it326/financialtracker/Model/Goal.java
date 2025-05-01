package it326.financialtracker.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Captures the information of a goal
@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "target_amount")
    private double targetAmount;

    @Column(name = "current_amount")
    private double currentAmount;

    @ManyToOne
    @JoinColumn(name = "myuser_id")
    private User myuser;

  public Goal (long theGoal, String desc, double trgtAmount, double curAmount, User theUser) {
      this.id = theGoal;
      goalSetter(desc, trgtAmount, curAmount);
      this.myuser = theUser;
  }

  public void editGoal(String desc, double trgtAmount, double curAmount) {
      goalSetter(desc, trgtAmount, curAmount);
  }

  private void goalSetter(String desc, double trgtAmount, double curAmount) {
      this.description = desc;
      this.targetAmount = trgtAmount;
      this.currentAmount = curAmount;
  }

  public long getGoalID() {
      return id;
  }

  public void setGoalID(long ID) {
      this.id = ID;
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
      return myuser;
  }

  public void setUser(User user) {
      this.myuser = user;
  }
}

