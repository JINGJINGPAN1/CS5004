package view;

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

public class GameOverScreen extends JPanel {

  private screenListener listener;
  private BufferedImage background;

  private JLabel scoreLabel;
  private JLabel levelLabel;

  private JButton restartButton;
  private JButton exitButton;

  public GameOverScreen(screenListener listener) {
    this.listener = listener;
    background = ResourceLoader.loadImage("resources/imgs/bg2.png");
    // GridBagLayout
    setLayout(new GridBagLayout());
    initializeUI();
  }

  private void initializeUI() {
    GridBagConstraints gbc = new GridBagConstraints();
    // gap
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL; // horizontally fill

    // scoreLabel
    scoreLabel = new JLabel("Score: 0");
    scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    scoreLabel.setForeground(Color.WHITE);
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(scoreLabel, gbc);

    // levelLabel
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

    // click events
    restartButton.addActionListener(e -> {
      if (listener != null) {
        listener.onStartClicked();
      }
    });

    exitButton.addActionListener(e -> System.exit(0));
  }

  private void stylizedButton(JButton button) {
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    button.setForeground(Color.WHITE);
    button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
    button.setContentAreaFilled(false);
    button.setOpaque(false);
    button.setFocusPainted(true);
  }

  public void updateScoreAndLevel(Score score, Level level) {
    scoreLabel.setText("Score: " + score.getCurrentScore());
    levelLabel.setText("Level: " + level.getCurrentLevel());
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
  }
}
