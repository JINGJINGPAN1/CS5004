package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import model.GameTimer;

public class GameTimerView {
  private GameTimer gameTimer;
  public GameTimerView(GameTimer gameTimer) {
    this.gameTimer = gameTimer;
  }

  public void draw(Graphics g) {
     double timeLeft = gameTimer.getTimeLeft();
     int displayTime = (int) timeLeft;
     g.setColor(Color.RED);
     g.setFont(new Font("Arial", Font.BOLD, 30));
     g.drawString("Time: " + displayTime, 400, 50);
  }

}
