package controller;

import java.awt.Rectangle;
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

/**
 * Main controller class for the Gold Miner game.
 * Manages game state, including the line mechanism, item generation,
 * collision handling, score tracking, timer updates, and level management.
 */
public class GameController {

  /** Random number generator for item placement. */
  private Random random;

  /** The mining line used to grab items. */
  private Line line;

  /** Player's score tracker. */
  private Score score;

  /** Game timer for controlling game duration per level. */
  private GameTimer gameTimer;

  /** Flag indicating whether the game is over. */
  private boolean gameOver = false;

  /** Flag indicating whether the game is currently paused. */
  private boolean gamePaused = false;

  /** List of items (gold and stone) present in the game. */
  private List<Item> itemList;

  /** Current game level and its configuration. */
  private Level level;

  /** Flag indicating whether the current level is complete. */
  private boolean levelComplete = false;

  /**
   * Constructs a new GameController, initializing the game components,
   * generating initial items, and setting up the timer and level.
   */
  public GameController() {
    random = new Random();
    line = new Line(300, 180, 50, 1, 0.5);
    itemList = new ArrayList<>();
    generateGold(5);
    generateStone(5);
    score = new Score();
    gameTimer = new GameTimer(10.0);
    level = new Level();
  }

  /**
   * Generates a specified number of gold items at random non-overlapping positions.
   *
   * @param amount the number of gold items to generate
   */
  public void generateGold(int amount) {
    for (int i = 0; i < amount; i++) {
      int rand_num = random.nextInt(71);
      int w = 30 + rand_num;
      int h = 30 + rand_num;
      int[] pos = generateNonOverlapPosition(w, h);
      itemList.add(new Gold(pos[0], pos[1], w, h));
    }
  }

  /**
   * Generates a specified number of stone items at random non-overlapping positions.
   *
   * @param amount the number of stone items to generate
   */
  public void generateStone(int amount) {
    for (int i = 0; i < amount; i++) {
      int rand_num = random.nextInt(71);
      int w = 30 + rand_num;
      int h = 30 + rand_num;
      int[] pos = generateNonOverlapPosition(w, h);
      itemList.add(new Stone(pos[0], pos[1], w, h));
    }
  }

  /**
   * Generates a random position within the playable area that does not overlap existing items.
   *
   * @param w the width of the item
   * @param h the height of the item
   * @return an array with x and y coordinates for the new item position
   */
  private int[] generateNonOverlapPosition(int w, int h) {
    int maxX = 600;
    int minY = 300;
    int maxY = 750;
    int playableHeight = maxY - minY;
    while (true) {
      int x = random.nextInt(maxX - w);
      int y = random.nextInt(playableHeight - h) + minY;
      if (!isOverlapped(x, y, w, h)) {
        return new int[]{x, y};
      }
    }
  }

