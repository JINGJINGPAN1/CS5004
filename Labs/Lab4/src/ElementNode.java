/**
 * This class represents a node in a list of books that contains a book and a reference to the rest of the list.
 * This class implements the IListOfBooks interface and is used to build a recursive list structure.
 * Each ElementNode holds a single book and a reference to the next node in the list.
 */
public class ElementNode implements IListOfBooks{

  private Book book; // The book stored in this node
  private IListOfBooks rest; // The rest of the list(could be another ElementNode or an Empty Node)

  /**
   * Constructs an elementNode with the given book and the rest of the list.
   *
   * @param book the book to store in this node.
   * @param rest the rest of the list, which could be another ElementNode or an EmptyNode.
   */
  public ElementNode(Book book, IListOfBooks rest) {
    this.book = book;
    this.rest = rest;
  }

  /**
   * Returns the total number of books in the list.
   * This method recursively counts the books by adding 1 for the current node and the count of the rest of the list.
   *
   * @return the total number of books in the list.
   */

  @Override
  public int count() {
    return 1 + rest.count();
  }

  /**
   * Returns the total prices of the book in the list.
   * This method recursively sums the price of the book in the current node and the total price of the rest of the list.
   *
   * @return the total price of all books in the list.
   */
  @Override
  public float totalPrice() {
    return book.getPrice() + rest.totalPrice();
  }

  /**
   * Filters the list to include only books published before the specified year.
   * This method checks if the current book meets the criteria and recursively processes the rest of the list.
   *
   * @param year the year before which all the returned books are published
   * @return a new list containing books published before the specified year.
   */
  @Override
  public IListOfBooks allBefore(int year) {
    if(book.before(year)){
      // If the current book is before the specified year, include it in the new list
      return new ElementNode(book, rest.allBefore(year));
    }else{
      // Otherwise, skip the current book and process the rest of the list
      return rest.allBefore(year);
    }

  }

  /**
   * Adds a book to the end of list.
   * This method recursively traverses the list until it reaches the end(an EmptyNode) and add the new book there.
   *
   * @param book an instance of Class Book
   * @return a new list with the book added at the end.
   */
  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(this.book, rest.addAtEnd(book));
  }

  /**
   * Returns a string representation of the list.
   * This method recursively constructs a string by concatenating the string representation of the current book and the rest of the list.
   *
   * @return a string representation of the list, with each book on a new line.
   */

  @Override
  public String toString(){
    return book.toString() + "\n" + rest.toString();
  }
}
