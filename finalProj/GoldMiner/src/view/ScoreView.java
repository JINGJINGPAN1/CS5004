package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import model.Score;

public class ScoreView {
  private Score score;
  public ScoreView(Score score) {
    this.score = score;
  }

  public void draw(Graphics g) {
    int currentScore = score.getCurrentScore();
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 28));
    g.drawString("Score: " + currentScore, 20, 50);
  }

}
