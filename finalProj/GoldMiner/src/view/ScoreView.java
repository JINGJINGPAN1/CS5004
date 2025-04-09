package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import model.Score;

/**
 * ScoreView is responsible for displaying the current score on the screen.
 */
public class ScoreView {
  private Score score; // The model that holds the score data

  /**
   * Constructor for ScoreView.
   * @param score The Score model to be displayed.
   */
  public ScoreView(Score score) {
    this.score = score;
  }

  /**
   * Draws the current score on the provided Graphics context.
   * @param g The Graphics context to draw on.
   */
  public void draw(Graphics g) {
    int currentScore = score.getCurrentScore();
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 28));
    g.drawString("Score: " + currentScore, 20, 50);
  }

}
