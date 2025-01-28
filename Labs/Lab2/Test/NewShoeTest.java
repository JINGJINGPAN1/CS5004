import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewShoeTest {
  private NewShoe newShoe1;
  private NewShoe newShoe2;

  @BeforeEach
  public void setUp() {
    newShoe1 = new NewShoe(7, Type.SNEAKER, Brand.NIKE, Color.GREEN);
    newShoe2 = new NewShoe(10, Type.CASUAL, Brand.PUMA, Color.BLACK);
  }

  @Test
  void constructorThrowsWhenNikeAndDress() {
    assertThrows(IllegalArgumentException.class, () -> {
      new NewShoe(9, Type.DRESS, Brand.NIKE, Color.BLACK);
    });
  }

  @Test
  void constructorAcceptsNonNikeDress() {
    assertDoesNotThrow(() -> {
      new NewShoe(7, Type.SNEAKER, Brand.ADIDAS, Color.BLACK);
    });
  }

  @Test
  void getShoeSize() {
    assertEquals(7, newShoe1.getShoeSize());
    assertEquals(10, newShoe2.getShoeSize());
  }

  @Test
  void getShoeType() {
    assertEquals(Type.SNEAKER, newShoe1.getShoeType());
    assertEquals(Type.CASUAL, newShoe2.getShoeType());
  }

  @Test
  void getBrand() {
    assertEquals(Brand.NIKE, newShoe1.getBrand());
    assertEquals(Brand.PUMA, newShoe2.getBrand());
  }

  @Test
  void getColor() {
    assertEquals(Color.GREEN, newShoe1.getColor());
    assertEquals(Color.BLACK, newShoe2.getColor());
  }

  @Test
  void testToString() {
    String expected = "Shoe Details:\n" +
        "Size: 7\n" +
        "Brand: Nike\n" +
        "Color: Primary\n" +
        "Type: Sneaker";

    assertEquals(expected, newShoe1.toString());


    String expected2 = "Shoe Details:\n" +
        "Size: 10\n" +
        "Brand: Puma\n" +
        "Color: Neutral\n" +
        "Type: Casual";

    assertEquals(expected2, newShoe2.toString());
  }


}