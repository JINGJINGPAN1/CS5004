import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class ToDoListTest {
  ToDoList t1;
  ToDoList t2;

  ToDoItem personTDI1;
  ToDoItem personTDI2;
  ToDoItem personTDI3;

  ToDoItem schoolTDI1;
  ToDoItem schoolTDI2;
  ToDoItem schoolTDI3;

  @BeforeEach
  void setUp() {
    t1 = new ToDoList();
    t2 = new ToDoList();

    personTDI1 = new ToDoItemPersonal("Cooking", Importance.MEDIUM, 2,45.0 );
    personTDI2 = new ToDoItemPersonal("Sports", Importance.HIGH, 3,60.0 );
    personTDI3 = new ToDoItemPersonal("Singing", Importance.LOW, 1,15.0 );

    schoolTDI1 = new ToDoItemSchool("homework", Importance.HIGH, 5);
    schoolTDI2 = new ToDoItemSchool("Reading", Importance.MEDIUM, 3);
    schoolTDI3 = new ToDoItemSchool("club", Importance.LOW, 1, false);

  }

  @Test
  void addItem() {
    t1.addItem(personTDI1);
    t1.addItem(personTDI2);
    String expected = "{\n"
        + "ToDoItem: Cooking Importance: MEDIUM Urgency: 2 PrepTime: 45.0\n"
        + "ToDoItem: Sports Importance: HIGH Urgency: 3 PrepTime: 60.0\n"
        + "\n"
        + "}";
    assertEquals(expected, t1.toString());

    t2.addItem(schoolTDI1);
    t2.addItem(personTDI3);
    String expected2 = "{\n"
        + "ToDoItem: homework Importance: HIGH Urgency: 5 required: true\n"
        + "ToDoItem: Singing Importance: LOW Urgency: 1 PrepTime: 15.0\n"
        + "\n"
        + "}";
    assertEquals(expected2, t2.toString());


  }

  @Test
  void getItem() {
    t1.addItem(personTDI1);
    assertEquals(personTDI1, t1.getItem(0));

    t1.addItem(personTDI2);
    assertEquals(personTDI2, t1.getItem(1));

    t2.addItem(schoolTDI1);
    assertEquals(schoolTDI1, t2.getItem(0));
    t2.addItem(schoolTDI2);
    assertEquals(schoolTDI2, t2.getItem(1));

    assertThrows(IndexOutOfBoundsException.class, () -> t1.getItem(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> t1.getItem(2));
    assertThrows(IndexOutOfBoundsException.class, () -> t2.getItem(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> t2.getItem(100));
  }


  @Test
  void remove() {
    t1.addItem(personTDI1);
    t1.addItem(personTDI2);

    assertTrue(t1.remove(personTDI1));
    assertTrue(t1.remove(personTDI2));
    assertFalse(t1.remove(personTDI3));

    t2.addItem(schoolTDI1);
    t2.addItem(schoolTDI2);

    assertTrue(t2.remove(schoolTDI2));
    assertFalse(t2.remove(personTDI3));


  }

  @Test
  void size() {
    t1.addItem(personTDI1);
    t1.addItem(personTDI2);
    t1.addItem(personTDI3);
    assertEquals(3, t1.size());

    t2.addItem(schoolTDI1);
    t2.addItem(schoolTDI2);

    assertEquals(2, t2.size());
  }

  @Test
  void sortByUrgency() {
    t1.addItem(personTDI1);
    t1.addItem(personTDI2);
    t1.addItem(personTDI3);
    t1.sortByUrgency();
    assertEquals(personTDI3, t1.getItem(0));
    assertEquals(personTDI1, t1.getItem(1));
    assertEquals(personTDI2, t1.getItem(2));

    t2.addItem(schoolTDI1);
    t2.addItem(schoolTDI2);
    t2.addItem(schoolTDI3);
    t2.sortByUrgency();
    assertEquals(schoolTDI3, t2.getItem(0));
    assertEquals(schoolTDI2, t2.getItem(1));
    assertEquals(schoolTDI1, t2.getItem(2));
  }

  @Test
  void sortByImportance() {
    t1.addItem(personTDI1);
    t1.addItem(personTDI2);
    t1.addItem(personTDI3);
    t1.sortByImportance();

    assertEquals(personTDI3, t1.getItem(0));
    assertEquals(personTDI1, t1.getItem(1));
    assertEquals(personTDI2, t1.getItem(2));

    t2.addItem(schoolTDI1);
    t2.addItem(schoolTDI2);
    t2.addItem(schoolTDI3);
    t2.sortByImportance();
    assertEquals(schoolTDI3, t2.getItem(0));
    assertEquals(schoolTDI2, t2.getItem(1));
    assertEquals(schoolTDI1, t2.getItem(2));
  }

  @Test
  void testToString() {
    t1.addItem(personTDI1);
    t1.addItem(personTDI2);
    t1.addItem(personTDI3);
    String expected = "{\n"
        + "ToDoItem: Cooking Importance: MEDIUM Urgency: 2 PrepTime: 45.0\n"
        + "ToDoItem: Sports Importance: HIGH Urgency: 3 PrepTime: 60.0\n"
        + "ToDoItem: Singing Importance: LOW Urgency: 1 PrepTime: 15.0\n"
        + "\n"
        + "}";
    assertEquals(expected, t1.toString());


    t2.addItem(schoolTDI1);
    t2.addItem(schoolTDI2);
    t2.addItem(schoolTDI3);
    String expected2 = "{\n"
        + "ToDoItem: homework Importance: HIGH Urgency: 5 required: true\n"
        + "ToDoItem: Reading Importance: MEDIUM Urgency: 3 required: true\n"
        + "ToDoItem: club Importance: LOW Urgency: 1 required: false\n"
        + "\n"
        + "}";
    assertEquals(expected2, t2.toString());
  }
}