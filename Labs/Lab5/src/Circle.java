/**
 * This class represents a Circle and implements all the operations mandated by the
 * {@link Shape} interface. The circle is defined by its center (a reference point)
 * and a radius, and it provides methods to calculate area, perimeter, resize, and
 * display its properties as a string.
 */
public class Circle extends AbstractShape {

  // The radius of the circle
  private double radius;

  /**
   * Constructs a Circle object using the given center and radius.
   * This constructor is used when specifying both the center coordinates and the radius.
   *
   * @param x The x coordinate of the center of this circle.
   * @param y The y coordinate of the center of this circle.
   * @param radius The radius of the circle.
   */
  public Circle(double x, double y, double radius) {
    super(new Point2D(x, y));  // Create the center point of the circle.
    this.radius = radius;  // Set the radius of the circle.
  }

  /**
   * Constructs a Circle object with the given radius, centered at (0, 0).
   * This constructor is used when only the radius is provided, and the center is assumed to be at the origin.
   *
   * @param radius The radius of the circle.
   */
  public Circle(double radius) {
    super(new Point2D(0, 0));  // Default center point at (0, 0).
    this.radius = radius;  // Set the radius of the circle.
  }

  /**
   * Calculates the area of the circle.
   * The area is computed using the formula: Area = π * radius².
   *
   * @return The area of the circle.
   */
  @Override
  public double area() {
    return Math.PI * radius * radius;  // Area of the circle using the radius.
  }

  /**
   * Calculates the perimeter (circumference) of the circle.
   * The perimeter is computed using the formula: Perimeter = 2 * π * radius.
   *
   * @return The perimeter of the circle.
   */
  @Override
  public double perimeter() {
    return 2 * Math.PI * radius;  // Perimeter of the circle using the radius.
  }

  /**
   * Resizes the circle by a given factor.
   * The new radius is scaled by the square root of the factor, as resizing the area by a factor
   * means that the radius changes by the square root of that factor.
   *
   * @param factor The factor by which to resize the circle.
   * @return A new {@link Circle} object with the resized radius.
   */
  @Override
  public Shape resize(double factor) {
    // Create a new Circle with the resized radius, keeping the same center.
    return new Circle(reference.getX(), reference.getY(), Math.sqrt(factor) * radius);
  }

  /**
   * Returns a string representation of the Circle object, including its center coordinates and radius.
   * The string is formatted with three decimal places for the coordinates and radius.
   *
   * @return A string representing the Circle in the format:
   *         "Circle: center (x.xxxx, y.yyyy) radius r.r"
   */
  @Override
  public String toString() {
    // Format and return a string with the circle's center and radius.
    return String.format("Circle: center (%.3f,%.3f) radius %.3f",
        this.reference.getX(), this.reference.getY(), this.radius);
  }
}
