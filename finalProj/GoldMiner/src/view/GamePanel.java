package view;

import controller.GameController;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Responsible for painting models in the Jpanel
 */
public class GamePanel extends JPanel {
  private GameController gameController;
  private BackgroundView backgroundView;
  private LineView lineView;
  private GoldView goldView;

  public GamePanel(GameController gameController,
      BackgroundView backgroundView,
      LineView lineView,
      GoldView goldView) {
    this.gameController = gameController;
    this.backgroundView = backgroundView;
    this.lineView = lineView;
    this.goldView = goldView;
    setDoubleBuffered(true);  // use double buffering to prevent flickering when updating the display
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // paint background
    if (backgroundView != null) {
      backgroundView.draw(g);
    }
    // paint line
    if (lineView != null) {
      lineView.draw(g);
    }
    // paint gold
    if (goldView != null) {
      goldView.draw(g);
    }
  }
}
