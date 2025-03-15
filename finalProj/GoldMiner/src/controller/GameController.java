package controller;

import java.awt.Graphics;
import model.LineState;
import view.Background;
import view.Gold;
import view.Line;

public class GameController {
  private Background background;
  private Line line;
  private Gold gold;

  public GameController() {
    background = new Background();
    line = new Line(380, 180, 500, 500, 100, 1, 0);
    gold = new Gold();
  }

  public void update() {
    line.update();
  }

  public void startGrabbing(){
    line.setState(LineState.GRAB);
  }

  public void startRetracting(){
    line.setState(LineState.RETRACT);
  }

  public void draw(Graphics g) {
    System.out.println("Drawing background and line");
    background.paintSelf(g);
    line.paintSelf(g);
    gold.paintSelf(g);
  }
}