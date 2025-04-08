package model;

public class GameTimer {
  private double timeLeft;
  private boolean timeUp;

  public GameTimer(double totalTime) {
    this.timeLeft = totalTime;
    this.timeUp = false;
  }

  public void update(double elapsedSeconds) {
    if(!timeUp){
      timeLeft -= elapsedSeconds;
      if(timeLeft <= 0){
        timeLeft = 0;
        timeUp = true;
      }
    }
  }

  public double getTimeLeft() {
    return timeLeft;
  }

  public boolean isTimeUp() {
    return timeUp;
  }

  public void reset(double totalTime) {
    this.timeLeft = totalTime;
    this.timeUp = false;
  }

}
