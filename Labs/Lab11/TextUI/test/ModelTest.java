import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Model class implementing IModel.
 */
class ModelTest {
  private IModel model1;
  private IModel model2;

  /**
   * Create two fresh Model instances before each test.
   */
  @BeforeEach
  void setUp() {
    model1 = new Model();
    model2 = new Model();
  }

  /**
   * Test that setString stores the value correctly.
   */
  @Test
  void setString() {
    model1.setString("Hello World");
    assertEquals("Hello World", model1.getString(),
        "model1 should return the string we set.");

    model2.setString("CS5004");
    assertEquals("CS5004", model2.getString(),
        "model2 should return the string we set.");
  }

  /**
   * Test that getString returns the initial value and updates correctly.
   */
  @Test
  void getString() {
    // Empty string
    assertEquals("", model1.getString(),
        "New model1 should start with empty string.");
    assertEquals("", model2.getString(),
        "New model2 should start with empty string.");

    model1.setString("Hello World");
    assertEquals("Hello World", model1.getString());

    model2.setString("Coding");
    assertNotEquals("CS5004", model2.getString(),
        "model2 should not return 'CS5004' after setting to 'Coding'.");
  }
}
