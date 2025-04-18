package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.Color;
import model.Score;
import model.Level;
import utils.ResourceLoader;

/**
 * The {@code GameOverScreen} class represents the screen shown when the game ends.
 * It displays the player's score and level and provides options to restart or exit.
 */
public class GameOverScreen extends JPanel {

  /** Listener for screen actions like restart or exit. */
  private screenListener listener;

  /** Background image for the game over screen. */
  private BufferedImage background;

  /** Label displaying the final score. */
  protected JLabel scoreLabel;

  /** Label displaying the final level. */
  protected JLabel levelLabel;

  /** Button to restart the game. */
  protected JButton restartButton;

  /** Button to exit the game. */
  protected JButton exitButton;

  /**
   * Constructs the game over screen and initializes the UI components.
   *
   * @param listener A {@link screenListener} for handling restart or exit events.
   */
  public GameOverScreen(screenListener listener) {
    this.listener = listener;
    background = ResourceLoader.loadImage("resources/imgs/bg2.png");
    setLayout(new GridBagLayout());
    initializeUI();
  }

  /**
   * Initializes the layout, styling, and components on the screen.
   */
  private void initializeUI() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Score label
    scoreLabel = new JLabel("Score: 0");
    scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    scoreLabel.setForeground(Color.WHITE);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(scoreLabel, gbc);

    // Level label
    levelLabel = new JLabel("Level: 0");
    levelLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    levelLabel.setForeground(Color.WHITE);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(levelLabel, gbc);

    // Restart button
    restartButton = new JButton("Restart");
    stylizedButton(restartButton);
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(restartButton, gbc);

    // Exit button
    exitButton = new JButton("Exit");
    stylizedButton(exitButton);
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(exitButton, gbc);
  }

  /**
   * Sets the action listeners for the restart and exit buttons.
   * The restart triggers {@code onStartClicked()}, and exit closes the application.
   */
  protected void setListeners() {
    restartButton.addActionListener(e -> {
      if (listener != null) {
        listener.onStartClicked();
      }
    });

    exitButton.addActionListener(e -> exitApplication());
  }

  /**
   * Applies custom styling to buttons for a consistent look.
   *
   * @param button The {@link JButton} to be styled.
   */
  protected void stylizedButton(JButton button) {
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    button.setForeground(Color.WHITE);
    button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, true));
    button.setContentAreaFilled(false);
    button.setOpaque(false);
    button.setFocusPainted(false);
  }

  /**
   * Updates the score and level labels on the game over screen.
   *
   * @param score The {@link Score} object holding the current score.
   * @param level The {@link Level} object holding the current level.
   */
  public void updateScoreAndLevel(Score score, Level level) {
    scoreLabel.setText("Score: " + score.getCurrentScore());
    levelLabel.setText("Level: " + level.getCurrentLevel());
  }

  /**
   * Draws the background image for the panel.
   *
   * @param g The {@link Graphics} context used for painting.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
  }

  /**
   * Exits the application when the exit button is clicked.
   */
  protected void exitApplication() {
    System.exit(0);
  }
}
