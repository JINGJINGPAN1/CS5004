package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import model.Level;

/**
 * {@code LevelView} is responsible for rendering level-related information
 * such as the current level and target score onto the game screen.
 */
public class LevelView {

  /** The {@link Level} model containing current level data. */
  private Level level;

  /**
   * Constructs a {@code LevelView} with the specified {@link Level} model.
   *
   * @param level The level model to visualize.
   */
  public LevelView(Level level) {
    this.level = level;
  }

  /**
   * Renders the current level and target score to the provided graphics context.
   *
   * @param g The {@link Graphics} context used for drawing.
   */
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
