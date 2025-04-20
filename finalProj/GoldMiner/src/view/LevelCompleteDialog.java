package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
import utils.ResourceLoader;

/**
 * {@code LevelCompleteDialog} is a modal pop-up that appears when a player completes a level.
 * <p>
 * It displays a congratulatory message and provides two options:
 * <ul>
 *   <li>Next Level</li>
 *   <li>Quit Game (Return to Menu)</li>
 * </ul>
 * The dialog uses a custom background and stylized buttons.
 */
public class LevelCompleteDialog extends JDialog {

  /** Background image displayed in the dialog. */
  private BufferedImage displayImage;

  /** Button to proceed to the next level. */
  private JButton nextButton;

  /** Button to quit the game and return to the main menu. */
  private JButton quitButton;

  /** Label for displaying the congratulatory message. */
  private JLabel messageLabel;

  /** Listener to handle screen navigation actions. */
  private ScreenListener listener;

  /**
   * Constructs the LevelCompleteDialog with custom UI elements and action handlers.
   *
   * @param frame    The parent frame that owns this dialog.
   * @param listener A {@link ScreenListener} to handle user choices.
   */
  public LevelCompleteDialog(JFrame frame, ScreenListener listener) {
    super(frame, "Level Complete", true);
    this.listener = listener;
    displayImage = ResourceLoader.loadImage("resources/imgs/display.png");
    setSize(500, 300);
    setLocationRelativeTo(frame);
    initializeUI();
  }

  /**
   * Initializes the user interface components and layout of the dialog.
   * Includes background, message label, and buttons.
   */
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

    // Message
    messageLabel = new JLabel("<html><div style='text-align: center;'>"
        + "🎉 Congratulations!"
        + "<br><br><span style='white-space: nowrap;'>What would you like to do?</span></div></html>",
        SwingConstants.CENTER);
    messageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
    messageLabel.setForeground(Color.YELLOW);
    messageLabel.setAlignmentX(CENTER_ALIGNMENT);
    messageLabel.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 20));
    backgroundPanel.add(messageLabel);
    backgroundPanel.add(Box.createVerticalGlue());

    // Buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setOpaque(false);
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    nextButton = new JButton(" Next Level ");
    stylizedButton(nextButton);
    nextButton.setAlignmentX(CENTER_ALIGNMENT);
    buttonPanel.add(nextButton);
    buttonPanel.add(Box.createVerticalStrut(15));

    quitButton = new JButton(" Quit Game ");
    stylizedButton(quitButton);
    quitButton.setAlignmentX(CENTER_ALIGNMENT);
    buttonPanel.add(quitButton);

    buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
    backgroundPanel.add(buttonPanel);
    backgroundPanel.add(Box.createVerticalGlue());

    // Button Actions
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

  /**
   * Applies a consistent style to buttons.
   *
   * @param button The {@link JButton} to be styled.
   */
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
