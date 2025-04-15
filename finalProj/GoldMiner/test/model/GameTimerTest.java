package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
/**
 * Test class for GameTimer.
 * This class contains unit tests for the GameTimer class.
 */
class GameTimerTest {
  GameTimer gameTimer;

  @BeforeEach
  void setUp() {
    gameTimer = new GameTimer(60); // Initialize with 60 seconds
  }

  /**
   * Test the update method of GameTimer.
   */
  @Test
  void update() {
    gameTimer.update(10); // Simulate 10 seconds elapsed
    assertEquals(50, gameTimer.getTimeLeft(), 0.01); // Check remaining time
  }

  /**
   * Test the getTimeLeft method of GameTimer.
   */
  @Test
  void getTimeLeft() {
    assertEquals(60, gameTimer.getTimeLeft(), 0.01); // Check initial time
    gameTimer.update(20); // Simulate 20 seconds elapsed
    assertEquals(40, gameTimer.getTimeLeft(), 0.01); // Check remaining time
  }

  /**
   * Test the isTimeUp method of GameTimer.
   */
  @Test
  void isTimeUp() {
    assertFalse(gameTimer.isTimeUp()); // Check initial state
    gameTimer.update(60); // Simulate 60 seconds elapsed
    assertTrue(gameTimer.isTimeUp()); // Check if time is up
  }

  /**
   * Test the reset method of GameTimer.
   */
  @Test
  void reset() {
    gameTimer.update(30); // Simulate 30 seconds elapsed
    assertEquals(30, gameTimer.getTimeLeft(), 0.01); // Check remaining time
    gameTimer.reset(90); // Reset to 90 seconds
    assertEquals(90, gameTimer.getTimeLeft(), 0.01); // Check remaining time after reset
    assertFalse(gameTimer.isTimeUp()); // Check if time is up after reset
  }
}