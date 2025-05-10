package data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lego")
public class Lego {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int run;
  private int speed;
  private int turn;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRun() {
    return run;
  }

  public void setRun(int run) {
    this.run = run;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getTurn() {
    return turn;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }

}
