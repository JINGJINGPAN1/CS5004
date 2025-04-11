package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utils.ResourceLoader;

/**
 * StartScreen displays "Welcome to Gold Miner" and a "Start" button.
 * When the button is clicked, it notifies the listener.
 */
public class StartScreen extends JPanel {

  private StartScreenListener listener;
  private BufferedImage background;

  public StartScreen(StartScreenListener listener) {
    this.listener = listener;
    background = ResourceLoader.loadImage("resources/imgs/bg2.png");
    initializeUI();
  }

  private void initializeUI() {

    setLayout(new BorderLayout());

    // Start Button
    JButton startButton = new JButton("Start");
    startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
    startButton.setPreferredSize(new Dimension(100, 50));
    startButton.setBorderPainted(false);
    startButton.setFocusPainted(false);
    startButton.setForeground(Color.WHITE);
    add(startButton, BorderLayout.SOUTH);

    // Button click event: call listener's onStartClicked()
    startButton.addActionListener(e -> {
      if (listener != null) {
        listener.onStartClicked();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int width = getWidth();
    int height = getHeight();
    g.drawImage(background, 0, 0, width, height, null);
  }
}
