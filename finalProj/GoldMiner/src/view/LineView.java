package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.Line;
import utils.ResourceLoader;

/**
 * LineView is responsible for rendering the line on the screen.
 */
public class LineView {
  private Line line; // Reference to the Line model
  private BufferedImage hookImage;

  /**
   * Constructor for LineView.
   * @param line The Line model to be rendered.
   */
  public LineView(Line line) {
    this.line = line;
    hookImage = ResourceLoader.loadImage("resources/imgs/hk.png");
  }

  /**
   * Draws the line on the provided Graphics context.
   * @param g The Graphics context to draw on.
   */
  public void draw(Graphics g) {
    // paint red line
    g.setColor(Color.RED);
    g.drawLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());

    if(hookImage != null) {
      int hookWidth = hookImage.getWidth(null);
      int hookHeight = hookImage.getHeight(null);

      int tipX = line.getEndX();
      int tipY = line.getEndY();

      // center the hook image at the tip.
      int drawX = tipX - hookWidth / 2;
      int drawY = tipY - hookHeight / 2;
      g.drawImage(hookImage, drawX, drawY, null);
    }

  }
}
