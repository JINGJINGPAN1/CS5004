/**
 * This class represents a Rectangle and implements all the operations mandated by the
 * {@link Shape} interface. The rectangle is defined by its lower-left corner (a reference point),
 * width, and height, and it provides methods to calculate area, perimeter, resize, and display
 * its properties as a string.
 */
public class Rectangle extends AbstractShape {

  // The width and height of the rectangle
  private double width, height;

  /**
   * Constructs a Rectangle object with the given location of its lower-left corner
   * and dimensions (width and height).
   *
   * @param x The x coordinate of the lower-left corner of this rectangle.
   * @param y The y coordinate of the lower-left corner of this rectangle.
   * @param width The width of the rectangle.
   * @param height The height of the rectangle.
   */
  public Rectangle(double x, double y, double width, double height) {
    super(new Point2D(x, y));  // Initialize the reference point (lower-left corner).
    this.width = width;        // Set the width of the rectangle.
    this.height = height;      // Set the height of the rectangle.
  }

  /**
   * Calculates the area of the rectangle.
   * The area is computed using the formula: Area = width * height.
   *
   * @return The area of the rectangle.
   */
  @Override
  public double area() {
    return this.width * this.height;  // Return the area of the rectangle.
  }

  /**
   * Calculates the perimeter of the rectangle.
   * The perimeter is computed using the formula: Perimeter = 2 * (width + height).
   *
   * @return The perimeter of the rectangle.
   */
  @Override
  public double perimeter() {
    return 2 * (this.width + this.height);  // Return the perimeter of the rectangle.
  }

  /**
   * Resizes the rectangle by a given factor.
   * The new width and height are scaled by the square root of the factor,
   * as resizing the area by a factor means that the dimensions (width and height)
   * change by the square root of that factor.
   *
   * @param factor The factor by which to resize the rectangle.
   * @return A new {@link Rectangle} object with the resized width and height.
   */
  @Override
  public Shape resize(double factor) {
    double sqrtFactor = Math.sqrt(factor);  // Scale by the square root of the factor.
    return new Rectangle(
        this.reference.getX(),            // Keep the same lower-left corner.
        this.reference.getY(),            // Keep the same lower-left corner.
        sqrtFactor * this.width,          // Resize the width.
        sqrtFactor * this.height);        // Resize the height.
  }

  /**
   * Returns a string representation of the Rectangle object, including its lower-left corner,
   * width, and height. The string is formatted with three decimal places for the coordinates,
   * width, and height.
   *
   * @return A string representing the Rectangle in the format:
   *         "Rectangle: LL corner (x.xxxx, y.yyyy) width w.w, height h.h"
   */
  @Override
  public String toString() {
    // Format and return a string with the rectangle's corner, width, and height.
    return String.format("Rectangle: LL corner (%.3f,%.3f) width %.3f height %.3f",
        this.reference.getX(), this.reference.getY(), this.width, this.height);
  }

}
