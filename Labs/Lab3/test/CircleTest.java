import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleTest {
  private Circle circle1;
  private Circle circle2;
  private Circle circle3;

  @BeforeEach
  void setUp() {
    circle1 = new Circle(3,4,5);
    circle2 = new Circle(10.32,10.43,10);
    circle3 = new Circle(20);
  }

  @Test
  void area() {
    assertEquals(Math.PI*25,circle1.area(),0.001);
    assertEquals(Math.PI*100,circle2.area(),0.001);
    assertEquals(Math.PI*400,circle3.area(),0.001);
  }

  @Test
  void perimeter() {
    assertEquals(2*Math.PI*5,circle1.perimeter(),0.001);
    assertEquals(2*Math.PI*10,circle2.perimeter(),0.001);
    assertEquals(2*Math.PI*20,circle3.perimeter(),0.001);
  }

  @Test
  void resize() {
    Shape resizedCircle1 = circle1.resize(2.5);
    assertEquals(2.5*circle1.area(),resizedCircle1.area(),0.001);

    Shape resizedCircle2 = circle2.resize(0);
    assertEquals(0*circle2.area(),resizedCircle2.area(),0.001);

    Shape resizedCircle3 = circle3.resize(10);
    assertEquals(10*circle3.area(),resizedCircle3.area(),0.001);
  }

  @Test
  void testToString() {
    assertEquals("Circle: center (3.000,4.000) radius 5.000",circle1.toString());
    assertEquals("Circle: center (10.320,10.430) radius 10.000",circle2.toString());
    assertEquals("Circle: center (0.000,0.000) radius 20.000",circle3.toString());
  }
}