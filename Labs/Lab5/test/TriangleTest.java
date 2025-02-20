import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit5 test class for testing the {@link Triangle} class.
 * This test suite covers the construction, area and perimeter calculations,
 * resizing functionality, distance calculations, and string representation of Triangle objects.
 */
class TriangleTest {

  private Triangle tri1; // Right triangle with base 3 and height 4
  private Triangle tri2; // Isosceles triangle with specific vertices

  /**
   * Initializes test triangle objects before each test.
   * Creates two triangles with predefined vertices.
   */
  @BeforeEach
  void setUp() {
    tri1 = new Triangle(1, 1, 4, 1, 1, 5); // Right triangle
    tri2 = new Triangle(2, 2, 7, 2, 4, 4); // Isosceles triangle
  }

  /**
   * Tests the constructor of the {@link Triangle} class.
   * Ensures valid triangles are constructed and invalid input throws the appropriate exception.
   */
  @Test
  void testConstructor() {
    // Valid triangle construction
    assertNotNull(tri1);
    assertNotNull(tri2);

    // Test invalid triangle construction (all points are the same)
    assertThrows(IllegalArgumentException.class, () ->
            new Triangle(0, 0, 0, 0, 0, 0),
        "Expected IllegalArgumentException for degenerate triangle with all points the same."
    );

    // Test invalid triangle construction (two points are the same)
    assertThrows(IllegalArgumentException.class, () ->
            new Triangle(0, 0, 0, 0, 1, 1),
        "Expected IllegalArgumentException for degenerate triangle with two identical points."
    );
  }

  /**
   * Tests the distance calculation between two points using the Triangle class's distance method.
   * Uses specific points to verify the Euclidean distance calculation.
   */
  @Test
  void distance() {
    Point2D point1 = new Point2D(0, 3);
    Point2D point2 = new Point2D(4, 0);

    // Expected distance is 5 (3-4-5 triangle)
    assertEquals(5, tri1.distance(point1, point2), 0.001);

    Point2D point3 = new Point2D(0, 9);
    Point2D point4 = new Point2D(12, 0);

    // Expected distance is 15 (similarity to 3-4-5 triangle scaled by 3)
    assertEquals(15, tri2.distance(point3, point4), 0.001);
  }

  /**
   * Tests the area calculation of triangles.
   * Verifies the correctness of the area method using known values.
   */
  @Test
  void area() {
    assertEquals(6, tri1.area(), 0.001, "Area of tri1 should be 6.0");
    assertEquals(5, tri2.area(), 0.001, "Area of tri2 should be 5.0");
  }

  /**
   * Tests the perimeter calculation of triangles.
   * Verifies the correctness of the perimeter method using known values.
   */
  @Test
  void perimeter() {
    assertEquals(12, tri1.perimeter(), 0.001, "Perimeter of tri1 should be 12.0");
    assertEquals(11.434, tri2.perimeter(), 0.001, "Perimeter of tri2 should be approximately 11.434");
  }

  /**
   * Tests the resize method for triangles.
   * Ensures resizing scales the area correctly.
   */
  @Test
  void resize() {
    // Test resizing with a scale factor of 12.5
    Shape resizedTri1 = tri1.resize(12.5);
    assertEquals(12.5 * tri1.area(), resizedTri1.area(), 0.001,
        "Resized area of tri1 should scale by 12.5");

    // Test resizing with a very small scale factor of 0.001
    Shape resizedTri2 = tri2.resize(0.001);
    assertEquals(0.001 * tri2.area(), resizedTri2.area(), 0.001,
        "Resized area of tri2 should scale by 0.001");
  }

  /**
   * Tests the string representation of triangles using the toString method.
   * Verifies the output format and correctness of the information displayed.
   */
  @Test
  void testToString() {
    assertEquals("Triangle: reference (1.000,1.000), vertexB (4.000,1.000), vertexC (1.000,5.000)",
        tri1.toString(), "String representation of tri1 is incorrect.");

    assertEquals("Triangle: reference (2.000,2.000), vertexB (7.000,2.000), vertexC (4.000,4.000)",
        tri2.toString(), "String representation of tri2 is incorrect.");
  }
}
