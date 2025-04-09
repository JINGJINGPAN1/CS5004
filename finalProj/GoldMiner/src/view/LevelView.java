package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import model.Level;

public class LevelView {
  private Level level;
  public LevelView(Level level) {
    this.level = level;
  }

  public void draw(Graphics g) {
    int currentLevel = level.getCurrentLevel();
    g.setColor(Color.BLUE);
    g.setFont(new Font("Arial", Font.BOLD, 28));
    g.drawString("Level: " + currentLevel, 20, 100);
  }

}
