package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import controller.GameController;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import model.GameTimer;
import model.Level;
import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Test class for GamePanel.
 */
class GamePanelTest {
  GameController mockController;
  BackgroundView mockBackgroundView;
  LineView mockLineView;
  ItemView mockItemView;
  GamePanel gamePanel;

  @BeforeEach
  void setUp() {
    mockController = mock(GameController.class);
    mockBackgroundView = mock(BackgroundView.class);
    mockLineView = mock(LineView.class);
    mockItemView = mock(ItemView.class);
    when(mockController.getScore()).thenReturn(new Score());
    when(mockController.getLevel()).thenReturn(new Level());
    when(mockController.getGameTimer()).thenReturn(new GameTimer(10));
    when(mockController.getItemList()).thenReturn(Collections.emptyList());

    gamePanel = new GamePanel(mockController, mockBackgroundView, mockLineView, mockItemView);
  }

  /**
   * Test of paintComponent method of GamePanel.
   */
  @Test
  void paintComponent() {
    Graphics graphics = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB).getGraphics();
    gamePanel.paintComponent(graphics);
    verify(mockBackgroundView).draw(graphics);
    verify(mockLineView).draw(graphics);
    verify(mockItemView, times(2)).draw(graphics);
    verify(mockItemView).setItemList(Collections.emptyList());
  }
}