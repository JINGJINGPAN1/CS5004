import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Cat class, covering all getter, setter, and utility methods.
 * It uses JUnit 5 for unit testing.
 */
class CatTest {
  private Cat cat1;
  private Cat cat2;
  private Cat cat3;
  private Person person1;
  private Person person2;

  /**
   * Sets up test objects before each test method is run.
   * Initializes Cat and Person objects with known states.
   */
  @BeforeEach
  void setUp() {
    person1 = new Person("Mary", "Wang", 1965);
    person2 = new Person("Lucy", "Zhou", 1999);

    cat1 = new Cat("cat1", 3, Color.BLACK, person1);
    cat2 = new Cat("cat2", 1, Color.YELLOW, person2);
    cat3 = new Cat("cat1", 3, Color.BLACK, person1);
  }

  /**
   * Tests the getName method of the Cat class.
   */
  @Test
  void getName() {
    assertEquals("cat1", cat1.getName());
    assertEquals("cat2", cat2.getName());
  }

  /**
   * Tests the getAge method of the Cat class.
   */
  @Test
  void getAge() {
    assertEquals(3, cat1.getAge());
    assertEquals(1, cat2.getAge());
  }

  /**
   * Tests the setAge method by updating the age and verifying the change.
   */
  @Test
  void setAge() {
    cat1.setAge(5);
    assertEquals(5, cat1.getAge());

    cat2.setAge(8);
    assertEquals(8, cat2.getAge());
  }

  /**
   * Tests the getColor method to ensure the correct Color enum is returned.
   */
  @Test
  void getColor() {
    assertEquals(Color.BLACK, cat1.getColor());
    assertEquals(Color.YELLOW, cat2.getColor());
  }

  /**
   * Tests the getOwner method to validate the correct Person object is returned.
   */
  @Test
  void getOwner() {
    assertEquals(person1, cat1.getOwner());
    assertEquals(person2, cat2.getOwner());
  }

  /**
   * Tests the setOwner method by updating the owner and verifying the change.
   */
  @Test
  void setOwner() {
    cat1.setOwner(person2);
    assertEquals(person2, cat1.getOwner());

    cat2.setOwner(person1);
    assertEquals(person1, cat2.getOwner());
  }

  /**
   * Tests the toString method to ensure a correctly formatted string is produced.
   */
  @Test
  void testToString() {
    String expected1 = "Cat Details:\n"
        + "Name: cat1\n"
        + "Age: 3\n"
        + "Color: Neutral\n"
        + "Owner: Mary Wang";
    assertEquals(expected1, cat1.toString());

    String expected2 = "Cat Details:\n"
        + "Name: cat2\n"
        + "Age: 1\n"
        + "Color: Primary\n"
        + "Owner: Lucy Zhou";
    assertEquals(expected2, cat2.toString());
  }

  /**
   * Tests the equals method for proper equality comparisons between Cat objects.
   */
  @Test
  void testEquals() {
    assertFalse(cat1.equals(cat2));
    assertTrue(cat1.equals(cat3));
  }

  /**
   * Tests the hashCode method to verify consistent hash codes for equal objects.
   */
  @Test
  void testHashCode() {
    assertNotEquals(cat1.hashCode(), cat2.hashCode());
    assertEquals(cat1.hashCode(), cat3.hashCode());
  }
}
