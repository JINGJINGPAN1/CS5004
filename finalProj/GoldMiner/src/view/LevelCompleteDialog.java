package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utils.ResourceLoader;

public class LevelCompleteDialog extends JDialog {
  public BufferedImage displayImage;

  public LevelCompleteDialog(JFrame frame, screenListener listener) {
    super(frame, "Level Complete", true);
    displayImage = ResourceLoader.loadImage("resources/imgs/display.png");
    setSize(500, 300);
    setLocationRelativeTo(frame);

    JPanel backgroundPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (displayImage != null) {
          g.drawImage(displayImage, 0, 0, getWidth(), getHeight(), null);
        }
      }
    };
    backgroundPanel.setLayout(new GridBagLayout());
    setContentPane(backgroundPanel);

    //message
    JLabel messageLabel = new JLabel("<html>Congratulations!<br><br>"
        + "You've reached the target score.<br><br>"
        + "What would you like to do?</html>", SwingConstants.CENTER);
    messageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
    messageLabel.setForeground(Color.BLACK);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0.7;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.NONE;
    gbc.insets = new Insets(50, 20, 10, 20); // 上边距20，下边距10，左右边距20
    backgroundPanel.add(messageLabel, gbc);

    // buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    buttonPanel.setOpaque(false);
    JButton nextButton = new JButton("Next");
    stylizedButton(nextButton);
    JButton quitButton = new JButton("Quit");
    stylizedButton(quitButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(quitButton);

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 0.3;
    gbc.anchor = GridBagConstraints.PAGE_END;
    gbc.fill = GridBagConstraints.NONE;
    gbc.insets = new Insets(10, 20, 20, 20);
    backgroundPanel.add(buttonPanel, gbc);

    // click event
    nextButton.addActionListener(e -> {
      if(listener != null) {
        listener.onNextLevelClicked();
      }
      setVisible(false);
      dispose();
    });

    quitButton.addActionListener(e -> {
      if(listener != null) {
        listener.onReturnToMenuClicked();
      }
      setVisible(false);
      dispose();
    });
  }

  private void stylizedButton(JButton button) {
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    button.setForeground(Color.WHITE);
    button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
    button.setContentAreaFilled(false);
    button.setOpaque(false);
    button.setFocusPainted(true);
    button.setPreferredSize(new Dimension(100, 50));
  }
}
