public class ElementNode implements IListOfBooks{

  private Book book;
  private IListOfBooks rest;

  public ElementNode(Book book, IListOfBooks rest) {
    this.book = book;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + rest.count();
  }

  @Override
  public float totalPrice() {
    return book.getPrice() + rest.totalPrice();
  }

  @Override
  public IListOfBooks allBefore(int year) {
    if(book.before(year)){
      return new ElementNode(book, rest.allBefore(year));
    }else{
      return rest.allBefore(year);
    }

  }

  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(this.book, rest.addAtEnd(book));
  }

  @Override
  public String toString(){
    return book.toString() + "\n" + rest.toString();
  }
}
