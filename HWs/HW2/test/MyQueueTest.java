import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code MyQueue} class.
 * This class contains tests for the basic functionality of the queue,
 * such as enqueueing, dequeueing, checking if the queue is empty,
 * and validating the behavior of methods like {@code peek}, {@code equals},
 * {@code hashCode}, and {@code toString}.
 */
class MyQueueTest {
  MyQueue<Integer> myQueue1;
  MyQueue<String> myQueue2;

  /**
   * Sets up the test environment by initializing two instances of MyQueue
   * before each test.
   */
  @BeforeEach
  void setUp() {
    myQueue1 = new MyQueue<>();
    myQueue2 = new MyQueue<>();
  }

  /**
   * Tests the {@code enqueue} and {@code dequeue} methods to ensure elements
   * are correctly added and removed from the queue.
   */
  @Test
  void testEnqueueDequeue() {
    myQueue1.enqueue(1);
    myQueue1.enqueue(2);
    assertEquals(1, myQueue1.dequeue());
    assertEquals(2, myQueue1.dequeue());

    myQueue2.enqueue("Apple");
    myQueue2.enqueue("Banana");
    assertEquals("Apple", myQueue2.dequeue());
    assertEquals("Banana", myQueue2.dequeue());
  }

  /**
   * Tests the {@code dequeue} method to ensure it throws a {@link NoSuchElementException}
   * when trying to dequeue from an empty queue.
   */
  @Test
  void testDequeueEmptyQueue() {
    assertThrows(NoSuchElementException.class, () -> myQueue1.dequeue());
    assertThrows(NoSuchElementException.class, () -> myQueue2.dequeue());
  }

  /**
   * Tests the {@code peek} method to verify that it throws a {@link NoSuchElementException}
   * when trying to peek at an empty queue.
   */
  @Test
  void testPeekEmptyQueue() {
    assertThrows(NoSuchElementException.class, () -> myQueue1.dequeue());
    assertThrows(NoSuchElementException.class, () -> myQueue2.dequeue());
  }

  /**
   * Tests the {@code peek} method to verify that it returns the front element of the queue
   * without removing it.
   */
  @Test
  void peek() {
    myQueue1.enqueue(1);
    myQueue1.enqueue(2);
    assertEquals(1, myQueue1.peek());

    myQueue2.enqueue("Apple");
    myQueue2.enqueue("Banana");
    assertEquals("Apple", myQueue2.peek());
  }

  /**
   * Tests the {@code isEmpty} method to check whether the queue is empty or not.
   */
  @Test
  void isEmpty() {
    assertTrue(myQueue1.isEmpty());
    myQueue1.enqueue(1);
    assertFalse(myQueue1.isEmpty());

    assertTrue(myQueue2.isEmpty());
    myQueue2.enqueue("Apple");
    assertFalse(myQueue2.isEmpty());
  }

  /**
   * Tests the {@code equals} method to ensure that two queues are considered equal
   * if they have the same elements in the same order.
   */
  @Test
  void testEquals() {
    myQueue1.enqueue(1);
    myQueue1.enqueue(2);
    myQueue2.enqueue("Apple");
    myQueue2.enqueue("Banana");
    assertFalse(myQueue1.equals(myQueue2));

    MyQueue<Integer> myQueue3 = new MyQueue<>();
    myQueue3.enqueue(1);
    myQueue3.enqueue(2);
    assertTrue(myQueue1.equals(myQueue3));

    MyQueue<String> myQueue4 = new MyQueue<>();
    myQueue4.enqueue("Apple");
    myQueue4.enqueue("Banana");
    assertTrue(myQueue2.equals(myQueue4));
  }

  /**
   * Tests the {@code hashCode} method to verify that two queues with the same elements
   * produce the same hash code, and different queues produce different hash codes.
   */
  @Test
  void testHashCode() {
    myQueue1.enqueue(1);
    myQueue1.enqueue(2);
    myQueue2.enqueue("Apple");
    myQueue2.enqueue("Banana");
    assertNotEquals(myQueue1.hashCode(), myQueue2.hashCode());

    MyQueue<Integer> myQueue3 = new MyQueue<>();
    myQueue3.enqueue(1);
    myQueue3.enqueue(2);
    assertEquals(myQueue1.hashCode(), myQueue3.hashCode());

    MyQueue<String> myQueue4 = new MyQueue<>();
    myQueue4.enqueue("Apple");
    myQueue4.enqueue("Banana");
    assertEquals(myQueue2.hashCode(), myQueue4.hashCode());
  }

  /**
   * Tests the {@code toString} method to ensure that it returns a correct string
   * representation of the queue's elements.
   */
  @Test
  void testToString() {
    myQueue1.enqueue(1);
    myQueue1.enqueue(2);
    String expected1 = "Queue: 1 2";
    assertEquals(expected1, myQueue1.toString());

    myQueue2.enqueue("Apple");
    myQueue2.enqueue("Banana");
    String expected2 = "Queue: Apple Banana";
    assertEquals(expected2, myQueue2.toString());

    assertNotEquals(myQueue1.toString(), myQueue2.toString());
  }
}
