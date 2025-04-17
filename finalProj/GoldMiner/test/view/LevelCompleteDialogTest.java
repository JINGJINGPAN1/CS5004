package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import utils.ResourceLoader;

/**
 * Test class for LevelCompleteDialog.
 */
class LevelCompleteDialogTest {
  screenListener mockListener;
  LevelCompleteDialog dialog;
  BufferedImage mockImage;

  @BeforeEach
  void setUp() {
    mockListener = mock(screenListener.class);
    mockImage = mock(BufferedImage.class);

    try(MockedStatic<ResourceLoader> mockedLoader = mockStatic(ResourceLoader.class)) {
      mockedLoader.when(() -> ResourceLoader.loadImage("resources/imgs/test.png"))
          .thenReturn(mockImage);

      dialog = new LevelCompleteDialog(new JFrame(), mockListener);
    }
  }

  /**
   * Test the initialization of the UI components.
   */
  @Test
  void initializeUI() {
    JButton nextButton = getPrivateButton("nextButton");
    JButton quitButton = getPrivateButton("quitButton");
    assertNotNull(nextButton);
    assertNotNull(quitButton);
    assertEquals(" Next Level ", nextButton.getText());
    assertEquals(" Quit Game ", quitButton.getText());

    nextButton.doClick();
    verify(mockListener).onNextLevelClicked();

    quitButton.doClick();
    verify(mockListener).onReturnToMenuClicked();
  }

  private JButton getPrivateButton(String fieldName) {
    try {
      var field = LevelCompleteDialog.class.getDeclaredField(fieldName);
      field.setAccessible(true);
      return (JButton) field.get(dialog);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Test the stylization of the dialog.
   */
  @Test
  void stylizedButton() {
    JButton button = new JButton("Test");
    dialog.stylizedButton(button);
    assertEquals(new Font("Comic Sans MS", Font.BOLD, 18), button.getFont());
    assertEquals(Color.WHITE, button.getForeground());
    assertFalse(button.isContentAreaFilled());
    assertFalse(button.isFocusPainted());
    assertFalse(button.isOpaque());
    assertNotNull(button.getBorder());
  }
}