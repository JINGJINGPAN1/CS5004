package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import model.GameTimer;

/**
 * GameTimerView is responsible for displaying the game timer on the screen.
 */
public class GameTimerView {
  private GameTimer gameTimer; // Reference to the GameTimer model

  /**
   * Constructor for GameTimerView.
   * @param gameTimer The GameTimer model to be displayed.
   */
  public GameTimerView(GameTimer gameTimer) {
    this.gameTimer = gameTimer;
  }

  /**
   * Draws the game timer on the provided Graphics object.
   * @param g The Graphics object to draw on.
   */
  public void draw(Graphics g) {
     double timeLeft = gameTimer.getTimeLeft();
     int displayTime = (int) Math.ceil(timeLeft);
     g.setColor(Color.RED);
     g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
     g.drawString("Time: " + displayTime, 400, 120);
  }
}
