package view;

import controller.GameController;
import java.awt.CardLayout;
import java.util.List;
import model.Item;
import model.Line;
import model.LineState;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The {@code GameWin} class is the main window for the Gold Miner game.
 * <p>
 * It manages the different screens using a {@link CardLayout}, such as:
 * - Start Screen
 * - Game Panel (main gameplay)
 * - Game Over Screen
 * </p>
 * It also handles user interactions and drives the game loop using a Swing Timer.
 */
public class GameWin extends JFrame implements screenListener {

  /** The controller that manages game logic and state. */
  protected GameController gameController;

  /** View for the background image of the game. */
  private BackgroundView backgroundView;

  /** View for rendering the swinging line. */
  private LineView lineView;

  /** View for rendering collectible items. */
  private ItemView itemView;

  /** Main gameplay panel that contains all active components. */
  protected GamePanel gamePanel;

  /** The initial start screen with the Start button. */
  protected StartScreen startScreen;

  /** The screen displayed when the game is over. */
  protected GameOverScreen gameOverScreen;

  /** The main container panel that holds and switches between views. */
  protected JPanel mainPanel;

  /** The layout manager used to switch between views. */
  protected CardLayout cardLayout;

  /** The game loop timer that drives updates and checks game state. */
  protected Timer gameLoopTimer;

  /**
   * Constructs the main game window, initializes views and sets up listeners.
   */
  public GameWin() {
    // Controller and view initialization
    gameController = new GameController();
    backgroundView = new BackgroundView();
    Line line = gameController.getLine();
    lineView = new LineView(line);
    List<Item> itemList = gameController.getItemList();
    itemView = new ItemView(itemList);
    gamePanel = new GamePanel(gameController, backgroundView, lineView, itemView);

    // Screens with listener registration
    startScreen = new StartScreen(this);
    startScreen.setListeners();
    gameOverScreen = new GameOverScreen(this);
    gameOverScreen.setListeners();

    // Setup CardLayout
    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);
    mainPanel.add(startScreen, "START");
    mainPanel.add(gamePanel, "GAME");
    mainPanel.add(gameOverScreen, "GAMEOVER");

    add(mainPanel);

    // Mouse listener to trigger line grab
    gamePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          Line currentLine = gameController.getLine();
          if (currentLine.getLineState() == LineState.GRAB || currentLine.getLineState() == LineState.SWING) {
            gameController.startGrabbing();
          } else {
            System.out.println("Cannot grab now, line is retracting.");
          }
        }
      }
    });
  }

  /**
   * Triggered when the user clicks the "Start" button.
   * Resets game state and switches to the game panel.
   */
  @Override
  public void onStartClicked() {
    gameController.resetGame();
    cardLayout.show(mainPanel, "GAME");
    startGameLoop();
  }

  /**
   * Starts the main game loop using a Swing {@link Timer}.
   * This timer updates the game state every ~16 milliseconds (60 FPS).
   */
  protected void startGameLoop() {
    gameLoopTimer = new javax.swing.Timer(16, e -> {
      gameController.update();

      // Show level complete dialog
      if (gameController.isLevelComplete()) {
        gameLoopTimer.stop();
        LevelCompleteDialog dialog = new LevelCompleteDialog(this, this);
        dialog.setVisible(true);
      }

      // Handle game over
      if (gameController.isGameOver()) {
        gameLoopTimer.stop();
        Timer delayTimer = new Timer(1000, ev -> {
          gameOverScreen.updateScoreAndLevel(gameController.getScore(), gameController.getLevel());
          cardLayout.show(mainPanel, "GAMEOVER");
        });
        delayTimer.setRepeats(false);
        delayTimer.start();
      }

      repaint();
    });
    gameLoopTimer.start();
  }

  /**
   * Triggered when the user clicks "Restart" on the game over screen.
   * Resets game state and starts the loop again.
   */
  @Override
  public void onRestartClicked() {
    gameController.resetGame();
    cardLayout.show(mainPanel, "GAME");
    startGameLoop();
  }

  /**
   * Triggered when the user clicks "Return to Menu" from any screen.
   * Switches to the start screen.
   */
  @Override
  public void onReturnToMenuClicked() {
    cardLayout.show(mainPanel, "START");
  }

  /**
   * Triggered when the user completes a level and clicks "Next Level".
   * Loads the next level and starts the game loop.
   */
  @Override
  public void onNextLevelClicked() {
    gameController.gotoNextLevel();
    cardLayout.show(mainPanel, "GAME");
    startGameLoop();
  }

  /**
   * Triggered when the user clicks "Exit" on the game over screen.
   * Resets the game and restarts it.
   */
  @Override
  public void onExitClicked() {
    gameController.resetGame();
    cardLayout.show(mainPanel, "GAME");
    startGameLoop();
  }

  /**
   * Launches the game window with title, size, and visibility.
   * This method should be called from your {@code main()} method.
   */
  public void launch() {
    setTitle("Gold Miner");
    setSize(600, 800);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    cardLayout.show(mainPanel, "START");
  }
}
