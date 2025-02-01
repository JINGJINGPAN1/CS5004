import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TriangleTest {
  private Triangle tri1;
  private Triangle tri2;

  @BeforeEach
  void setUp() {
    tri1 = new Triangle(1, 1, 4,1, 1, 5);
    tri2 = new Triangle(2, 2, 7, 2, 4, 4);
  }

  @Test
  void testConstructor() {
    assertNotNull(tri1);
    assertNotNull(tri2);

    // Test invalid triangle construction (all points are the same)
    assertThrows(IllegalArgumentException.class, () -> new Triangle(0, 0, 0, 0, 0, 0));

    // Test invalid triangle construction (two points are the same)
    assertThrows(IllegalArgumentException.class, () -> new Triangle(0, 0, 0, 0, 1, 1));
  }

  @Test
  void distance() {
    Point2D point1 = new Point2D(0, 3);
    Point2D point2 = new Point2D(4, 0);

    assertEquals(5, tri1.distance(point1, point2));

    Point2D point3 = new Point2D(0, 9);
    Point2D point4 = new Point2D(12, 0);

    assertEquals(15, tri2.distance(point3, point4));

  }

  @Test
  void area() {
    assertEquals(6,tri1.area(),0.001);
    assertEquals(5,tri2.area(),0.001);

  }

  @Test
  void perimeter() {
    assertEquals(12,tri1.perimeter(),0.001);
    assertEquals(11.434,tri2.perimeter(),0.001);
  }

  @Test
  void resize() {
    Shape resizedTri1 = tri1.resize(12.5);
    assertEquals(12.5*tri1.area(),resizedTri1.area(),0.001);

    Shape resizedTri2 = tri2.resize(0.001);
    assertEquals(0.001*tri2.area(),resizedTri2.area(),0.001);
  }

  @Test
  void testToString() {
    assertEquals("Triangle: reference (1.000,1.000), vertexB (4.000,1.000), vertexC (1.000,5.000)", tri1.toString());
    assertEquals("Triangle: reference (2.000,2.000), vertexB (7.000,2.000), vertexC (4.000,4.000)", tri2.toString());
  }
}