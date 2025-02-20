import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit5 test class for testing various Shape implementations including
 * Circle, Rectangle, and Triangle.
 * This class tests methods such as area calculation, perimeter calculation,
 * resizing shapes, and verifying object data through string representations.
 */
public class ShapeTest {

  // Shape instances for testing
  private Shape circle1, circle2, circle3;
  private Shape rect1, rect2;
  private Shape tri1, tri2;

  /**
   * Initializes test shape objects before each test.
   * Creates circles, rectangles, and triangles with predefined dimensions and positions.
   */
  @BeforeEach
  void setUp() {
    // Circles with different radii and positions
    circle1 = new Circle(3, 4, 5); // Circle at (3,4) with radius 5
    circle2 = new Circle(10.32, 10.43, 10); // Circle at (10.32,10.43) with radius 10
    circle3 = new Circle(20); // Circle at origin with radius 20

    // Rectangles with specified lower-left corners, widths, and heights
    rect1 = new Rectangle(5, 6, 2.5, 2); // Rectangle at (5,6) width 2.5, height 2
    rect2 = new Rectangle(2, 3, 10, 10); // Square at (2,3) width and height 10

    // Triangles with specified vertices
    tri1 = new Triangle(1, 1, 4, 1, 1, 5); // Right triangle
    tri2 = new Triangle(2, 2, 7, 2, 4, 4); // Isosceles triangle
  }

  /**
   * Tests the area calculation for all shapes.
   * Verifies that the area method returns the expected values for circles, rectangles, and triangles.
   */
  @Test
  void area() {
    // Circle area = π * r^2
    assertEquals(Math.PI * 25, circle1.area(), 0.001);
    assertEquals(Math.PI * 100, circle2.area(), 0.001);
    assertEquals(Math.PI * 400, circle3.area(), 0.001);

    // Rectangle area = width * height
    assertEquals(5, rect1.area(), 0.001);
    assertEquals(100, rect2.area(), 0.001);

    // Triangle area using standard formula (base * height) / 2
    assertEquals(6, tri1.area(), 0.001);
    assertEquals(5, tri2.area(), 0.001);
  }

  /**
   * Tests the perimeter calculation for all shapes.
   * Verifies that the perimeter method returns the correct perimeter for circles, rectangles, and triangles.
   */
  @Test
  void perimeter() {
    // Circle perimeter = 2 * π * r
    assertEquals(2 * Math.PI * 5, circle1.perimeter(), 0.001);
    assertEquals(2 * Math.PI * 10, circle2.perimeter(), 0.001);
    assertEquals(2 * Math.PI * 20, circle3.perimeter(), 0.001);

    // Rectangle perimeter = 2 * (width + height)
    assertEquals(9, rect1.perimeter(), 0.001);
    assertEquals(40, rect2.perimeter(), 0.001);

    // Triangle perimeter (sum of all sides)
    assertEquals(12, tri1.perimeter(), 0.001);
    assertEquals(11.434, tri2.perimeter(), 0.001);
  }

  /**
   * Tests the resize functionality for all shapes.
   * Checks if resizing the shapes properly scales their area.
   */
  @Test
  void resize() {
    // Resized shape instances
    Shape resizedCircle1 = circle1.resize(2.5);
    Shape resizedCircle2 = circle2.resize(0);
    Shape resizedCircle3 = circle3.resize(10);

    Shape resizedRect1 = rect1.resize(12.5);
    Shape resizedRect2 = rect2.resize(0.001);

    Shape resizedTri1 = tri1.resize(12.5);
    Shape resizedTri2 = tri2.resize(0.001);

    // Verify resized areas for all shapes
    assertEquals(2.5 * circle1.area(), resizedCircle1.area(), 0.001);
    assertEquals(0 * circle2.area(), resizedCircle2.area(), 0.001);
    assertEquals(10 * circle3.area(), resizedCircle3.area(), 0.001);

    assertEquals(12.5 * rect1.area(), resizedRect1.area(), 0.001);
    assertEquals(0.001 * rect2.area(), resizedRect2.area(), 0.001);

    assertEquals(12.5 * tri1.area(), resizedTri1.area(), 0.001);
    assertEquals(0.001 * tri2.area(), resizedTri2.area(), 0.001);
  }

  /**
   * Tests the string representation of all shape objects.
   * Verifies that the toString method returns the expected formatted output.
   */
  @Test
  public void testObjectData() {
    // Expected string representations for circles
    assertEquals("Circle: center (3.000,4.000) radius 5.000", circle1.toString());
    assertEquals("Circle: center (10.320,10.430) radius 10.000", circle2.toString());
    assertEquals("Circle: center (0.000,0.000) radius 20.000", circle3.toString());

    // Expected string representations for rectangles
    assertEquals("Rectangle: LL corner (5.000,6.000) width 2.500 height 2.000", rect1.toString());
    assertEquals("Rectangle: LL corner (2.000,3.000) width 10.000 height 10.000", rect2.toString());

    // Expected string representations for triangles
    assertEquals("Triangle: reference (1.000,1.000), vertexB (4.000,1.000), vertexC (1.000,5.000)", tri1.toString());
    assertEquals("Triangle: reference (2.000,2.000), vertexB (7.000,2.000), vertexC (4.000,4.000)", tri2.toString());
  }
}
