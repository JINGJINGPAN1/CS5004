import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the PerimeterComparator class.
 * This class tests the compare method, which compares the perimeters of different shapes.
 */
class PerimeterComparatorTest {

  // Shape instances for testing
  Shape circle;
  Shape rect;
  Shape tri;

  // Comparator instance
  PerimeterComparator perimeterComparator;

  /**
   * Sets up test objects before each test.
   * Initializes a circle, rectangle, triangle, and the perimeter comparator.
   */
  @BeforeEach
  void setUp() {
    // Circle with radius 5 at (3,4)
    circle = new Circle(3, 4, 5);

    // Rectangle with top-left corner (5,6) and width 2, height 4
    rect = new Rectangle(5, 6, 2, 4);

    // Triangle with vertices at (1,1), (4,1), and (1,5)
    tri = new Triangle(1, 1, 4, 1, 1, 5);

    // Initialize the perimeter comparator
    perimeterComparator = new PerimeterComparator();
  }

  /**
   * Tests the compare method of PerimeterComparator.
   * Compares the perimeters of circle, rectangle, and triangle, and verifies the results.
   */
  @Test
  void compare() {
    // Compare circle and rectangle perimeters; expect circle > rect => 1
    int res1 = perimeterComparator.compare(circle, rect);
    assertEquals(1, res1);

    // Compare rectangle and triangle perimeters; expect rect == tri => 0
    int res2 = perimeterComparator.compare(rect, tri);
    assertEquals(0, res2);

    // Compare triangle and circle perimeters; expect tri < circle => -1
    int res3 = perimeterComparator.compare(tri, circle);
    assertEquals(-1, res3);
  }
}
