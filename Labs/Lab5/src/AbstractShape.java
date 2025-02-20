/**
 * Abstract class representing a general shape.
 * This class implements the {@link Shape} interface and provides common functionality
 * that can be shared across all concrete shape classes (e.g., Circle, Rectangle, Triangle).
 * Specifically, it includes the distance calculation from the origin and a comparison method.
 * Concrete shape classes need to implement their specific area and perimeter calculations.
 */
public abstract class AbstractShape implements Shape {

  protected Point2D reference;

  /**
   * Constructs an AbstractShape object with a specified reference point.
   * This reference point typically represents a key position of the shape (e.g., center, corner).
   *
   * @param reference The reference point for this shape.
   */
  public AbstractShape(Point2D reference) {
    this.reference = reference;
  }

  /**
   * Calculates the distance of the shape's reference point from the origin (0, 0).
   * This method delegates the calculation to the Point2D class.
   *
   * @return The distance from the origin to the shape's reference point.
   */
  @Override
  public double distanceFromOrigin() {
    return reference.distToOrigin();
  }

  /**
   * Compares this shape with another shape based on their areas.
   * This method is used for sorting or ordering shapes by their areas.
   * It returns a negative integer, zero, or a positive integer depending on whether
   * the area of this shape is smaller, equal, or greater than the other shape's area.
   *
   * @param s The shape to compare this shape to.
   * @return A negative integer if this shape's area is smaller than the other's,
   *         a positive integer if this shape's area is larger, or zero if they are equal.
   */
  @Override
  public int compareTo(Shape s) {
    // Get the areas of this shape and the other shape for comparison.
    double areaThis = this.area();
    double areaOther = s.area();

    // Return comparison based on areas
    if (areaThis < areaOther) {
      return -1;  // this shape's area is smaller
    } else if (areaOther < areaThis) {
      return 1;   // this shape's area is larger
    } else {
      return 0;   // areas are equal
    }
  }

  // answer to task 1:
  // The compareTo method in AbstractShape would need to be modified to compare perimeters
  // instead of areas.
  // If AbstractShape did not exist, 3 files would have required modification.
  // Specifically, each concrete shape class (Circle, Rectangle, Triangle)
  // need to implement its own compareTo method, leading to code duplication.
}
