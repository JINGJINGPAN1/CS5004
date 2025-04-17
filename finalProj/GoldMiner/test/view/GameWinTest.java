package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import controller.GameController;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.GameTimer;
import model.Line;
import model.LineState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

/**
 * Test class for GameWin.
 */
class GameWinTest {
  GameWin gameWin;
  GameController mockController;
  StartScreen mockStartScreen;
  GameOverScreen mockGameOverScreen;
  GamePanel mockGamePanel;

  /**
   * Set up the test environment.
   */
  @BeforeEach
  void setUp() {
    gameWin = spy(new GameWin());
    mockController = mock(GameController.class);
    mockStartScreen = mock(StartScreen.class);
    mockGameOverScreen = mock(GameOverScreen.class);
    mockGamePanel = mock(GamePanel.class);

    gameWin.gameController = mockController;
    gameWin.startScreen = mockStartScreen;
    gameWin.gameOverScreen = mockGameOverScreen;
    gameWin.gamePanel = mockGamePanel;
    gameWin.mainPanel = mock(JPanel.class);
    gameWin.cardLayout = mock(CardLayout.class);
  }

  /**
   * Test the onStartClicked method of GameWin.
   */
  @Test
  void onStartClicked() {
    gameWin.onStartClicked();
    verify(mockController).resetGame();
    verify(gameWin.cardLayout).show(gameWin.mainPanel, "GAME");
    assertNotNull(gameWin.gameLoopTimer);
    assertTrue(gameWin.gameLoopTimer.isRunning());
  }

  /**
   * Test the onRestartClicked method of GameWin.
   */
  @Test
  void onRestartClicked() {
    gameWin.onRestartClicked();
    verify(mockController).resetGame();
    verify(gameWin.cardLayout).show(gameWin.mainPanel, "GAME");
    assertNotNull(gameWin.gameLoopTimer);
  }

  /**
   * Test the onReturnToMenuClicked method of GameWin.
   */
  @Test
  void onReturnToMenuClicked() {
    gameWin.onReturnToMenuClicked();
    verify(gameWin.cardLayout).show(gameWin.mainPanel, "START");
  }

  /**
   * Test the onNextLevelClicked method of GameWin.
   */
  @Test
  void onNextLevelClicked() {
    gameWin.onNextLevelClicked();
    verify(mockController).gotoNextLevel();
    verify(gameWin.cardLayout).show(gameWin.mainPanel, "GAME");
  }

  /**
   * Test the onExitClicked method of GameWin.
   */
  @Test
  void onExitClicked() {
    gameWin.onExitClicked();
    verify(mockController).resetGame();
    verify(gameWin.cardLayout).show(gameWin.mainPanel, "GAME");
  }

  /**
   * Test the launch method of GameWin.
   */
  @Test
  void launch() {
    try (MockedStatic<JFrame> mockedFrame = mockStatic(JFrame.class)) {
      gameWin.launch();
      verify(gameWin).setTitle("Gold Miner");
      verify(gameWin).setSize(600, 800);
      verify(gameWin).setVisible(true);
      verify(gameWin).setLocationRelativeTo(null);
      verify(gameWin).setResizable(false);
      verify(gameWin).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      verify(gameWin.cardLayout).show(gameWin.mainPanel, "START");
    }
  }

  /**
   * Test the startGameLoop method of GameWin.
   */
  @Test
  void startGameLoop() {
    gameWin.startGameLoop();
    assertNotNull(gameWin.gameLoopTimer);
    assertTrue(gameWin.gameLoopTimer.isRunning());
    assertEquals(16, gameWin.gameLoopTimer.getDelay());
    gameWin.gameLoopTimer.getActionListeners()[0].actionPerformed(null);
    verify(mockController).update();
    verify(gameWin).repaint();
  }
}