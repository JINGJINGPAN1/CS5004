package view;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
/**
 * BackgroundViewTest class is responsible for testing the BackgroundView class
 */
class BackgroundViewTest {
  BackgroundView backgroundView;
  Graphics mockGraphics;

  @BeforeEach
  void setUp() {
    backgroundView = new BackgroundView();
    // Mock the Graphics object
    mockGraphics = Mockito.mock(Graphics.class);
  }

  /**
   * Test the draw method of BackgroundView
   */
  @Test
  void draw() {
    // Call the draw method
    backgroundView.draw(mockGraphics);

    // Verify that the drawImage method was called with the correct parameters
    verify(mockGraphics, atLeastOnce()).drawImage(any(), anyInt(), anyInt(), isNull());
  }
}