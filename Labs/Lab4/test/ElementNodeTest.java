import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElementNodeTest {
  private ElementNode list1;
  private ElementNode list2;
  private Book book1;
  private Book book2;

  @BeforeEach
  void setUp() {
    book1 = new Book("Learn Java the Easy Way", "Bryson Payne", 2017, 26.66f);
    book2 = new Book("JavaScript Crash Course", "Nick Morgan", 2024, 30.49f);
    list1 = new ElementNode(book1, new EmptyNode());
    list2 = new ElementNode(book2, new EmptyNode());
  }

  @Test
  void count() {
    assertEquals(1, list1.count());
    assertEquals(1, list2.count());

  }

  @Test
  void totalPrice() {
    assertEquals(26.66f, list1.totalPrice());
    assertEquals(30.49f, list2.totalPrice());

    IListOfBooks list3 = new ElementNode(book1, list2);
    assertEquals(26.66f + 30.49f, list3.totalPrice());
  }

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

  @Test
  void addAtEnd() {
    IListOfBooks newList1 = list1.addAtEnd(book2);
    assertEquals(2, newList1.count());

    IListOfBooks newList2 = list2.addAtEnd(book1);
    assertEquals(2, newList1.count());

  }

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