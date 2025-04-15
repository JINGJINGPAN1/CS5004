package model;

/**
 * GameTimer class to manage the game timer.
 */
public class GameTimer {
  private double timeLeft; // Remaining time in seconds
  private boolean timeUp; // Flag to indicate if time is up

  /**
   * Constructor to initialize the GameTimer with a total time.
   * @param totalTime Total time in seconds for the timer.
   */
  public GameTimer(double totalTime) {
    this.timeLeft = totalTime;
    this.timeUp = false;
  }

  /**
   * Update the timer by subtracting the elapsed time.
   * @param elapsedSeconds Elapsed time in seconds to subtract from the timer.
   */
  public void update(double elapsedSeconds) {
    if(!timeUp){
      timeLeft -= elapsedSeconds;
      if (timeLeft <= 0) {
        timeLeft = 0;
        timeUp = true;
      }
    }
  }

  /**
   * Get the remaining time.
   * @return Remaining time in seconds.
   */
  public double getTimeLeft() {
    return timeLeft;
  }

  /**
   * Check if the time is up.
   * @return true if time is up, false otherwise.
   */
  public boolean isTimeUp() {
    return timeUp;
  }

  /**
   * Reset the timer to a new total time.
   * @param totalTime New total time in seconds for the timer.
   */
  public void reset(double totalTime) {
    this.timeLeft = totalTime;
    this.timeUp = false;
  }

}
