import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A generic queue implementation using an ArrayList as the underlying data structure.
 *
 * This class provides basic queue operations such as enqueue, dequeue, peek,
 * and checking if the queue is empty.
 * It also overrides equals(Object), hashCode(), and toString() methods
 * for better comparison and string representation.
 * @param <T> the type of elements held in this queue
 */
public class MyQueue<T> {
  private ArrayList<T> queue;

  /**
   * Constructs an empty queue.
   */
  public MyQueue() {
    queue = new ArrayList<>();
  }

  /**
   * Adds an element to the end of the queue.
   * @param e the new element to be added to the end of the queue
   */
  public void enqueue(T e) {
    queue.add(e);
  }

  /**
   * Removes and returns the front element of the queue.
   * @return the value at the head of the queue.
   */
  public T dequeue() {
    if (queue.isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return queue.remove(0);
  }

  /**
   * Gets the front element of the queue.
   * @return the front element of the queue.
   */
  public T peek() {
    if (queue.isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return queue.get(0);
  }

  /**
   * Checks if the queue is empty.
   * @return returns true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  /**
   * Compares this queue with another object for equality.
   * @param o the object to compare with.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    MyQueue<?> myQueue = (MyQueue<?>) o;
    return Objects.equals(queue, myQueue.queue);
  }

  /**
   * Return the hash code value for this queue.
   * @return the hash code of this queue.
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(queue);
  }

  /**
   * Returns a string representation of the queue.
   * @return a formatted string with the queue's details.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Queue:");
    for (T e : queue) {
      sb.append(" ");
      sb.append(e);
    }
    return sb.toString();
  }
}


//Question A: What undesirable behavior could occur if there are frequent dequeue operations?
//Answer: Frequent dequeue operations might result in lead to inefficiencies, especially with
//        frequent dequeue operations. Each dequeue requires shifting all remaining elements left,
//        resulting in O(n) time complexity, which degrades performance for large queues.
//        Additionally, the underlying array does not shrink after removals, leading to wasted memory,
//        and frequent resizing during size fluctuations can further impact performance.

//Question B: Propose (in 1 English sentence) an approach to resolving Question A.
//Answer: To resolve the inefficiencies of frequent dequeue operations, we can use LinkedList.
//        This is because a linked list allows for constant time O(1) removal of elements
//        from the front, eliminating the need to shift elements.
