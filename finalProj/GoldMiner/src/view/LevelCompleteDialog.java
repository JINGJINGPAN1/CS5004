package view;

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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utils.ResourceLoader;

public class LevelCompleteDialog extends JDialog {
  private BufferedImage displayImage;
  private JButton nextButton;
  private JButton quitButton;
  private JLabel messageLabel;
  private screenListener listener;


  public LevelCompleteDialog(JFrame frame, screenListener listener) {
    super(frame, "Level Complete", true);
    this.listener = listener;
    displayImage = ResourceLoader.loadImage("resources/imgs/display.png");
    setSize(500, 300);
    setLocationRelativeTo(frame);
    initializeUI();
  }

  protected void initializeUI() {
    JPanel backgroundPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (displayImage != null) {
          g.drawImage(displayImage, 0, 0, getWidth(), getHeight(), null);
        }
      }
    };
    backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
    setContentPane(backgroundPanel);

    // message
    messageLabel = new JLabel("<html><div style='text-align: center;'>"
        + "🎉 Congratulations!"
        + "<br><br><span style='white-space: nowrap;'>What would you like to do?</span></div></html>",
        SwingConstants.CENTER);
    messageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    messageLabel.setForeground(Color.YELLOW);
    messageLabel.setAlignmentX(CENTER_ALIGNMENT);
    messageLabel.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 20)); // top padding
    backgroundPanel.add(messageLabel);

    // flexible vertical space
    backgroundPanel.add(Box.createVerticalGlue());

    // buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setOpaque(false);
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    nextButton = new JButton(" Next Level ");
    stylizedButton(nextButton);
    nextButton.setAlignmentX(CENTER_ALIGNMENT);
    buttonPanel.add(nextButton);

    buttonPanel.add(Box.createVerticalStrut(15)); // spacing

    quitButton = new JButton(" Quit Game ");
    stylizedButton(quitButton);
    quitButton.setAlignmentX(CENTER_ALIGNMENT);
    buttonPanel.add(quitButton);

    buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
    backgroundPanel.add(buttonPanel);

    backgroundPanel.add(Box.createVerticalGlue()); // bottom glue

    // click event
    nextButton.addActionListener(e -> {
      if (listener != null) {
        listener.onNextLevelClicked();
      }
      setVisible(false);
      dispose();
    });

    quitButton.addActionListener(e -> {
      if (listener != null) {
        listener.onReturnToMenuClicked();
      }
      setVisible(false);
      dispose();
    });
  }

  protected void stylizedButton(JButton button) {
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
    button.setForeground(Color.WHITE);
    button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
    button.setContentAreaFilled(false);
    button.setFocusPainted(false);
    button.setOpaque(false);
    button.setPreferredSize(new Dimension(160, 40));
  }
}
