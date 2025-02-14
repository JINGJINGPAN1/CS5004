/**
 * This class represents an empty node in a list of books.
 * This class implements the IListOfBooks interface and serves as the base case for a recursive list structure.
 * An empty node contains no data and represents the end of the list.
 */
public class EmptyNode implements IListOfBooks{
  /**
   * Returns the count of books in the list.
   * Since this is an empty node, the count is always 0.
   *
   * @return the number of books in the list, which is 0 for an empty node.
   */
  @Override
  public int count() {
    return 0;
  }

  /**
   * Returns the total price of books in the list.
   * Since this is an empty node, the total price is always 0.
   *
   * @return the total price of books in the list, which is 0 for an empty node.
   */

  @Override
  public float totalPrice() {
    return 0;
  }

  /**
   * Filters the list to include only books published before the specified year.
   * Since this is an empty node, it returns itself as there are no books to filter.
   *
   * @param year the year before which all the returned books are published.
   * @return an empty node, as there are no books to filter.
   */

  @Override
  public IListOfBooks allBefore(int year) {
    return this;
  }

  /**
   * Adds a book to the end of the list.
   * Since this is an empty node, adding a book creates a new ElementNode with the provided book and this empty node as its next node.
   *
   * @param book an instance of Class Book to add to the list.
   * @return a new ElementNode containing the provided book and this empty node as the next node.
   */

  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(book, this);
  }

  /**
   * Returns a string representation of the list.
   * Since this is an empty node, the string representation is an empty string.
   *
   * @return an empty string, representing the absence of the books in the list.
   */
  @Override
  public  String toString() {
    return "";
  }
}
