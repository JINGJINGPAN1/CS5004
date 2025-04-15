package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * The LevelTest class contains unit tests for the Level class.
 * It tests the functionality of leveling up and managing target scores.
 */
class LevelTest {
  Level level;

  @BeforeEach
  void setUp() {
    level = new Level();
  }

  /**
   * Test to check if the level up condition is met.
   * It verifies if the current score is greater than or equal to the target score.
   */
  @Test
  void shouldLevelUp() {
    assertTrue(level.shouldLevelUp(200));
    assertFalse(level.shouldLevelUp(199));
    assertTrue(level.shouldLevelUp(500));
  }

  /**
   * Test to check the level up functionality.
   * It verifies if the current level and target score are updated correctly.
   */
  @Test
  void levelUp() {
    level.levelUp();
    assertEquals(2, level.getCurrentLevel());
    assertEquals(500, level.getTargetScore());
  }

  /**
   * Test to check the current level of the Level object.
   * It verifies if the current level is retrieved correctly.
   */
  @Test
  void getCurrentLevel() {
    assertEquals(1, level.getCurrentLevel());
    level.levelUp();
    assertEquals(2, level.getCurrentLevel());
  }

  /**
   * Test to check the setting of the current level.
   * It verifies if the target score is set correctly.
   */
  @Test
  void setCurrentLevel() {
    level.setCurrentLevel(3);
    assertEquals(3, level.getCurrentLevel());
    level.setCurrentLevel(1);
    assertEquals(1, level.getCurrentLevel());
  }

  /**
   * Test to check the target score of the Level object.
   * It verifies if the target score is retrieved correctly.
   */
  @Test
  void getTargetScore() {
    assertEquals(200, level.getTargetScore());
    level.levelUp();
    assertEquals(500, level.getTargetScore());
  }

  /**
   * Test to check the setting of the target score.
   * It verifies if the target score is set correctly.
   */
  @Test
  void setTargetScore() {
    level.setTargetScore(1000);
    assertEquals(1000, level.getTargetScore());
    level.setTargetScore(200);
    assertEquals(200, level.getTargetScore());
  }

  /**
   * Test to check the reset functionality of the Level object.
   * It verifies if the current level and target score are reset correctly.
   */
  @Test
  void reset() {
    level.setCurrentLevel(5);
    level.setTargetScore(2000);
    assertEquals(5, level.getCurrentLevel());
    assertEquals(2000, level.getTargetScore());
    level.reset();
    assertEquals(1, level.getCurrentLevel());
    assertEquals(100, level.getTargetScore());
  }
}