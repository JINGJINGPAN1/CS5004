package model;

public class Score {
  private int currentScore;

  public Score() {
    currentScore = 0;
  }

  public void addPoints(int points) {
    currentScore += points;
  }

  public int getCurrentScore() {
    return currentScore;
  }

  public void rest() {
    this.currentScore = 0;
  }

}
