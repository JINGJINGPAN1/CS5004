import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatTest {
  private Cat cat1;
  private Cat cat2;
  private Person person1;
  private Person person2;

  @BeforeEach
  void setUp() {
    person1 = new Person("Mary", "Wang", 1965);
    person2 = new Person("Lucy", "Zhou", 1999);

    cat1 = new Cat("cat1", 3, Color.BLACK, person1);
    cat2 = new Cat("cat2", 1, Color.YELLOW, person2);

  }

  @Test
  void getName() {
    assertEquals("cat1", cat1.getName());
    assertEquals("cat2", cat2.getName());
  }

  @Test
  void getAge() {
    assertEquals(3, cat1.getAge());
    assertEquals(1, cat2.getAge());

  }

  @Test
  void setAge() {
    cat1.setAge(5);
    assertEquals(5, cat1.getAge());

    cat2.setAge(8);
    assertEquals(8, cat2.getAge());
  }

  @Test
  void getColor() {
    assertEquals(Color.BLACK, cat1.getColor());
    assertEquals(Color.YELLOW, cat2.getColor());
  }

  @Test
  void getOwner() {
    assertEquals(person1, cat1.getOwner());
    assertEquals(person2, cat2.getOwner());
  }

  @Test
  void setOwner() {
    cat1.setOwner(person2);
    assertEquals(person2, cat1.getOwner());

    cat2.setOwner(person1);
    assertEquals(person1, cat2.getOwner());
  }

  @Test
  void testToString() {
    String expected1 = "Cat Details:\n"
        + "Name: cat1\n"
        + "Age: 3\n"
        + "Color: Neutral\n"
        + "Type: Mary Wang";
    assertEquals(expected1, cat1.toString());

    String expected2 = "Cat Details:\n"
        + "Name: cat2\n"
        + "Age: 1\n"
        + "Color: Primary\n"
        + "Type: Lucy Zhou";
    assertEquals(expected2, cat2.toString());

  }

  @Test
  void testEquals() {
    assertFalse(cat1.equals(cat2));

    Cat cat3 = new Cat("cat1", 3, Color.BLACK, person1);
    assertTrue(cat1.equals(cat3));
  }
}