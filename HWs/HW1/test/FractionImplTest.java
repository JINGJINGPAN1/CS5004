// Import necessary JUnit 5 assertions for testing
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the FractionImpl class.
 * This class contains unit tests to verify the functionality of the FractionImpl implementation.
 */
class FractionImplTest {

  // Declare FractionImpl objects to be used in the tests
  private FractionImpl fraction1;
  private FractionImpl fraction2;
  private FractionImpl fraction3;
  private FractionImpl fraction4;

  /**
   * Setup method that runs before each test.
   * Initializes FractionImpl objects with different numerator and denominator combinations
   * to test various scenarios.
   */
  @BeforeEach
  void setUp() {
    fraction1 = new FractionImpl(1, 2);   // Positive numerator and denominator
    fraction2 = new FractionImpl(-1, 2);  // Negative numerator, positive denominator
    fraction3 = new FractionImpl(1, -2);  // Positive numerator, negative denominator
    fraction4 = new FractionImpl(-1, -2); // Negative numerator and denominator
  }

  /**
   * Test case to verify that the constructor throws an IllegalArgumentException
   * when the denominator is zero.
   */
  @Test
  void constructorDenominatorZeroThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(1, 0)); // Non-zero numerator, zero denominator
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(0, 0)); // Zero numerator and denominator
  }

  /**
   * Test case to verify the getNumerator() method.
   * Ensures that the numerator is correctly returned for different FractionImpl objects.
   */
  @Test
  void getNumerator() {
    assertEquals(1, fraction1.getNumerator());  // Positive numerator
    assertEquals(-1, fraction2.getNumerator()); // Negative numerator
    assertEquals(1, fraction3.getNumerator());  // Positive numerator (sign handled in denominator)
    assertEquals(-1, fraction4.getNumerator()); // Negative numerator (sign handled in denominator)
  }

  /**
   * Test case to verify the getDenominator() method.
   * Ensures that the denominator is correctly returned for different FractionImpl objects.
   */
  @Test
  void getDenominator() {
    assertEquals(2, fraction1.getDenominator());  // Positive denominator
    assertEquals(2, fraction2.getDenominator());  // Positive denominator
    assertEquals(-2, fraction3.getDenominator()); // Negative denominator
    assertEquals(-2, fraction4.getDenominator()); // Negative denominator
  }

  /**
   * Test case to verify the setNumerator() method.
   * Ensures that the numerator is correctly updated for FractionImpl objects.
   */
  @Test
  void setNumerator() {
    fraction1.setNumerator(1);  // Set positive numerator
    assertEquals(1, fraction1.getNumerator());

    fraction2.setNumerator(-1); // Set negative numerator
    assertEquals(-1, fraction2.getNumerator());
  }

  /**
   * Test case to verify the setDenominator() method.
   * Ensures that the denominator is correctly updated for FractionImpl objects.
   * Also verifies that setting the denominator to zero throws an IllegalArgumentException.
   */
  @Test
  void setDenominator() {
    fraction2.setDenominator(2);  // Set positive denominator
    assertEquals(2, fraction2.getDenominator());

    fraction3.setDenominator(-2); // Set negative denominator
    assertEquals(2, fraction3.getDenominator()); // Denominator should be positive after normalization

    // Verify that setting denominator to zero throws an exception
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(1, 2).setDenominator(0));
  }

  /**
   * Test case to verify the toDouble() method.
   * Ensures that the fraction is correctly converted to a double value.
   */
  @Test
  void toDouble() {
    assertEquals(0.5, fraction1.toDouble());  // 1/2 = 0.5
    assertEquals(-0.5, fraction2.toDouble()); // -1/2 = -0.5
    assertEquals(-0.5, fraction3.toDouble()); // 1/-2 = -0.5
    assertEquals(0.5, fraction4.toDouble());  // -1/-2 = 0.5
  }

  /**
   * Test case to verify the reciprocal() method.
   * Ensures that the reciprocal of the fraction is correctly calculated.
   * Also verifies that attempting to take the reciprocal of zero throws an IllegalArgumentException.
   */
  @Test
  void reciprocal() {
    Fraction reciprocal1 = fraction1.reciprocal(); // Reciprocal of 1/2 is 2/1
    assertEquals(2, reciprocal1.getNumerator());
    assertEquals(1, reciprocal1.getDenominator());

    Fraction reciprocal2 = fraction2.reciprocal(); // Reciprocal of -1/2 is 2/-1
    assertEquals(2, reciprocal2.getNumerator());
    assertEquals(-1, reciprocal2.getDenominator());

    Fraction reciprocal3 = fraction3.reciprocal(); // Reciprocal of 1/-2 is -2/1
    assertEquals(-2, reciprocal3.getNumerator());
    assertEquals(1, reciprocal3.getDenominator());

    Fraction reciprocal4 = fraction4.reciprocal(); // Reciprocal of -1/-2 is -2/-1
    assertEquals(-2, reciprocal4.getNumerator());
    assertEquals(-1, reciprocal4.getDenominator());

    // Verify that taking the reciprocal of zero throws an exception
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(0, 2).reciprocal());
  }

  /**
   * Test case to verify the add() method.
   * Ensures that two fractions are correctly added together.
   */
  @Test
  void add() {
    fraction1.setNumerator(1);
    fraction1.setDenominator(2);
    fraction2.setNumerator(-1);
    fraction2.setDenominator(2);
    Fraction result1 = fraction1.add(fraction2); // 1/2 + (-1/2) = 0/4
    assertEquals(0, result1.getNumerator());
    assertEquals(4, result1.getDenominator());

    fraction3.setNumerator(1);
    fraction3.setDenominator(-2);
    fraction4.setNumerator(-1);
    fraction4.setDenominator(-2);
    Fraction result2 = fraction3.add(fraction4); // 1/-2 + (-1/-2) = 0/4
    assertEquals(0, result2.getNumerator());
    assertEquals(4, result2.getDenominator());

    Fraction result3 = fraction1.add(fraction4); // 1/2 + (-1/-2) = 4/4
    assertEquals(4, result3.getNumerator());
    assertEquals(4, result3.getDenominator());

    Fraction result4 = fraction2.add(fraction3); // -1/2 + 1/-2 = -4/4
    assertEquals(-4, result4.getNumerator());
    assertEquals(4, result4.getDenominator());
  }

  /**
   * Test case to verify the compareTo() method.
   * Ensures that fractions are correctly compared based on their values.
   */
  @Test
  void compareTo() {
    assertEquals(1, fraction1.compareTo(fraction2)); // 1/2 > -1/2
    assertEquals(-1, fraction2.compareTo(fraction4)); // -1/2 < 1/2
    assertEquals(0, fraction2.compareTo(fraction3)); // -1/2 == 1/-2
    assertEquals(0, fraction1.compareTo(fraction4)); // 1/2 == -1/-2
  }

  /**
   * Test case to verify the toString() method.
   * Ensures that the fraction is correctly represented as a string.
   */
  @Test
  void testToString() {
    assertEquals("1/2", fraction1.toString());  // 1/2 as string
    assertEquals("-1/2", fraction2.toString()); // -1/2 as string
    assertEquals("-1/2", fraction3.toString()); // 1/-2 as string (normalized to -1/2)
    assertEquals("1/2", fraction4.toString());  // -1/-2 as string (normalized to 1/2)
  }

  /**
   * Test case to verify the gcd() method.
   * Ensures that the greatest common divisor (GCD) is correctly calculated.
   */
  @Test
  void gcd() {
    assertEquals(1, fraction1.gcd(1, 2));   // GCD of 1 and 2 is 1
    assertEquals(-1, fraction2.gcd(-1, 2));  // GCD of -1 and 2 is -1 (sign depends on implementation)
    assertEquals(1, fraction3.gcd(1, -2));   // GCD of 1 and -2 is 1 (sign depends on implementation)
    assertEquals(-1, fraction4.gcd(-1, -2)); // GCD of -1 and -2 is -1 (sign depends on implementation)
  }
}