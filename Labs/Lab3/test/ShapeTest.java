import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit5 version of ShapeTest
 */
public class ShapeTest {
  Shape circle1,circle2,circle3,rect1,rect2, tri1,tri2;

  @BeforeEach
  void setUp() {
    circle1 = new Circle(3,4,5);
    circle2 = new Circle(10.32,10.43,10);
    circle3 = new Circle(20);
    rect1 = new Rectangle(5,6,2.5,2);
    rect2 = new Rectangle(2,3,10,10);
    tri1 = new Triangle(1, 1, 4,1, 1, 5);
    tri2 = new Triangle(2, 2, 7, 2, 4, 4);
  }

  @Test
  void distanceFromOrigin() {
    // Tested by Point2D so skipped here
  }

  /**
   * Tests whether the area methods work correctly for all shapes
   */
  @Test
  void area() {
    assertEquals(Math.PI*25,circle1.area(),0.001);
    assertEquals(Math.PI*100,circle2.area(),0.001);
    assertEquals(Math.PI*400,circle3.area(),0.001);
    assertEquals(5,rect1.area(),0.001);
    assertEquals(100,rect2.area(),0.001);
    assertEquals(6,tri1.area(),0.001);
    assertEquals(5,tri2.area(),0.001);
  }

  /**
   * Tests whether the perimeter methods work correctly for all shapes
   */
  @Test
  void perimeter() {
    assertEquals(2*Math.PI*5,circle1.perimeter(),0.001);
    assertEquals(2*Math.PI*10,circle2.perimeter(),0.001);
    assertEquals(2*Math.PI*20,circle3.perimeter(),0.001);
    assertEquals(9,rect1.perimeter(),0.001);
    assertEquals(40,rect2.perimeter(),0.001);
    assertEquals(12,tri1.perimeter(),0.001);
    assertEquals(11.434,tri2.perimeter(),0.001);

  }

  @Test
  void resize() {
    Shape resizedCircle1,resizedCircle2,resizedCircle3,resizedRect1,resizedRect2, resizedTri1, resizedTri2;
    resizedCircle1 = circle1.resize(2.5);
    resizedCircle2 = circle2.resize(0);
    resizedCircle3 = circle3.resize(10);
    resizedRect1 = rect1.resize(12.5);
    resizedRect2 = rect2.resize(0.001);
    resizedTri1 = tri1.resize(12.5);
    resizedTri2 = tri2.resize(0.001);
    assertEquals(2.5*circle1.area(),resizedCircle1.area(),0.001);
    assertEquals(0*circle2.area(),resizedCircle2.area(),0.001);
    assertEquals(10*circle3.area(),resizedCircle3.area(),0.001);
    assertEquals(12.5*rect1.area(),resizedRect1.area(),0.001);
    assertEquals(0.001*rect2.area(),resizedRect2.area(),0.001);
    assertEquals(12.5*tri1.area(),resizedTri1.area(),0.001);
    assertEquals(0.001*tri2.area(),resizedTri2.area(),0.001);
  }

  /**
   * Tests whether objects have been created with the correct numbers or not.
   * It does this by using their toString methods
   */
  @Test
  public void testObjectData() {
    assertEquals("Circle: center (3.000,4.000) radius 5.000",circle1.toString
        ());
    assertEquals("Circle: center (10.320,10.430) radius 10.000",circle2.toString
        ());
    assertEquals("Circle: center (0.000,0.000) radius 20.000",circle3
        .toString
            ());
    assertEquals("Rectangle: LL corner (5.000,6.000) width 2.500 height 2.000",
        rect1.toString());
    assertEquals("Rectangle: LL corner (2.000,3.000) width 10.000 height 10" +
        ".000",rect2
        .toString());
    assertEquals("Triangle: reference (1.000,1.000), vertexB (4.000,1.000), vertexC (1.000,5.000)", tri1.toString());
    assertEquals("Triangle: reference (2.000,2.000), vertexB (7.000,2.000), vertexC (4.000,4.000)", tri2.toString());
  }
}