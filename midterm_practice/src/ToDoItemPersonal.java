import java.awt.print.Book;
import java.util.List;

/**
 * School items are like other ToDos, but need prepTime, a double
 * <p></p>
 * This requires overriding the toString method
 */

// Use extends when a class inherits from another class (like ToDoItemAbs).
// Use implements when a class implements an interface (like ToDoItem).
//Since ToDoItemAbs already implements ToDoItem, subclasses should extend ToDoItemAbs to avoid redundant code.
// public class ToDoItemPersonal implements ToDoItemAbs {
public class ToDoItemPersonal extends ToDoItemAbs {
//  public String description;
//  private Importance importance;
//  private int urgency;

//  for description, importance, and urgency,
//  these fields already exist in ToDoItemAbs (which it extends).
//  Since ToDoItemAbs already has these fields, declaring them again in ToDoItemPersonal
//  creates duplicate fields, leading to confusion and potential bugs.
  private final double prepTime;

  public ToDoItemPersonal(String description, Importance importance,
      int urgency, double prepTime ) {
//    super(description, urgency, importance);
    // The order of parameters in the super(..) call does not match the constructor of ToDoItemAbs
    super(description, importance, urgency);
    this.prepTime = prepTime;
  }

  /**
   * School items need estimated time needed to prepare
   *
   * @return prepTime -- a double -- in minutes
   */
  public double getPrepTime() {
    return this.prepTime;
  }

//  incorrect Override signature
//  The equals method must override Object's equals method,
  // which has the signature: public boolean equals(Object obj)
  // However, the provided code, the type of parameter in the equals(...) is ToDoItemPersonal class
  // this is not right.
  // Also, the method implementation should be corrected.

//  @Override
//  public boolean equals(ToDoItemPersonal itm) {
//    if (this == itm) return true;
//    return this.getDescription().equals(itm.getDescription()) &&
//        (this.getUrgency() == itm.getUrgency()) &&
//        (this.getImportance() == itm.getImportance()) &&
//        (this.getPrepTime() == itm.getPrepTime());
//
//  }
  @Override
  public boolean equals(Object o){
    if(this == o) {
      return true;
    }
    if(o == null || !(o instanceof ToDoItemPersonal)) {
      return false;
    }

    ToDoItemPersonal itm = (ToDoItemPersonal) o;
    return this.getDescription().equals(itm.getDescription())
        && this.getUrgency() == itm.getUrgency()
        && this.getImportance() == itm.getImportance()
        && this.getPrepTime() == itm.getPrepTime();
  }


  @Override
  public String toString() {

    String str = "ToDoItem: "+this.getDescription();
//    str += " Importance: "+this.getImportance()+" Urgency: "+this.getUrgency();
//    this code is okay, but to make it more readable, i would like to correct it in a formatted version.
    str += " Importance: "+this.getImportance();
    str += " Urgency: "+this.getUrgency();
    str += " PrepTime: "+this.getPrepTime();

    // Missing return
    return str;
  }
}
