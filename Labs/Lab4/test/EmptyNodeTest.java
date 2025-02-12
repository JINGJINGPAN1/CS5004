import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmptyNodeTest {
  private EmptyNode emptyNode;
  private Book book;

  @BeforeEach
  void setUp() {
    emptyNode = new EmptyNode();
    book = new Book("Learn Java the Easy Way", "Bryson Payne", 2017, 26.66f);
  }

  @Test
  void count() {
    assertEquals(0, emptyNode.count());
  }

  @Test
  void totalPrice() {
    assertEquals(0, emptyNode.totalPrice());
  }

  @Test
  void allBefore() {
    IListOfBooks result = emptyNode.allBefore(2024);
    assertEquals(0, result.count());
  }

  @Test
  void addAtEnd() {
    IListOfBooks newList = emptyNode.addAtEnd(book);
    assertEquals(1, newList.count());
  }


  @Test
  void testToString() {
    assertEquals("", emptyNode.toString());
  }
}