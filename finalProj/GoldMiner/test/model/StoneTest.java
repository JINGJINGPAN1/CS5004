package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Test class for the Stone class.
 * This class contains unit tests for the methods in the Stone class.
 */
class StoneTest {
  Stone stone1;
  Stone stone2;
  Stone stone3;

  @BeforeEach
  void setUp() {
    stone1 = new Stone(0, 0, 100, 100);
    stone2 = new Stone(50, 50, 10, 10);
    stone3 = new Stone(0, 0, 50, 50);
  }

  @Test
  void getScoreValue() {
    assertEquals(250, stone1.getScoreValue());
    assertEquals(50, stone2.getScoreValue());
    assertEquals(100, stone3.getScoreValue());
  }

  @Test
  void computeRetractSpeed() {
    assertEquals(0.5, stone1.computeRetractSpeed());
    assertEquals(2.5, stone2.computeRetractSpeed());
    assertEquals(1.0, stone3.computeRetractSpeed());
  }
}