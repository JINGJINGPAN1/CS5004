package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.Line;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the LineView class.
 */
class LineViewTest {
  Line mockLine;
  Graphics graphics;
  LineView lineView;

  /**
   * Sets up the test environment by creating a mock Line object and initializing the graphics context.
   */
  @BeforeEach
  void setUp() {
    mockLine = mock(Line.class);
    when(mockLine.getStartX()).thenReturn(1);
    when(mockLine.getStartY()).thenReturn(2);
    when(mockLine.getEndX()).thenReturn(3);
    when(mockLine.getEndY()).thenReturn(4);

    BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    graphics = image.getGraphics();

    lineView = new LineView(mockLine);
  }

  /**
   * Tests the draw method of LineView.
   * It checks if the draw method can be called without throwing an exception.
   */
  @Test
  void draw() {
    assertDoesNotThrow(() -> lineView.draw(graphics));
  }
}