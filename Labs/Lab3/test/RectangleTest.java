import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RectangleTest {
  Rectangle rec1;
  Rectangle rec2;

  @BeforeEach
  void setUp() {
    rec1 = new Rectangle(0, 0, 2, 3);
    rec2 = new Rectangle(5, 5, 5, 5);
  }

  @Test
  void area() {
    assertEquals(6, rec1.area());
    assertEquals(25, rec2.area());

  }

  @Test
  void perimeter() {
    assertEquals(10, rec1.perimeter());
    assertEquals(20, rec2.perimeter());
  }

  @Test
  void resize() {
    Shape resizedRec1 = rec1.resize(12.5);
    assertEquals(12.5*rec1.area(),resizedRec1.area(),0.001);

    Shape resizedRec2 = rec2.resize(0.001);
    assertEquals(0.001*rec2.area(),resizedRec2.area(),0.001);
  }

  @Test
  void testToString() {
    assertEquals("Rectangle: LL corner (0.000,0.000) width 2.000 height 3.000",
        rec1.toString());
    assertEquals("Rectangle: LL corner (5.000,5.000) width 5.000 height 5.000",
        rec2.toString());
  }
}