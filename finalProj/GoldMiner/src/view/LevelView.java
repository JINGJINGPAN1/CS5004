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
    g.setColor(Color.GREEN);
    g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
    g.drawString("Level: " + currentLevel, 400, 70);

    int targetScore = level.getTargetScore();
    g.setColor(Color.BLUE);
    g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
    g.drawString("Target: " + targetScore, 20, 120);
  }

}
