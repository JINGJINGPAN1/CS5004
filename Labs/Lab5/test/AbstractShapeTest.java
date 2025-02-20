import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the AbstractShape class and its concrete implementations:
 * Circle, Rectangle, and Triangle.
 * This test class verifies the behavior of distanceFromOrigin() and compareTo() methods.
 */
class AbstractShapeTest {

  // Shape instances for testing
  private Circle circle;
  private Rectangle rectangle;
  private Triangle triangle;

  /**
   * Initializes shape objects before each test.
   * Sets up a Circle, Rectangle, and Triangle with specific coordinates and dimensions.
   */
  @BeforeEach
  void setUp() {
    // Circle with center at (3,4) and radius 5
    circle = new Circle(3, 4, 5);

    // Rectangle with top-left corner at (0,0) and width 2, height 3
    rectangle = new Rectangle(0, 0, 2, 3);

    // Triangle with vertices at (1,1), (4,1), (1,5)
    triangle = new Triangle(1, 1, 4, 1, 1, 5);
  }

  /**
   * Tests the distanceFromOrigin() method for all shape instances.
   * Verifies the calculated distance to the origin (0,0) with a tolerance of 0.001.
   */
  @Test
  void distanceFromOrigin() {
    // Circle's center at (3,4) should have a distance of 5 from the origin
    assertEquals(5, circle.distanceFromOrigin(), 0.001);

    // Rectangle's top-left corner at (0,0) should have a distance of 0 from the origin
    assertEquals(0, rectangle.distanceFromOrigin(), 0.001);

    // Triangle's closest vertex at (1,1) should have a distance of approx. 1.414
    assertEquals(1.414, triangle.distanceFromOrigin(), 0.001);
  }

  /**
   * Tests the compareTo() method of shapes, which compares the area of shapes.
   * Asserts the expected comparison results between the shapes.
   */
  @Test
  void compareTo() {
    // Compare the area of the circle with the rectangle; expect circle > rectangle => 1
    assertEquals(1, circle.compareTo(rectangle), 0.001);

    // Compare the triangle with the circle; expect triangle < circle => -1
    assertEquals(-1, triangle.compareTo(circle), 0.001);

    // Compare the rectangle with the triangle; expect rectangle == triangle => 0
    assertEquals(0, rectangle.compareTo(triangle), 0.001);
  }
}
