package controller;

import model.Gold;
import model.Line;
import model.LineState;

public class GameController {
  private Line line;
  private Gold gold;

  public GameController() {
    // 初始化 Model
    this.line = new Line(
        380,   // startX
        180,   // startY
        100,   // initialLength
        1,     // initialDirection
        0      // initialAngleFactor
    );
    this.gold = new Gold();
  }

  public void startGrabbing() {
    line.startGrabbing();
  }

  public void startRetracting() {
    line.startRetracting();
  }


  public void update() {
    line.update();
  }

  public Line getLine() {
    return line;
  }

  public Gold getGold() {
    return gold;
  }
}
