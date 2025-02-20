import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Rectangle} class.
 * Tests methods including area calculation, perimeter calculation, resizing, and string representation.
 */
class RectangleTest {

  // Rectangle instances for testing
  private Rectangle rec1;
  private Rectangle rec2;

  /**
   * Initializes test rectangles before each test.
   * Sets up rectangles with specific coordinates, widths, and heights for testing.
   */
  @BeforeEach
  void setUp() {
    // Rectangle with lower-left corner at (0, 0), width 2, and height 3
    rec1 = new Rectangle(0, 0, 2, 3);

    // Rectangle with lower-left corner at (5, 5), width 5, and height 5 (a square)
    rec2 = new Rectangle(5, 5, 5, 5);
  }

  /**
   * Tests the {@code area()} method of the Rectangle class.
   * Verifies that the calculated area matches the expected value.
   */
  @Test
  void area() {
    // Area of a rectangle is width * height
    assertEquals(6, rec1.area());
    assertEquals(25, rec2.area());
  }

  /**
   * Tests the {@code perimeter()} method of the Rectangle class.
   * Verifies that the calculated perimeter matches the expected value.
   */
  @Test
  void perimeter() {
    // Perimeter of a rectangle is 2 * (width + height)
    assertEquals(10, rec1.perimeter());
    assertEquals(20, rec2.perimeter());
  }

  /**
   * Tests the {@code resize()} method of the Rectangle class.
   * Ensures that resizing the rectangle correctly scales its area.
   */
  @Test
  void resize() {
    // Resize rec1 by a factor of 12.5 and verify the new area
    Shape resizedRec1 = rec1.resize(12.5);
    assertEquals(12.5 * rec1.area(), resizedRec1.area(), 0.001);

    // Resize rec2 by a factor of 0.001 and verify the new area
    Shape resizedRec2 = rec2.resize(0.001);
    assertEquals(0.001 * rec2.area(), resizedRec2.area(), 0.001);
  }

  /**
   * Tests the {@code toString()} method of the Rectangle class.
   * Verifies the string representation of rectangle objects.
   */
  @Test
  void testToString() {
    // Check the expected formatted string output for each rectangle
    assertEquals(
        "Rectangle: LL corner (0.000,0.000) width 2.000 height 3.000",
        rec1.toString()
    );
    assertEquals(
        "Rectangle: LL corner (5.000,5.000) width 5.000 height 5.000",
        rec2.toString()
    );
  }
}
