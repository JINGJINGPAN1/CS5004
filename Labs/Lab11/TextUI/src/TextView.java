import java.io.PrintStream;

public class TextView implements IView {
  private PrintStream out;

  public TextView(PrintStream out) {
    this.out = out;
  }

  @Override
  public void showString(String s) {
    out.println("String: "+s);
  }

  @Override
  public void showOptions() {
    // print the UI -- details are up to the View,
    // except for the messages "E" and "Q"
    out.println("Menu: ");
    out.println("E: Enter a string");
    out.println("Q: Quit the program");
    out.print("Enter your choice: ");
  }

  @Override
  public void showStringEntry() {
    out.print("\nEnter the string to be echoed: ");
  }

  @Override
  public void showOptionError() {
    out.print("\nInvalid option.\n");
  }
}
