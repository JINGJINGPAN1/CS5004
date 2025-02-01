import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Point2DTest {
  private Point2D point1;
  private Point2D point2;
  private Point2D point3;

  @BeforeEach
  void setUp() {
    point1 = new Point2D(3, 4);
    point2 = new Point2D(-3, 4);
    point3 = new Point2D(-3, -4);

  }

  @Test
  void distToOrigin() {
    assertEquals(5, point1.distToOrigin());
    assertEquals(5, point2.distToOrigin());
    assertEquals(5, point3.distToOrigin());

  }

  @Test
  void getX() {
    assertEquals(3, point1.getX());
    assertEquals(-3, point2.getX());
    assertEquals(-3, point3.getX());
  }

  @Test
  void getY() {
    assertEquals(4, point1.getY());
    assertEquals(4, point2.getY());
    assertEquals(-4, point3.getY());
  }
}