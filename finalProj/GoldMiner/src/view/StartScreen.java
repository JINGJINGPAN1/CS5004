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
 * {@code StartScreen} is the welcome screen displayed when the game launches.
 * <p>
 * It shows a background image along with two buttons:
 * <ul>
 *   <li>"Start" — triggers the game to begin via {@link screenListener#onStartClicked()}</li>
 *   <li>"Exit" — closes the application</li>
 * </ul>
 */
public class StartScreen extends JPanel {

  /** Listener for screen-related actions. */
  private screenListener listener;

  /** Background image for the start screen. */
  private BufferedImage background;

  /** The "Start" button to begin the game. */
  protected JButton startButton;

  /** The "Exit" button to quit the game. */
  protected JButton exitButton;

  /**
   * Constructs the start screen and initializes its UI.
   *
   * @param listener A {@link screenListener} to notify when buttons are clicked.
   */
  public StartScreen(screenListener listener) {
    this.listener = listener;
    background = ResourceLoader.loadImage("resources/imgs/bg1.png");
    initializeUI();
  }

  /**
   * Initializes the layout and UI components, including the buttons and panel.
   */
  private void initializeUI() {
    setLayout(new BorderLayout());

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    buttonPanel.setOpaque(false);

    // Start button
    startButton = new JButton("Start");
    stylizedButton(startButton);

    // Exit button
    exitButton = new JButton("Exit");
    stylizedButton(exitButton);

    buttonPanel.add(startButton);
    buttonPanel.add(exitButton);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Registers listeners for the start and exit buttons.
   */
  protected void setListeners() {
    startButton.addActionListener(e -> {
      if (listener != null) {
        listener.onStartClicked();
      }
    });

    exitButton.addActionListener(e -> exitApplication());
  }

  /**
   * Applies consistent styling to buttons.
   *
   * @param button The {@link JButton} to style.
   */
  private void stylizedButton(JButton button) {
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
    button.setForeground(Color.WHITE);
    button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, true));
    button.setContentAreaFilled(false);
    button.setOpaque(false);
    button.setFocusPainted(true);
    button.setPreferredSize(new Dimension(120, 60));
  }

  /**
   * Paints the background image of the start screen.
   *
   * @param g The {@link Graphics} context used for drawing.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
  }

  /**
   * Exits the application when the Exit button is clicked.
   */
  protected void exitApplication() {
    System.exit(0);
  }
}
