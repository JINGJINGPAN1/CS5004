import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

/**
 * This class tests the main integration of the MVCExampleTextUI.
 * It simulates user input and checks the output.
 */
public class MVCExampleTextUITest {

  /**
   * Tests the main method of MVCExampleTextUI.
   * It checks that the program's output contains the entered text and the menu prompt.
   */
  @Test
  public void testMainIntegration() {
    // Save the original system input and output streams.
    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;

    try {
      // Create simulated input: "E", then a test string, then "Q".
      String simulatedInput = "E\nMain Integration Test\nQ\n";
      ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
      ByteArrayOutputStream testOut = new ByteArrayOutputStream();

      PrintStream testPrintOut = new PrintStream(testOut);
      PrintStreamAdapter adapter = new PrintStreamAdapter(testPrintOut);

      // Replace System.in and System.out for the duration of the test.
      System.setIn(testIn);
      System.setOut(testPrintOut);

      // Run the main method.
      MVCExampleTextUI.main(new String[0]);

      // Get the output from the test.
      String output = testOut.toString();

      // Check that the output contains the test string.
      assertTrue(output.contains("Main Integration Test"), "Output should display the entered string.");

      // Check that the output includes the menu prompt.
      assertTrue(output.contains("Enter your choice:"), "Output should include the choice prompt.");
    } finally {
      // Restore the original system input and output streams.
      System.setIn(originalIn);
      System.setOut(originalOut);
    }
  }
}
