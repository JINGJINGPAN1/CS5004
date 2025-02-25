import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoItemSchoolTest {
  ToDoItemSchool schoolTDI1;
  ToDoItemSchool schoolTDI2;

  @BeforeEach
  void setUp() {
    //  STUDENT TO IMPLEMENT AS NEEDED FOR THE TESTS BELOW
    schoolTDI1 = new ToDoItemSchool("homework", Importance.MEDIUM, 3);
    schoolTDI2 = new ToDoItemSchool("club", Importance.LOW, 1, false);
  }

  @Test
  void getRequired() {
    // STUDENT TO IMPLEMENT THIS TEST WITH 1 ASSERTION
    assertTrue(schoolTDI1.getRequired());
    assertFalse(schoolTDI2.getRequired());
  }

  @Test
  void testToString() {
    // STUDENT TO IMPLEMENT THIS TEST WITH 1 ASSERTION
    String expected1 = "ToDoItem: homework Importance: MEDIUM Urgency: 3 required: true";
    assertEquals(expected1, schoolTDI1.toString());

    String expected2 = "ToDoItem: club Importance: LOW Urgency: 1 required: false";
    assertEquals(expected2, schoolTDI2.toString());
  }
}