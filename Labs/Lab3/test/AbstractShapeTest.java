import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractShapeTest {
  private Circle circle;
  private Rectangle rectangle;
  private Triangle triangle;

  @BeforeEach
  void setUp() {
    circle = new Circle(3, 4, 5);
    rectangle = new Rectangle(0, 0, 2, 3);
    triangle = new Triangle(1, 1, 4,1, 1, 5);
  }

  @Test
  void distanceFromOrigin() {
    assertEquals(5, circle.distanceFromOrigin(), 0.001);
    assertEquals(0, rectangle.distanceFromOrigin(), 0.001);
    assertEquals(1.414, triangle.distanceFromOrigin(), 0.001);
  }

  @Test
  void compareTo() {
    assertEquals(1, circle.compareTo(rectangle), 0.001);
    assertEquals(-1, triangle.compareTo(circle), 0.001);
    assertEquals(0, rectangle.compareTo(triangle), 0.001);
  }
}