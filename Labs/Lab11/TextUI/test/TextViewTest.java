import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the TextView class.
 * It verifies that the TextView methods correctly print the expected output.
 */
class TextViewTest {
  private ByteArrayOutputStream output1;
  private PrintStream printStream1;
  private IView view1;

  private ByteArrayOutputStream output2;
  private PrintStream printStream2;
  private IView view2;

  /**
   * Setup method that runs before each test.
   * It creates output streams and initializes two instances of TextView.
   */
  @BeforeEach
  void setUp() {
    output1 = new ByteArrayOutputStream();
    printStream1 = new PrintStream(output1);
    view1 = new TextView(printStream1);

    output2 = new ByteArrayOutputStream();
    printStream2 = new PrintStream(output2);
    view2 = new TextView(printStream2);
  }

  /**
   * Test that the showString method prints a string followed by a newline character.
   */
  @Test
  void showString() {
    view1.showString("Hello");
    String result = output1.toString();
    assertNotEquals("String: Hello", result, "Output should end with a newline");
    assertEquals("String: Hello\n", result);

    view2.showString("World");
    result = output2.toString();
    assertNotEquals("String: World", result, "Output should end with a newline");
    assertEquals("String: World\n", result);
  }

  /**
   * Test that the showOptions method prints the menu options.
   */
  @Test
  void showOptions() {
    view1.showOptions();
    String result1 = output1.toString();
    assertTrue(result1.contains("Menu: "), "Output should display the menu header");
    assertTrue(result1.contains("Enter your choice:"), "Output should include the prompt for choice");

    view2.showOptions();
    String result2 = output1.toString();
    assertEquals("Menu: \n"
        + "E: Enter a string\n"
        + "Q: Quit the program\n"
        + "Enter your choice: ", result2);
  }

  /**
   * Test that the showStringEntry method prints the prompt for entering a string.
   */
  @Test
  void showStringEntry() {
    view1.showStringEntry();
    String result = output1.toString();
    assertTrue(result.contains("Enter the string to be echoed:"), "Output should contain the string entry prompt");
    assertTrue(result.startsWith("\n"), "Output should start with a newline");

    view2.showStringEntry();
    String result2 = output2.toString();
    assertEquals("\n"
        + "Enter the string to be echoed: ", result2);
  }

  /**
   * Test that the showOptionError method prints an error message
   */
  @Test
  void showOptionError() {
    view1.showOptionError();
    String result1 = output1.toString();
    assertTrue(result1.contains("Invalid option."), "Output should contain 'Invalid option.'");
    assertTrue(result1.startsWith("\n"), "Output should start with a newline");

    view2.showOptionError();
    String result2 = output2.toString();
    assertEquals("\n" + "Invalid option.\n", result2);
  }
}
