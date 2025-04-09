package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * ScoreTest class to test the functionality of the Score class.
 */
class ScoreTest {
  Score score;

  @BeforeEach
  void setUp() {
    score = new Score();
  }

  /**
   * Test the addPoints method of the Score class.
   * It checks if points are added correctly to the current score.
   */
  @Test
  void addPoints() {
    score.addPoints(10);
    assertEquals(10, score.getCurrentScore());
    score.addPoints(5);
    assertEquals(15, score.getCurrentScore());
    score.addPoints(-5);
    assertEquals(10, score.getCurrentScore());
    score.addPoints(0);
    assertEquals(10, score.getCurrentScore());
  }

  /**
   * Test the getCurrentScore method of the Score class.
   * It checks if the current score is retrieved correctly.
   */
  @Test
  void getCurrentScore() {
    assertEquals(0, score.getCurrentScore());
    score.addPoints(20);
    assertEquals(20, score.getCurrentScore());
    score.addPoints(-10);
    assertEquals(10, score.getCurrentScore());
    score.addPoints(30);
    assertEquals(40, score.getCurrentScore());
  }

  /**
   * Test the reset method of the Score class.
   * It checks if the score is reset correctly to zero.
   */
  @Test
  void reset() {
    score.addPoints(10);
    assertEquals(10, score.getCurrentScore());
    score.reset();
    assertEquals(0, score.getCurrentScore());
    score.addPoints(20);
    assertEquals(20, score.getCurrentScore());
    score.reset();
    assertEquals(0, score.getCurrentScore());
  }
}