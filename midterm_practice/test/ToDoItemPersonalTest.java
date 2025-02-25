import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoItemPersonalTest {
  ToDoItemPersonal personTDI1;
  ToDoItemPersonal personTDI2;

  @BeforeEach
  void setUp() {
    personTDI1 = new ToDoItemPersonal("Cooking", Importance.MEDIUM, 2,45.0 );
    personTDI2 = new ToDoItemPersonal("Sports", Importance.HIGH, 3,60.0 );
  }

  @Test
  void getPrepTime() {
    assertEquals(45.0, personTDI1.getPrepTime());
    assertEquals(60.0, personTDI2.getPrepTime());
  }

  @Test
  void testToString() {
    String expected1 = "ToDoItem: Cooking Importance: MEDIUM Urgency: 2 PrepTime: 45.0";
    assertEquals(expected1, personTDI1.toString());
    String expected2 = "ToDoItem: Sports Importance: HIGH Urgency: 3 PrepTime: 60.0";
    assertEquals(expected2, personTDI2.toString());
  }

  @Test
  void testEquals() {
    assertFalse(personTDI1.equals(personTDI2));
    ToDoItemPersonal personTDI3 = new ToDoItemPersonal("Cooking", Importance.MEDIUM, 2,45.0 );
    assertTrue(personTDI1.equals(personTDI3));

  }

}