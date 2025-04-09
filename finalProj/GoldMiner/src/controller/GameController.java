package controller;

import java.util.Random;
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
  private final Score score;
  private GameTimer gameTimer;
  private boolean gameOver = false;
  private List<Item> itemList;
  private Level level;

  public GameController() {
    random = new Random();

    // Initialize the line
    line = new Line(300, 180, 50, 1, 0.5);

    // Initialize BOTH lists first
//    goldList = new ArrayList<>();
//    stoneList = new ArrayList<>();

    // Now produce gold (stoneList is not null anymore)
//
    itemList = new ArrayList<>();
    generateGold(5);
    generateStone(5);

    // Initialize the Score
    score = new Score();
    // Initialize the game timer
    gameTimer = new GameTimer(10.0);
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
    // gameWindow 768 * 1000
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
//    for(Gold gold : goldList){
//      if(isRectOverlap(x, y, w, h, gold.getX(), gold.getY(), gold.getWidth(), gold.getHeight())){
//        return true;
//      }
//    }
//
//    for(Stone stone : stoneList){
//      if(isRectOverlap(x, y, w, h, stone.getX(), stone.getY(), stone.getWidth(), stone.getHeight())){
//        return true;
//      }
//    }
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
    if(gameOver){
      return;
    }
    // We approximate each frame as 16 ms => 0.016s
    gameTimer.update(0.016);
    if(gameTimer.isTimeUp()){
      if(level.shouldLevelUp(score.getCurrentScore())){
//        level.levelUp();
//        gameTimer.reset(20.0);
        gotoNextLevel();
      }else{
        gameOver = true;
        System.out.println("Game Over");
        return;
      }
    }
    // 1) Check collision only if the line isn't already carrying something
    //    (meaning both grabbedGold and grabbedStone are null).
//    if (line.getGrabbedGold() == null && line.getGrabbedStone() == null) {
//      checkCollision();
//    }
    if (line.getGrabbedItem() == null) {
      checkCollision();
    }
    // 2) Update the line each frame
    line.update();
    // 3) Remove gold that is marked as collected or stone that is marked as collected
    removeCollectedItems();
  }

  private void gotoNextLevel() {
    level.levelUp();
    gameTimer.reset(15.0);
    itemList.clear();
    generateGold(6);
    generateStone(6);
    line.reset();
  }

  private void checkCollision() {
    // If line is swinging or grabbing outward, we can check collision
    // In your game logic, you might only check collision in certain states
//    if (line.getLineState() == LineState.SWING || line.getLineState() == LineState.GRAB) {
//      int tipX = line.getEndX();
//      int tipY = line.getEndY();
//
//      for (Gold gold : goldList) {
//        if (!gold.isCollected() && isColliding(tipX, tipY, gold)) {
//          // We have collision
//          System.out.println("Collision detected with a gold piece!");
//          // Let the line hold this gold
//          line.setGrabbedGold(gold);
//          // Switch line to RETRACT immediately
//          line.setLineState(LineState.RETRACT);
//          // Break so we only grab one gold at a time
//          break;
//        }
//      }
//
//      for(Stone stone : stoneList) {
//        if(!stone.isCollected() && isColliding(tipX, tipY, stone)) {
//          System.out.println("Collision detected with a stone piece!");
//          line.setGrabbedStone(stone);
//          line.setLineState(LineState.RETRACT);
//          break;
//        }
//      }
//    }
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

//  private boolean isColliding(int x, int y, Gold gold) {
//    return x > gold.getX() && x < gold.getX() + gold.getWidth()
//        && y > gold.getY() && y < gold.getY() + gold.getHeight();
//  }
//
//  private boolean isColliding(int x, int y, Stone stone) {
//    return x > stone.getX() && x < stone.getX() + stone.getWidth()
//        && y > stone.getY() && y < stone.getY() + stone.getHeight();
//  }
  private boolean isColliding(int x, int y, Item item) {
    return x > item.getX() && x < item.getX() + item.getWidth()
        && y > item.getY() && y < item.getY() + item.getHeight();
  }
  
  private void removeCollectedItems() {
    // Optionally remove any gold that isCollected from the list,
    // so it's no longer drawn or processed.
//    Iterator<Gold> iterator = goldList.iterator();
//    while (iterator.hasNext()) {
//      Gold gold = iterator.next();
//      if (gold.isCollected()) {
//
//        // add points for the gold
//        score.addPoints(computeGoldPoints(gold));
//        iterator.remove();
//      }
//    }
//
//    Iterator<Stone> iterator1 = stoneList.iterator();
//    while (iterator1.hasNext()) {
//      Stone stone = iterator1.next();
//      if (stone.isCollected()) {
//        score.addPoints(computeStonePoints(stone));
//        iterator1.remove();
//      }
//    }
    Iterator<Item> iterator = itemList.iterator();
    while (iterator.hasNext()) {
      Item item = iterator.next();
      if (item.isCollected()) {
        // Add points based on item type
        if (item instanceof Gold) {
          score.addPoints(item.getScoreValue(item.getWidth(), item.getHeight()));
        } else if (item instanceof Stone) {
          score.addPoints(item.getScoreValue(item.getWidth(), item.getHeight()));
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

//  public void startRetracting() {
//    line.startRetracting();
//  }

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
}
