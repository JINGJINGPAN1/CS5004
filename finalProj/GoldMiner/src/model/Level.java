package model;

public class Level {
  private int currentLevel;
  private int targetScore;

  public Level(){
    this.currentLevel = 1;
    this.targetScore = 200;
  }

  public boolean shouldLevelUp(int currentScore){
    return currentScore >= targetScore;
  }

  public void levelUp(){
    currentLevel++;
    targetScore += 300;
    System.out.println("Leveled up to Level: "+ currentLevel + "! New target score: " + targetScore);
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(int currentLevel) {
    this.currentLevel = currentLevel;
  }

  public int getTargetScore() {
    return targetScore;
  }


  public void setTargetScore(int targetScore) {
    this.targetScore = targetScore;
  }

  public void reset() {
    currentLevel = 1;
    targetScore = 100;
  }
}
