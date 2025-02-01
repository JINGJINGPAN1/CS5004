import static java.lang.Math.sqrt;

public class Triangle extends AbstractShape{
  private Point2D vertexB;
  private Point2D vertexC;
  private double sideAB;
  private double sideBC;
  private double sideAC;


  public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
    super(new Point2D(x1, y1));
    vertexB = new Point2D(x2, y2);
    vertexC = new Point2D(x3, y3);

    // Validate that all points are distinct
    if((x1 == x2 && y1 == y2) || (x1 == x3 && y1 == y3) || (x2 == x3 && y2 == y3)) {
      throw new IllegalArgumentException("All vertices must be distinct. ");
    }
  }

  public double distance(Point2D point1, Point2D point2) {
    double xDiff = point1.getX() - point2.getX();
    double yDiff = point1.getY() - point2.getY();
    return sqrt(xDiff*xDiff + yDiff*yDiff);
  }


  @Override
  public double area() {
    double semiPerimeter = perimeter() / 2;
    return sqrt(semiPerimeter * (semiPerimeter - sideAB) * (semiPerimeter - sideAC) * (semiPerimeter - sideBC));
  }

  @Override
  public double perimeter() {
    sideAB = distance(reference, vertexB);
    sideAC = distance(reference, vertexC);
    sideBC = distance(vertexB, vertexC);
    return sideAB + sideAC + sideBC;


  }

  @Override
  public Shape resize(double factor) {
    double sqrtFactor = Math.sqrt(factor);
    Point2D newVertexB = new Point2D(
        reference.getX() + (vertexB.getX() - reference.getX()) * sqrtFactor,
        reference.getY() + (vertexB.getY() - reference.getY()) * sqrtFactor);
    Point2D newVertexC = new Point2D(
        reference.getX() + (vertexC.getX() - reference.getX()) * sqrtFactor,
        reference.getY() + (vertexC.getY() - reference.getY()) * sqrtFactor);
    return new Triangle(reference.getX(), reference.getY(), newVertexB.getX(), newVertexB.getY(), newVertexC.getX(), newVertexC.getY());
  }

  @Override
  public String toString() {
    return String.format("Triangle: reference (%.3f,%.3f), vertexB (%.3f,%.3f), vertexC (%.3f,%.3f)",
        reference.getX(), reference.getY(), vertexB.getX(), vertexB.getY(), vertexC.getX(), vertexC.getY());
  }

}
