package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Item class.
 */
class ItemTest {
  Item gold;
  Item stone;

  @BeforeEach
  void setUp() {
    // Initialize the items with their respective positions and dimensions
    gold = new Gold(0, 0, 10, 10);
    stone = new Stone(1, 2, 10, 20);
  }

  /**
   * Test the getter of the x coordinate.
   */
  @Test
  void getX() {
    assertEquals(0, gold.getX());
    assertEquals(1, stone.getX());
  }

  /**
   * Test the getter of the y coordinate.
   */
  @Test
  void getY() {
    assertEquals(0, gold.getY());
    assertEquals(2, stone.getY());
  }

  /**
   * Test the getter of the width.
   */
  @Test
  void getWidth() {
    assertEquals(10, gold.getWidth());
    assertEquals(10, stone.getWidth());
  }

  /**
   * Test the getter of the height.
   */
  @Test
  void getHeight() {
    assertEquals(10, gold.getHeight());
    assertEquals(20, stone.getHeight());
  }

  /**
   * Test the setter of the x coordinate.
   */
  @Test
  void setX() {
    gold.setX(5);
    assertEquals(5, gold.getX());
    stone.setX(3);
    assertEquals(3, stone.getX());
  }

  /**
   * Test the setter of the y coordinate.
   */
  @Test
  void setY() {
    gold.setY(5);
    assertEquals(5, gold.getY());
    stone.setY(3);
    assertEquals(3, stone.getY());
  }

  /**
   * Test the setter of the width.
   */
  @Test
  void setWidth() {
    gold.setWidth(15);
    assertEquals(15, gold.getWidth());
    stone.setWidth(20);
    assertEquals(20, stone.getWidth());
  }

  /**
   * Test the setter of the height.
   */
  @Test
  void setHeight() {
    gold.setHeight(15);
    assertEquals(15, gold.getHeight());
    stone.setHeight(25);
    assertEquals(25, stone.getHeight());
  }

  /**
   * Test the collected status of the items.
   */
  @Test
  void isCollected() {
    assertFalse(gold.isCollected());
    assertFalse(stone.isCollected());

    gold.setCollected(true);
    assertTrue(gold.isCollected());

    stone.setCollected(true);
    assertTrue(stone.isCollected());
  }

  /**
   * Test the setter of the collected status.
   */
  @Test
  void setCollected() {
    gold.setCollected(true);
    assertTrue(gold.isCollected());

    stone.setCollected(true);
    assertTrue(stone.isCollected());
  }
}