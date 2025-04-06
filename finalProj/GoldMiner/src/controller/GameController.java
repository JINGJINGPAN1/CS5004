package controller;

import model.Gold;
import model.Line;
import model.LineState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController {
  private Line line;
  private List<Gold> goldList;

  public GameController() {
    // Initialize the line
    line = new Line(
        380,  // startX
        180,  // startY
        100,  // initialLength
        1,    // initialDirection
        0.5   // initialAngleFactor
    );

    // Create multiple gold pieces
    goldList = new ArrayList<>();
    goldList.add(new Gold(300, 500, 52, 52));
    goldList.add(new Gold(500, 300, 82, 82));
    goldList.add(new Gold(100, 400, 102, 102));
  }

  public void update() {
    // 1) If line is NOT already carrying a gold piece, check collisions with all gold
    if (line.getGrabbedGold() == null) {
      checkCollision();
    }

    // 2) Update the line each frame
    line.update();

    // 3) Remove gold that is marked as collected
    removeCollectedGold();
  }

  private void checkCollision() {
    // If line is swinging or grabbing outward, we can check collision
    // In your game logic, you might only check collision in certain states
    if (line.getLineState() == LineState.SWING || line.getLineState() == LineState.GRAB) {
      int tipX = line.getEndX();
      int tipY = line.getEndY();

      for (Gold gold : goldList) {
        if (!gold.isCollected()) {
          if (tipX > gold.getX() && tipX < gold.getX() + gold.getWidth()
              && tipY > gold.getY() && tipY < gold.getY() + gold.getHeight()) {

            // We have collision
            System.out.println("Collision detected with a gold piece!");
            // Let the line hold this gold
            line.setGrabbedGold(gold);
            // Switch line to RETRACT immediately
            line.setLineState(LineState.RETRACT);
            // Break so we only grab one gold at a time
            break;
          }
        }
      }
    }
  }

  private void removeCollectedGold() {
    // Optionally remove any gold that isCollected from the list,
    // so it's no longer drawn or processed.
    Iterator<Gold> iterator = goldList.iterator();
    while (iterator.hasNext()) {
      Gold g = iterator.next();
      if (g.isCollected()) {
        iterator.remove();
      }
    }
  }

  // For the View
  public List<Gold> getGoldList() {
    return goldList;
  }

  public Line getLine() {
    return line;
  }

  // Methods that handle user input
  public void startGrabbing() {
    line.startGrabbing();
  }

  public void startRetracting() {
    line.startRetracting();
  }
}
