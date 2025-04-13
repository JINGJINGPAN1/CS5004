import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the TextController class.
 * It uses a model, a view, and simulated user input.
 * The tests check that the controller behaves as expected.
 */
class TextControllerTest {
  private IModel model;
  private IView view;
  private ByteArrayOutputStream output;
  private InputStream input;

  /**
   * This method runs before each test.
   * It sets up a default input that simulates quitting by typing "Q".
   * It also creates a model and a view that writes to an output stream.
   */
  @BeforeEach
  void setUp() {
    input = new ByteArrayInputStream("Q\n".getBytes());
    model = new Model();
    output = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(output);
    view = new TextView(ps);
  }

  /**
   * Test when the user types "Q" to quit immediately.
   */
  @Test
  void testGoQuitImmediately() {
    IController controller = new TextController(model, input, view);
    controller.go();

    assertEquals("", model.getString());

    String outStr = output.toString();
    assertTrue(outStr.contains("Enter your choice:"));
  }

  /**
   * Test when the user enters "E", then types "Hello World", and then "Q" to quit.
   */
  @Test
  void testGoEnterString() {
    // Simulate input: "E" to choose string entry, "Hello World" as the string, then "Q" to quit.
    input = new ByteArrayInputStream("E\nHello World\nQ\n".getBytes());
    IController controller = new TextController(model, input, view);
    controller.go();

    assertEquals("Hello World", model.getString());

    String outStr = output.toString();
    assertTrue(outStr.contains("Enter the string to be echoed:"));
  }

  /**
   * Test when the user enters an invalid option "X", then quits with "Q".
   */
  @Test
  void testGoInvalidOption() {
    // Simulate input: "X" as an invalid option, then "Q" to quit.
    input = new ByteArrayInputStream("X\nQ\n".getBytes());
    IController controller = new TextController(model, input, view);
    controller.go();

    assertEquals("", model.getString());

    String outStr = output.toString();
    assertTrue(outStr.contains("Invalid option."));
  }
}
