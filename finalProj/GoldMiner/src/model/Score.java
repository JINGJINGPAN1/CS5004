package model;
/**
 * Score class to manage the player's score in the game.
 * It allows adding points, getting the current score, and resetting the score.
 */
public class Score {
  private int currentScore; // Current score of the player

  /**
   * Constructor to initialize the score to zero.
   */
  public Score() {
    currentScore = 0;
  }

  /**
   * Method to add points to the current score.
   * @param points The number of points to add.
   */
  public void addPoints(int points) {
    currentScore += points;
  }

  /**
   * Getter method to retrieve the current score.
   * @return The current score of the player.
   */
  public int getCurrentScore() {
    return currentScore;
  }

  /**
   * Method to reset the score to zero.
   */
  public void reset() {
    currentScore = 0;
  }

}
