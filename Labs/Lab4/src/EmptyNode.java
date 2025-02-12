public class EmptyNode implements IListOfBooks{


  @Override
  public int count() {
    return 0;
  }

  @Override
  public float totalPrice() {
    return 0;
  }

  @Override
  public IListOfBooks allBefore(int year) {
    return this;
  }

  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(book, this);
  }

  @Override
  public  String toString() {
    return "";
  }
}
