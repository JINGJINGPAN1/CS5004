package view;

import java.awt.Color;
import java.awt.Graphics;
import model.Line;

public class LineView {
  private Line line;

  public LineView(Line line) {
    this.line = line;
  }

  public void draw(Graphics g) {
    // paint red line
    g.setColor(Color.RED);
    g.drawLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
  }
}
