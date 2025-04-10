package view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Score;
import model.Level;

/**
 * GameOverScreen shows the game over message along with the player's score and level.
 * It provides options to restart the game or return to the start menu.
 */
public class GameOverScreen extends JPanel {

  public interface GameOverListener {
    // Called when the user clicks to restart the game.
    void onRestartClicked();
    // Called when the user chooses to return to the start menu.
    void onReturnToMenuClicked();
  }

  private GameOverListener listener;
  private JLabel gameOverLabel;
  private JLabel scoreLabel;
  private JLabel levelLabel;
  private JButton restartButton;
  private JButton returnButton;

  public GameOverScreen(GameOverListener listener) {
    this.listener = listener;
    initializeUI();
  }

  private void initializeUI() {
    setLayout(new BorderLayout());

    // Top area: game over message.
    gameOverLabel = new JLabel("GAME OVER", JLabel.CENTER);
    gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
    add(gameOverLabel, BorderLayout.NORTH);

    // Center area: display score and level.
    JPanel centerPanel = new JPanel(new BorderLayout());
    scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
    scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
    levelLabel = new JLabel("Level: 1", JLabel.CENTER);
    levelLabel.setFont(new Font("Arial", Font.BOLD, 24));
    centerPanel.add(scoreLabel, BorderLayout.NORTH);
    centerPanel.add(levelLabel, BorderLayout.SOUTH);
    add(centerPanel, BorderLayout.CENTER);

    // Bottom area: buttons for restart and return.
    JPanel buttonPanel = new JPanel();
    restartButton = new JButton("Restart Game");
    returnButton = new JButton("Return to Menu");
    buttonPanel.add(restartButton);
    buttonPanel.add(returnButton);
    add(buttonPanel, BorderLayout.SOUTH);

    // Add button listeners.
    restartButton.addActionListener(e -> {
      if (listener != null) {
        listener.onRestartClicked();
      }
    });

    returnButton.addActionListener(e -> {
      if (listener != null) {
        listener.onReturnToMenuClicked();
      }
    });
  }

  /**
   * Updates the score and level information displayed.
   *
   * @param score the current score
   * @param level the current level
   */
  public void updateScoreAndLevel(Score score, Level level) {
    scoreLabel.setText("Score: " + score.getCurrentScore());
    levelLabel.setText("Level: " + level.getCurrentLevel());
  }
}

