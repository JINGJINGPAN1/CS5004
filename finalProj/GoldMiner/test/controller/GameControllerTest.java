package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import model.Gold;
import model.Item;
import model.Line;
import model.LineState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameControllerTest {
  GameController gameController;

  @BeforeEach
  void setUp() {
    gameController = new GameController();
  }

  @Test
  void generateGold() {
  }

  @Test
  void generateStone() {
  }

  @Test
  void getGameTimer() {
    assertNotNull(gameController.getGameTimer());
    assertEquals(10.0, gameController.getGameTimer().getTimeLeft(), 0.1);
  }

  @Test
  void isGameOver() {
    assertFalse(gameController.isGameOver());
  }

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

  @Test
  void updateLevelComplete() {
    gameController.getScore().addPoints(gameController.getLevel().getTargetScore());
    gameController.getGameTimer().reset(0.01);
    gameController.update();
    assertTrue(gameController.isLevelComplete());
    assertTrue(gameController.isGamePaused());
  }

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

  @Test
  void getLine() {
    assertNotNull(gameController.getLine());
  }

  @Test
  void startGrabbing() {
    gameController.startGrabbing();
    assertEquals(LineState.GRAB, gameController.getLine().getLineState());
  }

  @Test
  void isGamePaused() {
    assertFalse(gameController.isGamePaused());
  }

  @Test
  void isLevelComplete() {
    assertFalse(gameController.isLevelComplete());
  }

  @Test
  void getScore() {
    assertNotNull(gameController.getScore());
    assertEquals(0, gameController.getScore().getCurrentScore());
  }

  @Test
  void getItemList() {
    assertEquals(10, gameController.getItemList().size());
    for (Item item : gameController.getItemList()) {
      assertNotNull(item);
    }
  }

  @Test
  void getLevel() {
    assertNotNull(gameController.getLevel());
    assertEquals(1, gameController.getLevel().getCurrentLevel());
  }

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