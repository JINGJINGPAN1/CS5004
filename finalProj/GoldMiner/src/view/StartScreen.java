package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import utils.ResourceLoader;

/**
 * StartScreen displays "Welcome to Gold Miner" and a "Start" button.
 * When the button is clicked, it notifies the listener.
 */
public class StartScreen extends JPanel {

  private screenListener listener;
  private BufferedImage background;
  private JButton startButton;
  private JButton exitButton;

  public StartScreen(screenListener listener) {
    this.listener = listener;
    background = ResourceLoader.loadImage("resources/imgs/bg1.png");
    initializeUI();
  }

  private void initializeUI() {

    setLayout(new BorderLayout());

    // create the buttonPanel where 3 buttons will be placed
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    buttonPanel.setOpaque(false);

    // create Start button
    startButton = new JButton("Start");
    stylizedButton(startButton);

    // create Exit button
    exitButton = new JButton("Exit");
    stylizedButton(exitButton);

    // 将三个按钮添加到按钮面板中
    buttonPanel.add(startButton);
    buttonPanel.add(exitButton);

    // 将按钮面板添加到整个页面的底部
    add(buttonPanel, BorderLayout.SOUTH);

    // Button click event: call listener's onStartClicked()
    startButton.addActionListener(e -> {
      if (listener != null) {
        listener.onStartClicked();
      }
    });

    // Exit click event
    exitButton.addActionListener(e -> {System.exit(0);});
  }

  private void stylizedButton(JButton button) {
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    button.setForeground(Color.WHITE);
    button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
    button.setContentAreaFilled(false);
    button.setOpaque(false);
    button.setFocusPainted(true);
    button.setPreferredSize(new Dimension(120, 60));
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
  }
}
