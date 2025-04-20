package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import utils.ResourceLoader;

/**
 * Test class for StartScreen.
 */
class StartScreenTest {
  ScreenListener mockListener;
  StartScreen startScreen;
  BufferedImage mockBackground;
  MockedStatic<ResourceLoader> mockedLoader;

  @BeforeEach
  void setUp() {
    mockListener = mock(ScreenListener.class);
    mockBackground = mock(BufferedImage.class);
    mockedLoader = mockStatic(ResourceLoader.class);
    mockedLoader.when(() -> ResourceLoader.loadImage("resources/imgs/test.png"))
          .thenReturn(mockBackground);
    startScreen = spy(new StartScreen(mockListener));
    doNothing().when(startScreen).exitApplication();
    startScreen.setListeners();
  }

  @AfterEach
  void tearDown() {
    mockedLoader.close(); // Close the static mock
  }

  /**
   * Test the initialization of the StartScreen.
   */
  @Test
  void initializeUI() {
    assertNotNull(startScreen.startButton);
    assertEquals("Start", startScreen.startButton.getText());
    startScreen.startButton.doClick();
    verify(mockListener).onStartClicked();

    assertNotNull(startScreen.exitButton);
    assertEquals("Exit", startScreen.exitButton.getText());
    startScreen.exitButton.doClick();
    verify(startScreen).exitApplication();
  }

  /**
   * Test the paintComponent method.
   */
  @Test
  void paintComponent() {
    BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
    Graphics graphics = image.createGraphics();

    try {
      startScreen.setSize(800, 600);
      startScreen.paintComponent(graphics);
      assertNotEquals(0, image.getRGB(0, 0));
    } finally {
        graphics.dispose();
    }
  }
}