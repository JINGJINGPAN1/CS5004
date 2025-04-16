package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for LevelView.
 */
class LevelViewTest {
  Graphics graphics;
  Level mockLevel;

  @BeforeEach
  void setUp() {
    BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    graphics = mockImage.getGraphics();
    mockLevel = mock(Level.class);
    when(mockLevel.getCurrentLevel()).thenReturn(2);
    when(mockLevel.getTargetScore()).thenReturn(1000);
  }

  /**
   * Test the draw method of LevelView.
   */
  @Test
  void draw() {
    LevelView levelView = new LevelView(mockLevel);
    assertDoesNotThrow(() -> levelView.draw(graphics));
    levelView.draw(graphics);
    assertNotNull(graphics);
  }
}