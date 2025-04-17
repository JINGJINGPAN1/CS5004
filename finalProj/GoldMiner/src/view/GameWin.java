package view;

import controller.GameController;
import java.awt.CardLayout;
import java.util.List;
import model.Item;
import model.Line;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.LineState;

public class GameWin extends JFrame implements screenListener {
  protected GameController gameController;

  // Views
  private BackgroundView backgroundView;
  private LineView lineView;
  private ItemView itemView;
  protected GamePanel gamePanel;
  protected StartScreen startScreen;      // Changed type to StartScreen
  protected GameOverScreen gameOverScreen;

  protected JPanel mainPanel;  // container for different views
  protected CardLayout cardLayout;

  // Use a single timer and keep a reference to it so we can stop it when needed.
  protected Timer gameLoopTimer;

  public GameWin() {
    // Initialize the controller and views
    gameController = new GameController();
    backgroundView = new BackgroundView();
    Line line = gameController.getLine();
    lineView = new LineView(line);
    List<Item> itemList = gameController.getItemList();
    itemView = new ItemView(itemList);
    gamePanel = new GamePanel(gameController, backgroundView, lineView, itemView);

    // Initialize StartScreen and GameOverScreen with listeners.
    // 'this' is passed as the listener because GameWin now implements StartScreenListener.
    startScreen = new StartScreen(this);
    startScreen.setListeners();
    gameOverScreen = new GameOverScreen(this);

    // Setup CardLayout to switch views.
    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);
    mainPanel.add(startScreen, "START");
    mainPanel.add(gamePanel, "GAME");
    mainPanel.add(gameOverScreen, "GAMEOVER");

    add(mainPanel);

    // Set mouse listener on gamePanel for game actions.
    gamePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // Only allow grabbing if the line is in SWING or GRAB state.
        if (e.getButton() == MouseEvent.BUTTON1) {
          if (gameController.getLine().getLineState() == LineState.GRAB ||
              gameController.getLine().getLineState() == LineState.SWING) {
            gameController.startGrabbing();
          } else {
            System.out.println("Cannot grab now, line is retracting.");
          }
        }
      }
    });
  }

  /**
   * This method is called when the user clicks the "Start" button on the start screen.
   */
  @Override
  public void onStartClicked() {
    // Reset the game state every time Start is clicked.
    gameController.resetGame();
    // Show the game panel.
    cardLayout.show(mainPanel, "GAME");
    // Start the game loop.
    startGameLoop();
  }

  /**
   * Starts the game loop timer.
   */
  protected void startGameLoop() {
    // Create and assign the timer to a member variable.
    gameLoopTimer = new javax.swing.Timer(16, e -> {
      gameController.update();

      if(gameController.isLevelComplete()){
        gameLoopTimer.stop();

        // display popup to next level or not
        LevelCompleteDialog dialog = new LevelCompleteDialog(this, this);
        dialog.setVisible(true);
      }

      // When game over is detected, stop the timer and switch to GameOverScreen.
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

  @Override
  public void onRestartClicked() {
    // Restart the game: reset game state and switch to game panel.
    gameController.resetGame();
    cardLayout.show(mainPanel, "GAME");
    // Restart the game loop timer.
    startGameLoop();
  }

  @Override
  public void onReturnToMenuClicked() {
    // Return to the start screen.
    cardLayout.show(mainPanel, "START");
  }

  @Override
  public void onNextLevelClicked() {
    gameController.gotoNextLevel();
    cardLayout.show(mainPanel, "GAME");
    startGameLoop();
  }

  @Override
  public void onExitClicked() {
    gameController.resetGame();
    cardLayout.show(mainPanel, "GAME");
    startGameLoop();
  }

  public void launch() {
    setTitle("Gold Miner");
    setSize(600, 800);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // Initially show the start screen.
    cardLayout.show(mainPanel, "START");
  }
}
