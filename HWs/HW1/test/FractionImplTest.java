import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FractionImplTest {

  private FractionImpl fraction1;
  private FractionImpl fraction2;
  private FractionImpl fraction3;
  private FractionImpl fraction4;


  @BeforeEach
  void setUp() {
    fraction1 = new FractionImpl(1, 2);
    fraction2 = new FractionImpl(-1, 2);
    fraction3 = new FractionImpl(1, -2);
    fraction4 = new FractionImpl(-1, -2);

  }

  @Test
  void constructorDenominatorZeroThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(1, 0));
    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(0, 0));
  }

  @Test
  void getNumerator() {
    assertEquals(1, fraction1.getNumerator());
    assertEquals(-1, fraction2.getNumerator());
    assertEquals(1, fraction3.getNumerator());
    assertEquals(-1, fraction4.getNumerator());
  }

  @Test
  void getDenominator() {
    assertEquals(2, fraction1.getDenominator());
    assertEquals(2, fraction2.getDenominator());
    assertEquals(-2, fraction3.getDenominator());
    assertEquals(-2, fraction4.getDenominator());
  }

  @Test
  void setNumerator() {
    fraction1.setNumerator(1);
    assertEquals(1, fraction1.getNumerator());

    fraction2.setNumerator(-1);
    assertEquals(-1, fraction2.getNumerator());
  }

  @Test
  void setDenominator() {
    fraction2.setDenominator(2);
    assertEquals(2, fraction2.getDenominator());

    fraction3.setDenominator(-2);
    assertEquals(2, fraction3.getDenominator());

    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(1, 2).setDenominator(0));

  }

  @Test
  void toDouble() {
    assertEquals(0.5, fraction1.toDouble());
    assertEquals(-0.5, fraction2.toDouble());
    assertEquals(-0.5, fraction3.toDouble());
    assertEquals(0.5, fraction4.toDouble());
  }

  @Test
  void reciprocal() {
    Fraction reciprocal1 = fraction1.reciprocal();
    assertEquals(2, reciprocal1.getNumerator());
    assertEquals(1, reciprocal1.getDenominator());

    Fraction reciprocal2 = fraction2.reciprocal();
    assertEquals(2, reciprocal2.getNumerator());
    assertEquals(-1, reciprocal2.getDenominator());

    Fraction reciprocal3 = fraction3.reciprocal();
    assertEquals(-2, reciprocal3.getNumerator());
    assertEquals(1, reciprocal3.getDenominator());

    Fraction reciprocal4 = fraction4.reciprocal();
    assertEquals(-2, reciprocal4.getNumerator());
    assertEquals(-1, reciprocal4.getDenominator());

    assertThrows(IllegalArgumentException.class, () -> new FractionImpl(0, 2).reciprocal());

  }

  @Test
  void add() {
    fraction1.setNumerator(1);
    fraction1.setDenominator(2);
    fraction2.setNumerator(-1);
    fraction2.setDenominator(2);
    Fraction result1 = fraction1.add(fraction2);
    assertEquals(0, result1.getNumerator());
    assertEquals(4, result1.getDenominator());

    fraction3.setNumerator(1);
    fraction3.setDenominator(-2);
    fraction4.setNumerator(-1);
    fraction4.setDenominator(-2);
    Fraction result2 = fraction3.add(fraction4);
    assertEquals(0, result2.getNumerator());
    assertEquals(4, result2.getDenominator());

    Fraction result3 = fraction1.add(fraction4);
    assertEquals(4, result3.getNumerator());
    assertEquals(4, result3.getDenominator());

    Fraction result4 = fraction2.add(fraction3);
    assertEquals(-4, result4.getNumerator());
    assertEquals(4, result4.getDenominator());

  }

  @Test
  void compareTo() {
    assertEquals(1, fraction1.compareTo(fraction2));
    assertEquals(-1, fraction2.compareTo(fraction4));
    assertEquals(0, fraction2.compareTo(fraction3));
    assertEquals(0, fraction1.compareTo(fraction4));

  }

  @Test
  void testToString() {
    assertEquals("1/2", fraction1.toString());
    assertEquals("-1/2", fraction2.toString());
    assertEquals("-1/2", fraction3.toString());
    assertEquals("1/2", fraction4.toString());
  }

  @Test
  void gcd() {
    assertEquals(1, fraction1.gcd(1, 2));
    assertEquals(-1, fraction2.gcd(-1, 2));
    assertEquals(1, fraction3.gcd(1, -2));
    assertEquals(-1, fraction4.gcd(-1, -2));
  }
}