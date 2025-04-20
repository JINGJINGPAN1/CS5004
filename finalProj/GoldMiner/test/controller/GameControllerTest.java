package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import model.Gold;
import model.Item;
import model.Line;
import model.LineState;
import model.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for GameController.
 * This class contains unit tests for the GameController class,
 * ensuring that the game logic and item generation work as expected.
 */
class GameControllerTest {
  GameController gameController;

  @BeforeEach
  void setUp() {
    gameController = new GameController();
  }

  /**
   * Test the generation of gold items.
   */
  @Test
  void generateGold() {
    List<Item> itemList = gameController.getItemList();
    itemList.clear();
    gameController.generateGold(5);
    long goldCount = itemList.stream().filter(item -> item instanceof Gold).count();
    assertEquals(5, goldCount, "Should generate exactly 5 gold items");
  }

  /**
   * Test the generation of stone items.
   */
  @Test
  void generateStone() {
    List<Item> itemList = gameController.getItemList();
    itemList.clear();
    gameController.generateStone(3);
    long stoneCount = itemList.stream().filter(item -> item instanceof Stone).count();
    assertEquals(3, stoneCount, "Should generate exactly 3 stone items");
  }

  /**
   * Test the getGameTimer method.
   */
  @Test
  void getGameTimer() {
    assertNotNull(gameController.getGameTimer());
    assertEquals(10.0, gameController.getGameTimer().getTimeLeft(), 0.1);
  }

  /**
   * Test the isGameOver method.
   */
  @Test
  void isGameOver() {
    assertFalse(gameController.isGameOver());
  }

  /**
   * Test the update method when the game is paused or over.
   */
  @Test
  void updateGamePausedOrOver() {
    gameController.getGameTimer().reset(10);
    gameController.update();
    double timeLeft1 = gameController.getGameTimer().getTimeLeft();
    gameController.update();
    double timeLeft2 = gameController.getGameTimer().getTimeLeft();

    assertTrue(timeLeft1 > timeLeft2);
    gameController.resetGame();
    gameController.getGameTimer().update(10.232);
    gameController.update();
    assertTrue(gameController.isGameOver());
    gameController.update();
    assertTrue(gameController.isGameOver());
  }

  /**
   * Test the update method when the game completes a level.
   */
  @Test
  void updateLevelComplete() {
    gameController.getScore().addPoints(gameController.getLevel().getTargetScore());
    gameController.getGameTimer().reset(0.01);
    gameController.update();
    assertTrue(gameController.isLevelComplete());
    assertTrue(gameController.isGamePaused());
  }

  /**
   * Test the update method when the game has a collision.
   */
  @Test
  void updateCollisionDetection() {
    Line line = gameController.getLine();
    gameController.getItemList().clear();
    gameController.startGrabbing();

    for (int i = 0; i < 20; i++) {
      gameController.update();
    }

    Gold gold = new Gold(line.getEndX() - 5, line.getEndY() - 5, 10, 10);
    gameController.getItemList().add(gold);

    gameController.update();;

    assertEquals(LineState.RETRACT, line.getLineState());
    assertEquals(gold, line.getGrabbedItem());
  }

  /**
   * Test the update method when the game removes a grabbed item.
   */
  @Test
  void updateRemoveGrabbedItem() {
    List<Item> items = gameController.getItemList();
    Item item = items.get(0);
    item.setCollected(true);
    int scoreBefore = gameController.getScore().getCurrentScore();
    gameController.update();
    assertFalse(gameController.getItemList().contains(item));
    assertTrue(gameController.getScore().getCurrentScore() > scoreBefore);
  }

  /**
   * Test the getter for the line object.
   */
  @Test
  void getLine() {
    assertNotNull(gameController.getLine());
  }

  /**
   * Test the startGrabbing method.
   */
  @Test
  void startGrabbing() {
    gameController.startGrabbing();
    assertEquals(LineState.GRAB, gameController.getLine().getLineState());
  }

  /**
   * Test the isGamePaused method.
   */
  @Test
  void isGamePaused() {
    assertFalse(gameController.isGamePaused());
  }

  /**
   * Test the isLevelComplete method.
   */
  @Test
  void isLevelComplete() {
    assertFalse(gameController.isLevelComplete());
  }

  /**
   * Test the getter for the score object.
   */
  @Test
  void getScore() {
    assertNotNull(gameController.getScore());
    assertEquals(0, gameController.getScore().getCurrentScore());
  }

  /**
   * Test the getter for the item list.
   */
  @Test
  void getItemList() {
    assertEquals(10, gameController.getItemList().size());
    for (Item item : gameController.getItemList()) {
      assertNotNull(item);
    }
  }

  /**
   * Test the getter for the level object.
   */
  @Test
  void getLevel() {
    assertNotNull(gameController.getLevel());
    assertEquals(1, gameController.getLevel().getCurrentLevel());
  }

  /**
   * Test the resetGame method.
   */
  @Test
  void resetGame() {
    gameController.getScore().addPoints(100);
    gameController.getGameTimer().update(10);
    gameController.update();

    assertTrue(gameController.isGameOver());

    gameController.resetGame();
    assertFalse(gameController.isGameOver());
    assertFalse((gameController.isGamePaused()));
    assertFalse(gameController.isLevelComplete());
    assertEquals(0, gameController.getScore().getCurrentScore());
    assertEquals(10.232, gameController.getGameTimer().getTimeLeft(), 0.01);
    assertEquals(10, gameController.getItemList().size());
    assertEquals(LineState.SWING, gameController.getLine().getLineState());
  }

  /**
   * Test the gotoNextLevel method.
   */
  @Test
  void gotoNextLevel() {
    int initialLevel = gameController.getLevel().getCurrentLevel();
    gameController.gotoNextLevel();
    assertEquals(initialLevel + 1, gameController.getLevel().getCurrentLevel());
    assertEquals(10.232, gameController.getGameTimer().getTimeLeft(), 0.01);
    assertEquals(12, gameController.getItemList().size());
    assertFalse(gameController.isGameOver());
    assertFalse(gameController.isGamePaused());
    assertFalse(gameController.isLevelComplete());
  }
}