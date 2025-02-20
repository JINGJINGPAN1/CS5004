import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Circle} class.
 * Tests methods including area calculation, perimeter calculation, resizing, and string representation.
 */
class CircleTest {

  // Circle instances for testing
  private Circle circle1;
  private Circle circle2;
  private Circle circle3;

  /**
   * Initializes test circles before each test.
   * Sets up circles with specific centers and radii for testing.
   */
  @BeforeEach
  void setUp() {
    // Circle with center at (3,4) and radius 5
    circle1 = new Circle(3, 4, 5);

    // Circle with center at (10.32,10.43) and radius 10
    circle2 = new Circle(10.32, 10.43, 10);

    // Circle with center at origin (0,0) and radius 20
    circle3 = new Circle(20);
  }

  /**
   * Tests the {@code area()} method of the Circle class.
   * Verifies that the calculated area matches the expected value.
   */
  @Test
  void area() {
    // Area of a circle is π * radius^2
    assertEquals(Math.PI * 25, circle1.area(), 0.001);
    assertEquals(Math.PI * 100, circle2.area(), 0.001);
    assertEquals(Math.PI * 400, circle3.area(), 0.001);
  }

  /**
   * Tests the {@code perimeter()} method of the Circle class.
   * Verifies that the calculated perimeter matches the expected value.
   */
  @Test
  void perimeter() {
    // Perimeter (circumference) of a circle is 2 * π * radius
    assertEquals(2 * Math.PI * 5, circle1.perimeter(), 0.001);
    assertEquals(2 * Math.PI * 10, circle2.perimeter(), 0.001);
    assertEquals(2 * Math.PI * 20, circle3.perimeter(), 0.001);
  }

  /**
   * Tests the {@code resize()} method of the Circle class.
   * Ensures that resizing the circle correctly scales its area.
   */
  @Test
  void resize() {
    // Resize circle1 by a factor of 2.5 and verify the new area
    Shape resizedCircle1 = circle1.resize(2.5);
    assertEquals(2.5 * circle1.area(), resizedCircle1.area(), 0.001);

    // Resize circle2 by a factor of 0 (should result in 0 area)
    Shape resizedCircle2 = circle2.resize(0);
    assertEquals(0 * circle2.area(), resizedCircle2.area(), 0.001);

    // Resize circle3 by a factor of 10 and verify the new area
    Shape resizedCircle3 = circle3.resize(10);
    assertEquals(10 * circle3.area(), resizedCircle3.area(), 0.001);
  }

  /**
   * Tests the {@code toString()} method of the Circle class.
   * Verifies the string representation of circle objects.
   */
  @Test
  void testToString() {
    // Check the expected formatted string output for each circle
    assertEquals("Circle: center (3.000,4.000) radius 5.000", circle1.toString());
    assertEquals("Circle: center (10.320,10.430) radius 10.000", circle2.toString());
    assertEquals("Circle: center (0.000,0.000) radius 20.000", circle3.toString());
  }
}
