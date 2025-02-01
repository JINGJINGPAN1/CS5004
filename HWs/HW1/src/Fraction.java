public interface Fraction extends Comparable<Fraction> {

  /**
   * returns the numerator of the fraction
   *
   * @return the numerator
   */
  int getNumerator();

  /**
   * returns the denominator of the fraction
   *
   * @return the denominator
   */

  int getDenominator();

  /**
   * sets the numerator of the fraction to the specific value
   *
   * @param numerator of the new numerator
   */
  void setNumerator(int numerator);

  /**
   * Sets the denominator of the fraction to the specified value. The denominator must be non-zero.
   * If the specified denominator is negative, the sign of the numerator is adjusted to preserve the
   * fraction's value, and the denominator is stored as positive.
   *
   * @param denominator the new denominator (must not be zero)
   * @throws IllegalArgumentException if the denominator is zero
   */
  void setDenominator(int denominator);

  /**
   * Returns the decimal value of the fraction.
   *
   * @return the decimal equivalent of the fraction
   */
  double toDouble();

  /**
   * Returns the reciprocal of this fraction as a new Fraction instance. The reciprocal is obtained by
   * swapping the numerator and denominator. If the current numerator is zero, this method throws an
   * ArithmeticException.
   *
   * @return the reciprocal fraction
   * @throws ArithmeticException if the numerator of this fraction is zero
   */
  Fraction reciprocal();

  /**
   * Adds the specified fraction to this fraction and returns the result as a new Fraction instance.
   *
   * @param other the fraction to add to this fraction
   * @return a new Fraction representing the sum
   */
  Fraction add(Fraction other);

  /**
   * Compares this fraction to the specified fraction. Returns a negative integer, zero, or a positive
   * integer if this fraction is less than, equal to, or greater than the specified fraction.
   *
   * @param other the fraction to compare to
   * @return a negative, zero, or positive integer as this fraction is less than, equal to, or greater than the other
   */
  @Override
  int compareTo(Fraction other);

}
