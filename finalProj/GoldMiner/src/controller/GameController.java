package controller;

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.GameTimer;
import model.Gold;
import model.Item;
import model.Level;
import model.Line;
import model.LineState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Score;
import model.Stone;

public class GameController {
  private Random random;
  private Line line;
  private Score score;
  private GameTimer gameTimer;
  private boolean gameOver = false;
  private boolean gamePaused = false;
  private List<Item> itemList;
  private Level level;

  public GameController() {
    random = new Random();

    // Initialize the line
    line = new Line(300, 180, 50, 1, 0.5);
    // Initialize the item list
    itemList = new ArrayList<>();
    generateGold(5);
    generateStone(5);
    // Initialize the Score
    score = new Score();
    // Initialize the game timer
    gameTimer = new GameTimer(10.0);
    // Initialize the level
    level = new Level();

  }

  public void generateGold(int amount){
    for(int i = 0; i < amount; i++){
      int rand_num = random.nextInt(71);
      int w = 30 + rand_num;
      int h = 30 + rand_num;

      int[] pos = generateNonOverlapPosition(w, h);
      itemList.add(new Gold(pos[0], pos[1], w, h));
    }
  }

  public void generateStone(int amount){
    for(int i = 0; i < amount; i++){
      int rand_num = random.nextInt(71);
      int w = 30 + rand_num;
      int h = 30 + rand_num;

      int[] pos = generateNonOverlapPosition(w, h);
      itemList.add(new Stone(pos[0], pos[1], w, h));
    }
  }

  private int[] generateNonOverlapPosition(int w, int h) {
    // gameWindow 600 * 800
    int maxX = 600;
    int minY = 200;
    int maxY = 800;

    int playableHeight = maxY - minY;

    while(true){
      int x = random.nextInt(maxX - w);
      int y = random.nextInt(playableHeight - h) + minY;

      // check isOverlapped
      if(!isOverlapped(x, y, w, h)){
        return new int[]{x, y};
      }
    }
  }

  private boolean isOverlapped(int x, int y, int w, int h) {
    for(Item item : itemList){
      if(isRectOverlap(x, y, w, h, item.getX(), item.getY(), item.getWidth(), item.getHeight())){
        return true;
      }
    }
    return false;
  }

  private boolean isRectOverlap(int x1, int y1, int w1, int h1, int x2, int y2, int w2,
      int h2) {
    return x1 < x2 + w2 && (x1 + w1) > x2
        && y1 < y2 + h2 && (y1 + h1) > y2;
  }

  public GameTimer getGameTimer() {
    return gameTimer;
  }

  public void setGameTimer(GameTimer gameTimer) {
    this.gameTimer = gameTimer;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public void update() {
    if(gameOver || gamePaused){
      return;
    }
    // We approximate each frame as 16 ms => 0.016s
    gameTimer.update(0.016);
    if(gameTimer.isTimeUp()){
      if(level.shouldLevelUp(score.getCurrentScore())){
        gamePaused = true;
        showLevelCompleteDialog();
        return;
      }else{
        gameOver = true;
        System.out.println("Game Over");
        return;
      }
    }
    // 1) Check collision only if the line isn't already carrying something
    //    (meaning both grabbedGold and grabbedStone are null).
    if (line.getGrabbedItem() == null) {
      checkCollision();
    }
    // 2) Update the line each frame
    line.update();
    // 3) Remove gold that is marked as collected or stone that is marked as collected
    removeCollectedItems();
  }

  private void showLevelCompleteDialog() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Object[] options = { "Enter NEXT LEVEL", "RETURN TO GAME" };
        int option = JOptionPane.showOptionDialog(null,
            "Congratulations! You have reached the target score.\nWhat would you like to do?",
            "Level Complete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        if(option == JOptionPane.YES_OPTION){
          gotoNextLevel();
        }else{
          resumeGame();
        }
      }
    });
  }

  private void resumeGame() {
    gameTimer.reset(10.0);
    score.reset();
    level.reset();
    gameOver = false;
    gamePaused = false;
  }

  public void resetGame() {
    // Reset the game timer to initial time (e.g. 10.0 seconds for a new game)
    gameTimer.reset(10.0);
    // Reset the score to 0
    score.reset();
    // Reset the level back to initial values
    level.reset();
    // Reset the line to its initial state
    line.reset();
    // Clear current items and generate new ones
    itemList.clear();
    generateGold(5);
    generateStone(5);
    // Unpause game, allowing updates to occur
    gameOver = false;
  }

  private void gotoNextLevel() {
    level.levelUp();
    gameTimer.reset(30.0);
    itemList.clear();
    generateGold(6);
    generateStone(6);
    line.reset();
    gameOver = false;
    gamePaused = false;
  }

  private void checkCollision() {
    if (line.getLineState() == LineState.SWING || line.getLineState() == LineState.GRAB) {
      int tipX = line.getEndX();
      int tipY = line.getEndY();

      for (Item item : itemList) {
        if (!item.isCollected() && isColliding(tipX, tipY, item)) {
          // We have collision
          System.out.println("Collision detected with an item!");
          // Let the line hold this gold
          line.setGrabbedItem(item);
          // Switch line to RETRACT immediately
          line.setLineState(LineState.RETRACT);
          // Break so we only grab one gold at a time
          break;
        }
      }
    }
  }

  private boolean isColliding(int x, int y, Item item) {
    return x > item.getX() && x < item.getX() + item.getWidth()
        && y > item.getY() && y < item.getY() + item.getHeight();
  }
  
  private void removeCollectedItems() {
    Iterator<Item> iterator = itemList.iterator();
    while (iterator.hasNext()) {
      Item item = iterator.next();
      if (item.isCollected()) {
        // Add points based on item type
        if (item instanceof Gold) {
          score.addPoints(item.getScoreValue());
        } else if (item instanceof Stone) {
          score.addPoints(item.getScoreValue());
        }
        // Remove the item from the list so it is no longer drawn or processed.
        iterator.remove();
      }
    }
  }

  // For the View
  public List<Gold> getGoldList() {
    List<Gold> goldList = new ArrayList<>();
    for (Item item : itemList) {
      if (item instanceof Gold) {
        goldList.add((Gold) item);
      }
    }
    return goldList;
  }
  public List<Stone> getStoneList() {
    List<Stone> stoneList = new ArrayList<>();
    for (Item item : itemList) {
      if(item instanceof Stone) {
        stoneList.add((Stone) item);
      }
    }
    return stoneList;
  }

  public Line getLine() {
    return line;
  }

  // Methods that handle user input
  public void startGrabbing() {
    line.startGrabbing();
  }

  public void startRetracting() {
    line.startRetracting();
  }

  public Score getScore() {
    System.out.println("Current score: " + score);
    return score;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public Level getLevel() {
    return level;
  }

  public boolean isGamePaused() {
    return gamePaused;
  }
}
