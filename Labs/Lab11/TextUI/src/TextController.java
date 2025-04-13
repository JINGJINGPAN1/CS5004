import java.io.InputStream;
import java.util.Scanner;

public class TextController implements IController {
  private Scanner in;
  private IView view;
  private IModel model;

  /** Construct concrete Text Controller based on IController interface */
  public TextController(IModel model, InputStream in, IView view) {
    this.model = model;
    this.view = view;
    this.in = new Scanner(in);
  }

  public void go() {
    boolean quit = false;

    while (!quit) {
      // tell view to show the string so far
      view.showString(this.model.getString());
      // tell view to show options
      view.showOptions();
      // accept user input
      String option = in.next();
      switch (option) {
        // View and controller must agree on these signals (E and Q)
        case "E":
          // ask for string input
          view.showStringEntry();
          in.nextLine();
          String input  = in.nextLine();
          // give to model
          model.setString(input);
          break;
        case "Q":
          quit = true;
          break;
        default:
          view.showOptionError();
      }
    }
  }
}
