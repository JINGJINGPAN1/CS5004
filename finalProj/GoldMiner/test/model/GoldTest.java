package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Gold class.
 * This class tests the functionality of the Gold class, including
 * score value calculation and retract speed computation.
 */
class GoldTest {
  Gold gold1;
  Gold gold2;
  Gold gold3;

  @BeforeEach
  void setUp() {
    gold1 = new Gold(0, 0, 100, 100);
    gold2 = new Gold(50, 50, 10, 10);
    gold3 = new Gold(0, 0, 50, 50);
  }

  /**
   * Test the getter of the score value.
   */
  @Test
  void getScoreValue() {
    assertEquals(250, gold1.getScoreValue());
    assertEquals(50, gold2.getScoreValue());
    assertEquals(100, gold3.getScoreValue());
  }

  /**
   * Test the retract speed calculation of the Gold object.
   */
  @Test
  void computeRetractSpeed() {
    assertEquals(3.5, gold1.computeRetractSpeed());
    assertEquals(5.0, gold2.computeRetractSpeed());
    assertEquals(4.5, gold3.computeRetractSpeed());
  }
}