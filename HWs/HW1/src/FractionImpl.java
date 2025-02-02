public class FractionImpl implements Fraction {

  private int numerator;
  private int denominator;

  /**
   * Constructs a fraction with the specified numerator and denominator. The denominator must not be
   * zero. If the denominator is negative, the numerator's sign is adjusted to ensure the
   * denominator is positive.
   *
   * @param numerator   the numerator of the fraction
   * @param denominator the denominator of the fraction (must not be zero)
   * @throws IllegalArgumentException if the denominator is zero
   */
  public FractionImpl(int numerator, int denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
    if (denominator == 0) {
      throw new IllegalArgumentException("Cannot divide by zero");
    }
  }

  /**
   * returns the numerator of the fraction
   *
   * @return the numerator
   */
  @Override
  public int getNumerator() {
    return numerator;
  }

  /**
   * returns the denominator of the fraction
   *
   * @return the denominator
   */
  @Override
  public int getDenominator() {
    return denominator;
  }

  /**
   * sets the numerator of the fraction to the specific value
   *
   * @param numerator of the new numerator
   */
  @Override
  public void setNumerator(int numerator) {
    this.numerator = numerator;

  }

  /**
   * Sets the denominator of the fraction to the specified value. The denominator must be non-zero.
   * If the specified denominator is negative, the sign of the numerator is adjusted to preserve the
   * fraction's value, and the denominator is stored as positive.
   *
   * @param denominator the new denominator (must not be zero)
   * @throws IllegalArgumentException if the denominator is zero
   */
  @Override
  public void setDenominator(int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Cannot divide by zero");
    }

    if (denominator < 0) {
      this.numerator *= -1;
      this.denominator *= -1;
    } else {
      this.denominator = denominator;
    }

  }

  /**
   * Returns the decimal value of the fraction.
   *
   * @return the decimal equivalent of the fraction
   */
  @Override
  public double toDouble() {
    return (double) numerator / denominator;
  }

  /**
   * Returns the reciprocal of this fraction as a new Fraction instance. The reciprocal is obtained
   * by swapping the numerator and denominator. If the current numerator is zero, this method throws
   * an ArithmeticException.
   *
   * @return the reciprocal fraction
   * @throws ArithmeticException if the numerator of this fraction is zero
   */
  @Override
  public Fraction reciprocal() {
    if (numerator == 0) {
      throw new IllegalArgumentException("Cannot compute reciprocal of zero.");
    }
    return new FractionImpl(denominator, numerator);
  }

  /**
   * Adds the specified fraction to this fraction and returns the result as a new Fraction
   * instance.
   *
   * @param other the fraction to add to this fraction
   * @return a new Fraction representing the sum
   */
  @Override
  public Fraction add(Fraction other) {
    int otherNumerator = other.getNumerator();
    int otherDenominator = other.getDenominator();
    int newNumerator = numerator * otherDenominator + denominator * otherNumerator;
    int newDenominator = denominator * otherDenominator;
    return new FractionImpl(newNumerator, newDenominator);
  }

  /**
   * Compares this fraction to the specified fraction. Returns a negative integer, zero, or a
   * positive integer if this fraction is less than, equal to, or greater than the specified
   * fraction.
   *
   * @param other the fraction to compare to
   * @return a negative, zero, or positive integer as this fraction is less than, equal to, or
   * greater than the other
   */
  @Override
  public int compareTo(Fraction other) {
    return Double.compare(this.toDouble(), other.toDouble());
  }

  /**
   * Returns a string representation of the fraction in its simplified form.
   * The fraction is simplified by dividing both the numerator and denominator
   * by their greatest common divisor (GCD). If the denominator is negative,
   * the sign is moved to the numerator to ensure the denominator is always positive.
   *
   * @return A string representation of the simplified fraction in the format "numerator/denominator".
   */
  public String toString() {
    int gcd = gcd(numerator, denominator);
    int simplifiedNumerator = numerator / gcd;
    int simplifiedDenominator = denominator / gcd;
    if (simplifiedDenominator < 0) {
      simplifiedDenominator *= -1;
      simplifiedNumerator *= -1;
    }
    return String.format("%d/%d", simplifiedNumerator, simplifiedDenominator);

  }

  /**
   * Computes the greatest common divisor (GCD) of two integers using the Euclidean algorithm.
   * The GCD is the largest positive integer that divides both numbers without leaving a remainder.
   *
   * @param a The first integer.
   * @param b The second integer.
   * @return The greatest common divisor of the two integers.
   */

  public int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}
