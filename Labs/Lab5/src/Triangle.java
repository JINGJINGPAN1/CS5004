import static java.lang.Math.sqrt;

/**
 * This class represents a triangle, implementing all the operations mandated by the
 * {@link Shape} interface. The triangle is defined by three vertices (reference, vertexB, and vertexC),
 * and it provides methods to calculate the area, perimeter, resize the triangle, and display its properties as a string.
 */
public class Triangle extends AbstractShape {

  // Vertices of the triangle
  private Point2D vertexB;
  private Point2D vertexC;

  // Length of the sides of the triangle
  private double sideAB;
  private double sideBC;
  private double sideAC;

  /**
   * Constructs a Triangle object using the given coordinates for its three vertices.
   *
   * @param x1 The x coordinate of the reference point (vertexA).
   * @param y1 The y coordinate of the reference point (vertexA).
   * @param x2 The x coordinate of vertexB.
   * @param y2 The y coordinate of vertexB.
   * @param x3 The x coordinate of vertexC.
   * @param y3 The y coordinate of vertexC.
   * @throws IllegalArgumentException If any two vertices are the same, as the triangle cannot have degenerate sides.
   */
  public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
    super(new Point2D(x1, y1));  // Initialize the reference point (vertexA).
    vertexB = new Point2D(x2, y2);  // Set the coordinates of vertexB.
    vertexC = new Point2D(x3, y3);  // Set the coordinates of vertexC.

    // Validate that all points are distinct
    if((x1 == x2 && y1 == y2) || (x1 == x3 && y1 == y3) || (x2 == x3 && y2 == y3)) {
      throw new IllegalArgumentException("All vertices must be distinct.");
    }
  }

  /**
   * Calculates the distance between two points using the distance formula.
   *
   * @param point1 The first point.
   * @param point2 The second point.
   * @return The distance between the two points.
   */
  public double distance(Point2D point1, Point2D point2) {
    double xDiff = point1.getX() - point2.getX();
    double yDiff = point1.getY() - point2.getY();
    return sqrt(xDiff * xDiff + yDiff * yDiff);  // Return the Euclidean distance between the points.
  }

  /**
   * Calculates the area of the triangle using Heron's formula.
   * The area is calculated by first computing the semi-perimeter and then applying the formula:
   * Area = √(s * (s - sideAB) * (s - sideAC) * (s - sideBC)), where s is the semi-perimeter.
   *
   * @return The area of the triangle.
   */
  @Override
  public double area() {
    double semiPerimeter = perimeter() / 2;  // Calculate the semi-perimeter.
    return sqrt(semiPerimeter * (semiPerimeter - sideAB) * (semiPerimeter - sideAC) * (semiPerimeter - sideBC));  // Apply Heron's formula.
  }

  /**
   * Calculates the perimeter of the triangle by summing the lengths of its sides.
   * The sides are calculated by finding the distances between the vertices:
   * Perimeter = sideAB + sideAC + sideBC.
   *
   * @return The perimeter of the triangle.
   */
  @Override
  public double perimeter() {
    sideAB = distance(reference, vertexB);  // Calculate side AB.
    sideAC = distance(reference, vertexC);  // Calculate side AC.
    sideBC = distance(vertexB, vertexC);   // Calculate side BC.
    return sideAB + sideAC + sideBC;  // Return the perimeter.
  }

  /**
   * Resizes the triangle by a given factor. This method scales the positions of vertexB and vertexC
   * relative to the reference vertex (vertexA), which remains unchanged. The scaling is done by multiplying
   * the distance from the reference point by the square root of the factor.
   *
   * @param factor The factor by which to resize the triangle. The factor should be greater than 0.
   * @return A new {@link Triangle} object with the resized dimensions.
   */
  @Override
  public Shape resize(double factor) {
    double sqrtFactor = sqrt(factor);  // Scale by the square root of the factor.
    Point2D newVertexB = new Point2D(
        reference.getX() + (vertexB.getX() - reference.getX()) * sqrtFactor,
        reference.getY() + (vertexB.getY() - reference.getY()) * sqrtFactor);  // Scale vertexB.
    Point2D newVertexC = new Point2D(
        reference.getX() + (vertexC.getX() - reference.getX()) * sqrtFactor,
        reference.getY() + (vertexC.getY() - reference.getY()) * sqrtFactor);  // Scale vertexC.
    return new Triangle(reference.getX(), reference.getY(), newVertexB.getX(), newVertexB.getY(), newVertexC.getX(), newVertexC.getY());  // Return the resized triangle.
  }

  /**
   * Returns a string representation of the Triangle object, including the coordinates of its reference
   * point (vertexA), vertexB, and vertexC. The coordinates are formatted to three decimal places.
   *
   * @return A string representing the Triangle in the format:
   *         "Triangle: reference (x.xxxx, y.yyyy), vertexB (x.xxxx, y.yyyy), vertexC (x.xxxx, y.yyyy)"
   */
  @Override
  public String toString() {
    // Format and return a string with the triangle's reference and vertex coordinates.
    return String.format("Triangle: reference (%.3f,%.3f), vertexB (%.3f,%.3f), vertexC (%.3f,%.3f)",
        reference.getX(), reference.getY(), vertexB.getX(), vertexB.getY(), vertexC.getX(), vertexC.getY());
  }
}
