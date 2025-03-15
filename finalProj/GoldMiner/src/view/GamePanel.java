package view;

import controller.GameController;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
  private GameController gameController;

  public GamePanel(GameController gameController) {
    this.gameController = gameController;
    setDoubleBuffered(true); // double buffered in order to update and show the picture
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    gameController.draw(g);
  }
}
