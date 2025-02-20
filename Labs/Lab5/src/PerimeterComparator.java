import java.util.Comparator;

/**
 * This class provides a comparator for comparing shapes based on their perimeter.
 */
public class PerimeterComparator implements Comparator<Shape> {

  /**
   * Compares two shapes based on their perimeter.
   *
   * @param s1 the first shape to compare
   * @param s2 the second shape to compare
   * @return a negative integer, zero, or a positive integer as the first shape's perimeter
   *         is less than, equal to, or greater than the second shape's perimeter
   */
  @Override
  public int compare(Shape s1, Shape s2) {
    double perimeter1 = s1.perimeter();
    double perimeter2 = s2.perimeter();

    if (perimeter1 < perimeter2) {
      return -1;
    } else if (perimeter1 > perimeter2) {
      return 1;
    } else {
      return 0;
    }
  }
}
