package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.GameTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * GameTimerViewTest is a test class for the GameTimerView class.
 */
class GameTimerViewTest {
  GameTimer mockTimer;
  GameTimerView gameTimerView;
  Graphics graphics;

  @BeforeEach
  void setUp() {
    mockTimer = mock(GameTimer.class);
    gameTimerView = new GameTimerView(mockTimer);
    BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    graphics = image.getGraphics();
  }

  /**
   * Test the draw method of GameTimerView.
   */
  @Test
  void draw() {
    when(mockTimer.getTimeLeft()).thenReturn(10.0);
    assertDoesNotThrow(() -> gameTimerView.draw(graphics));

    when(mockTimer.getTimeLeft()).thenReturn(0.0);
    assertDoesNotThrow(() -> gameTimerView.draw(graphics));

    when(mockTimer.getTimeLeft()).thenReturn(-2.0);
    assertDoesNotThrow(() -> gameTimerView.draw(graphics));
  }
}