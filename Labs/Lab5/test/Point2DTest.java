import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Point2D} class.
 * Tests the distance to the origin, and getter methods for x and y coordinates.
 */
class Point2DTest {

  // Point instances for testing
  private Point2D point1;
  private Point2D point2;
  private Point2D point3;

  /**
   * Initializes test points before each test.
   * Sets up points in different quadrants of the coordinate plane.
   */
  @BeforeEach
  void setUp() {
    // Point in the first quadrant (3, 4)
    point1 = new Point2D(3, 4);

    // Point in the second quadrant (-3, 4)
    point2 = new Point2D(-3, 4);

    // Point in the third quadrant (-3, -4)
    point3 = new Point2D(-3, -4);
  }

  /**
   * Tests the {@code distToOrigin()} method of the Point2D class.
   * Verifies the calculated distance from each point to the origin (0,0).
   */
  @Test
  void distToOrigin() {
    // The distance to the origin should be 5 for all three points
    assertEquals(5, point1.distToOrigin());
    assertEquals(5, point2.distToOrigin());
    assertEquals(5, point3.distToOrigin());
  }

  /**
   * Tests the {@code getX()} method of the Point2D class.
   * Verifies the x-coordinate is correctly returned for each point.
   */
  @Test
  void getX() {
    // Check the x-coordinate of each point
    assertEquals(3, point1.getX());
    assertEquals(-3, point2.getX());
    assertEquals(-3, point3.getX());
  }

  /**
   * Tests the {@code getY()} method of the Point2D class.
   * Verifies the y-coordinate is correctly returned for each point.
   */
  @Test
  void getY() {
    // Check the y-coordinate of each point
    assertEquals(4, point1.getY());
    assertEquals(4, point2.getY());
    assertEquals(-4, point3.getY());
  }
}
