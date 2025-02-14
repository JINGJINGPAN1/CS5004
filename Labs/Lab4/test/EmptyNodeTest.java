import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for the EmptyNode class, which represents an empty node in a linked list of books.
 * This class tests the functionality of the EmptyNode class, including counting books,
 * calculating total prices, filtering books by publication year, adding books to the end of the list,
 * and generating string representations of the list.
 */
class EmptyNodeTest {
  private EmptyNode emptyNode;
  private Book book;
  private Book anotherBook;

  /**
   * Sets up the test environment before each test method is executed.
   * Initializes an EmptyNode object and a Book object for testing.
   */
  @BeforeEach
  void setUp() {
    emptyNode = new EmptyNode();
    book = new Book("Learn Java the Easy Way", "Bryson Payne", 2017, 26.66f);
    anotherBook = new Book("JavaScript Crash Course", "Nick Morgan", 2024, 30.49f);
  }

  /**
   * Tests the count method of the EmptyNode class.
   * Verifies that an empty node returns a count of 0.
   */
  @Test
  void count() {
    // Test 1: Verify that an empty node has a count of 0
    assertEquals(0, emptyNode.count());

    // Test 2: Verify that the count remains 0 even after attempting to add a book (since EmptyNode is immutable)
    emptyNode.addAtEnd(book);
    assertEquals(0, emptyNode.count());
  }

  /**
   * Tests the totalPrice method of the EmptyNode class.
   * Verifies that an empty node returns a total price of 0.
   */
  @Test
  void totalPrice() {
    // Test 1: Verify that an empty node has a total price of 0
    assertEquals(0, emptyNode.totalPrice());

    // Test 2: Verify that the total price remains 0 even after attempting to add a book (since EmptyNode is immutable)
    emptyNode.addAtEnd(book);
    assertEquals(0, emptyNode.totalPrice());
  }

  /**
   * Tests the allBefore method of the EmptyNode class.
   * Verifies that an empty node returns an empty list when filtering books by publication year.
   */
  @Test
  void allBefore() {
    // Test 1: Verify that filtering an empty node returns an empty list
    IListOfBooks result = emptyNode.allBefore(2024);
    assertEquals(0, result.count());

    // Test 2: Verify that filtering with a different year still returns an empty list
    IListOfBooks result2 = emptyNode.allBefore(2000);
    assertEquals(0, result2.count());
  }

  /**
   * Tests the addAtEnd method of the EmptyNode class.
   * Verifies that adding a book to an empty node creates a new list with one book.
   */
  @Test
  void addAtEnd() {
    // Test 1: Verify that adding a book to an empty node creates a list with one book
    IListOfBooks newList = emptyNode.addAtEnd(book);
    assertEquals(1, newList.count());

    // Test 2: Verify that adding a second book creates a list with two books
    IListOfBooks newList2 = newList.addAtEnd(anotherBook);
    assertEquals(2, newList2.count());
  }

  /**
   * Tests the toString method of the EmptyNode class.
   * Verifies that an empty node returns an empty string as its representation.
   */
  @Test
  void testToString() {
    // Test 1: Verify that an empty node returns an empty string
    assertEquals("", emptyNode.toString());

    // Test 2: Verify that the string representation of a list with one book matches the expected format
    IListOfBooks newList = emptyNode.addAtEnd(book);
    String expected = "Title: Learn Java the Easy Way Author: Bryson Payne Year: 2017 Price: 26.66\n";
    assertEquals(expected, newList.toString());
  }
}