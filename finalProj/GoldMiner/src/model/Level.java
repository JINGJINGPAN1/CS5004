package model;

/**
 * The Level class represents the player's level in a game.
 * It manages the current level and the target score required to level up.
 */
public class Level {
  private int currentLevel; // The current level of the player
  private int targetScore; // The score required to level up

  /**
   * Constructor to initialize the Level object.
   * Sets the initial level to 1 and target score to 200.
   */
  public Level(){
    this.currentLevel = 1;
    this.targetScore = 200;
  }

  /**
   * Checks if the player should level up based on the current score.
   * @param currentScore
   * @return true if the current score is greater than or equal to the target score, false otherwise.
   */
  public boolean shouldLevelUp(int currentScore){
    return currentScore >= targetScore;
  }

  /**
   * Increments the current level and updates the target score.
   * The target score increases by 300 for each level up.
   */
  public void levelUp(){
    currentLevel++;
    targetScore += 300;
    System.out.println("Leveled up to Level: "+ currentLevel + "! New target score: " + targetScore);
  }

  /**
   * Getters for currentLevel
   * @return the current level of the player.
   */
  public int getCurrentLevel() {
    return currentLevel;
  }

  /**
   * Setters for currentLevel
   * @param currentLevel the new level to set.
   */
  public void setCurrentLevel(int currentLevel) {
    this.currentLevel = currentLevel;
  }

  /**
   * Getters for targetScore
   * @return the target score required to level up.
   */
  public int getTargetScore() {
    return targetScore;
  }

  /**
   * Setters for targetScore
   * @param targetScore the new target score to set.
   */
  public void setTargetScore(int targetScore) {
    this.targetScore = targetScore;
  }

  /**
   * Resets the level to the initial state.
   * Sets the current level to 1 and target score to 200.
   */
  public void reset() {
    currentLevel = 1;
    targetScore = 100;
  }
}
