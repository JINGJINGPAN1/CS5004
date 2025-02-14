import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for the ElementNode class, which represents a node in a linked list of books.
 * This class tests the functionality of the ElementNode class, including counting books,
 * calculating total prices, filtering books by publication year, adding books to the end of the list,
 * and generating string representations of the list.
 */
class ElementNodeTest {
  private ElementNode list1;
  private ElementNode list2;
  private Book book1;
  private Book book2;

  /**
   * Sets up the test environment before each test method is executed.
   * Initializes two Book objects and two ElementNode objects for testing.
   */
  @BeforeEach
  void setUp() {
    book1 = new Book("Learn Java the Easy Way", "Bryson Payne", 2017, 26.66f);
    book2 = new Book("JavaScript Crash Course", "Nick Morgan", 2024, 30.49f);
    list1 = new ElementNode(book1, new EmptyNode());
    list2 = new ElementNode(book2, new EmptyNode());
  }

  /**
   * Tests the count method of the ElementNode class.
   * Verifies that each list contains exactly one book.
   */
  @Test
  void count() {
    assertEquals(1, list1.count());
    assertEquals(1, list2.count());
  }

  /**
   * Tests the totalPrice method of the ElementNode class.
   * Verifies that the total price of each list matches the price of its single book.
   * Also tests the total price of a combined list.
   */
  @Test
  void totalPrice() {
    assertEquals(26.66f, list1.totalPrice());
    assertEquals(30.49f, list2.totalPrice());

    IListOfBooks list3 = new ElementNode(book1, list2);
    assertEquals(26.66f + 30.49f, list3.totalPrice());
  }

  /**
   * Tests the allBefore method of the ElementNode class.
   * Verifies that the method correctly filters books published before a given year.
   */
  @Test
  void allBefore() {
    IListOfBooks listAllBefore1 = list1.allBefore(2024);
    assertEquals(1, listAllBefore1.count());

    IListOfBooks listAllBefore2 = list2.allBefore(2023);
    assertEquals(0, listAllBefore2.count());

    IListOfBooks list3 = new ElementNode(book1, list2);
    IListOfBooks listAllBefore3 = list3.allBefore(2025);
    assertEquals(2, listAllBefore3.count());
  }

  /**
   * Tests the addAtEnd method of the ElementNode class.
   * Verifies that a book is correctly added to the end of the list and that the count is updated.
   */
  @Test
  void addAtEnd() {
    IListOfBooks newList1 = list1.addAtEnd(book2);
    assertEquals(2, newList1.count());

    IListOfBooks newList2 = list2.addAtEnd(book1);
    assertEquals(2, newList1.count());
  }

  /**
   * Tests the toString method of the ElementNode class.
   * Verifies that the string representation of the list matches the expected format.
   */
  @Test
  void testToString() {
    IListOfBooks newList1 = new ElementNode(book1, list2);
    String expected1 = "Title: Learn Java the Easy Way Author: Bryson Payne Year: 2017 Price: 26.66\n" +
        "Title: JavaScript Crash Course Author: Nick Morgan Year: 2024 Price: 30.49\n";
    assertEquals(expected1, newList1.toString());

    IListOfBooks newList2 = list2.addAtEnd(book1);
    String expected2 = "Title: JavaScript Crash Course Author: Nick Morgan Year: 2024 Price: 30.49\n" +
        "Title: Learn Java the Easy Way Author: Bryson Payne Year: 2017 Price: 26.66\n";
    assertEquals(expected2, newList2.toString());
  }
}