package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.Level;
import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import utils.ResourceLoader;

/**
 * Test class for GameOverScreen.
 * This class contains unit tests for the GameOverScreen class.
 */
class GameOverScreenTest {
  screenListener mockListener; // Mocked screenListener
  GameOverScreen screen; // Instance of GameOverScreen to be tested
  Score mockScore; // Mocked Score object
  Level mockLevel; // Mocked Level object
  ResourceLoader mockLoader; // Mocked ResourceLoader
  BufferedImage mockImage; // Mocked BufferedImage

  @BeforeEach
  void setUp() {
    mockListener = mock(screenListener.class);
    screen = spy(new GameOverScreen(mockListener));
    doNothing().when(screen).exitApplication();
    screen.setListeners();
    mockScore = mock(Score.class);
    mockLevel = mock(Level.class);
    mockLoader = mock(ResourceLoader.class);
    mockImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Test the initialization of the UI components.
   */
  @Test
  void initializeUI() {
    assertNotNull(screen.scoreLabel);
    assertNotNull(screen.levelLabel);
    assertNotNull(screen.restartButton);
    assertNotNull(screen.exitButton);
    assertEquals("Score: 0", screen.scoreLabel.getText());
    assertEquals("Level: 0", screen.levelLabel.getText());

    screen.restartButton.doClick();
    verify(mockListener).onStartClicked();

    screen.exitButton.doClick();
    verify(screen).exitApplication();
  }

  /**
   * Test the stylizedButton method.
   */
  @Test
  void stylizedButton() {
    screen.stylizedButton(screen.restartButton);
    assertEquals("ComicSansMS-Bold", screen.restartButton.getFont().getFontName());
    assertEquals(24, screen.restartButton.getFont().getSize());
    assertEquals(java.awt.Color.WHITE, screen.restartButton.getForeground());
  }

  /**
   * Test the updateScoreAndLevel method.
   */
  @Test
  void updateScoreAndLevel() {
    screen.updateScoreAndLevel(mockScore, mockLevel);
    assertEquals("Score: " + mockScore.getScore(), screen.scoreLabel.getText());
    assertEquals("Level: " + mockLevel.getCurrentLevel(), screen.levelLabel.getText());
  }

  /**
   * Test the paintComponent method.
   * This method is responsible for painting the background image.
   */
  @Test
  void paintComponent() {
    BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    Graphics graphics = image.createGraphics();

    try {
      screen.setSize(800, 600);
      screen.paintComponent(graphics);
      assertNotNull(image.getRGB(0, 0));
    } finally {
      graphics.dispose();
    }
  }
}