package view;

import java.awt.Color;
import java.awt.Graphics;
import model.Line;

/**
 * LineView is responsible for rendering the line on the screen.
 */
public class LineView {
  private Line line; // Reference to the Line model

  /**
   * Constructor for LineView.
   * @param line The Line model to be rendered.
   */
  public LineView(Line line) {
    this.line = line;
  }

  /**
   * Draws the line on the provided Graphics context.
   * @param g The Graphics context to draw on.
   */
  public void draw(Graphics g) {
    // paint red line
    g.setColor(Color.RED);
    g.drawLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
  }
}