  /**
   * Checks if a rectangle at the given position overlaps any existing item.
   *
   * @param x the x-coordinate of the rectangle
   * @param y the y-coordinate of the rectangle
   * @param w the width of the rectangle
   * @param h the height of the rectangle
   * @return true if the rectangle overlaps an existing item, false otherwise
   */
  private boolean isOverlapped(int x, int y, int w, int h) {
    for (Item item : itemList) {
      if (isRectOverlap(x, y, w, h, item.getX(), item.getY(), item.getWidth(), item.getHeight())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines whether two rectangles overlap.
   *
   * @param x1 the x-coordinate of the first rectangle
   * @param y1 the y-coordinate of the first rectangle
   * @param w1 the width of the first rectangle
   * @param h1 the height of the first rectangle
   * @param x2 the x-coordinate of the second rectangle
   * @param y2 the y-coordinate of the second rectangle
   * @param w2 the width of the second rectangle
   * @param h2 the height of the second rectangle
   * @return true if the rectangles overlap, false otherwise
   */
  private boolean isRectOverlap(int x1, int y1, int w1, int h1,
      int x2, int y2, int w2, int h2) {
    return x1 < x2 + w2 && (x1 + w1) > x2
        && y1 < y2 + h2 && (y1 + h1) > y2;
  }

  /**
   * Returns the GameTimer controlling the current level duration.
   *
   * @return the GameTimer instance
   */
  public GameTimer getGameTimer() {
    return gameTimer;
  }

  /**
   * Indicates whether the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Updates the game state for each frame, including timer update,
   * collision detection, line behavior, and item removal.
   */
  public void update() {
    if (gameOver || gamePaused) {
      return;
    }
    gameTimer.update(0.016);
    if (gameTimer.isTimeUp()) {
      if (level.shouldLevelUp(score.getCurrentScore())) {
        gamePaused = true;
        levelComplete = true;
        return;
      } else {
        gameOver = true;
        System.out.println("Game Over");
        return;
      }
    }
    if (line.getGrabbedItem() == null) {
      checkCollision();
    }
    line.update();
    removeCollectedItems();
  }

  /**
   * Checks for collisions between the line tip and items when swinging or grabbing.
   */
  private void checkCollision() {
    if (line.getLineState() == LineState.SWING || line.getLineState() == LineState.GRAB) {
      int tipX = line.getEndX();
      int tipY = line.getEndY();
      for (Item item : itemList) {
        if (!item.isCollected() && isColliding(tipX, tipY, item)) {
          System.out.println("Collision detected with an item!");
          line.setGrabbedItem(item);
          line.setLineState(LineState.RETRACT);
          break;
        }
      }
    }
  }

  /**
   * Determines if the given point collides with the specified item.
   *
   * @param x    the x-coordinate of the point
   * @param y    the y-coordinate of the point
   * @param item the Item to check collision against
   * @return true if the point is within the item's bounds, false otherwise
   */
  private boolean isColliding(int x, int y, Item item) {
    return x > item.getX() && x < item.getX() + item.getWidth()
        && y > item.getY() && y < item.getY() + item.getHeight();
  }

  /**
   * Removes items that have been collected, updating the score accordingly.
   */
  private void removeCollectedItems() {
    Iterator<Item> iterator = itemList.iterator();
    while (iterator.hasNext()) {
      Item item = iterator.next();
      if (item.isCollected()) {
        score.addPoints(item.getScoreValue());
        iterator.remove();
      }
    }
  }

  /**
   * Returns the Line used for grabbing items.
   *
   * @return the Line instance
   */
  public Line getLine() {
    return line;
  }

  /**
   * Initiates the grabbing action of the line.
   */
  public void startGrabbing() {
    line.startGrabbing();
  }

  /**
   * Indicates whether the game is currently paused.
   *
   * @return true if the game is paused, false otherwise
   */
  public boolean isGamePaused() {
    return gamePaused;
  }

  /**
   * Indicates whether the current level has been completed.
   *
   * @return true if the level is complete, false otherwise
   */
  public boolean isLevelComplete() {
    return levelComplete;
  }

  /**
   * Returns the current score.
   *
   * @return the Score instance
   */
  public Score getScore() {
    System.out.println("Current score: " + score);
    return score;
  }

  /**
   * Returns the list of items currently in the game.
   *
   * @return the list of Item objects
   */
  public List<Item> getItemList() {
    return itemList;
  }

  /**
   * Returns the current Level configuration.
   *
   * @return the Level instance
   */
  public Level getLevel() {
    return level;
  }

  /**
   * Resets the game to its initial state, clearing items, resetting the score and timer, and unpausing.
   */
  public void resetGame() {
    score.reset();
    level.reset();
    gameTimer.reset(10.232);
    line.reset();
    itemList.clear();
    generateGold(5);
    generateStone(5);
    gameOver = false;
    gamePaused = false;
    levelComplete = false;
  }

  /**
   * Advances the game to the next level, resetting timer and regenerating items with increased count.
   */
  public void gotoNextLevel() {
    level.levelUp();
    gameTimer.reset(10.232);
    itemList.clear();
    generateGold(6);
    generateStone(6);
    line.reset();
    gameOver = false;
    gamePaused = false;
    levelComplete = false;
  }
}
