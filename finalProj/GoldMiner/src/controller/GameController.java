package controller;

import model.Gold;
import model.Line;
import model.LineState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Stone;

public class GameController {
  private Line line;
  private List<Gold> goldList;
  private List<Stone> stoneList;

  public GameController() {
    // Initialize the line
    line = new Line(
        380,  // startX
        180,  // startY
        50,  // initialLength
        1,    // initialDirection
        0.5   // initialAngleFactor
    );

    // Create multiple gold pieces
    goldList = new ArrayList<>();
    goldList.add(new Gold(300, 500, 52, 52));
    goldList.add(new Gold(500, 300, 82, 82));
    goldList.add(new Gold(100, 400, 102, 102));

    // create multiple Stone pieces
    stoneList = new ArrayList<>();
    stoneList.add(new Stone(115, 600, 41, 41));
    stoneList.add(new Stone(600, 600, 100, 100));
    stoneList.add(new Stone(250, 400, 76, 76));
  }

  public void update() {
    // 1) Check collision only if the line isn't already carrying something
    //    (meaning both grabbedGold and grabbedStone are null).
    if (line.getGrabbedGold() == null && line.getGrabbedStone() == null) {
      checkCollision();
    }
    // 2) Update the line each frame
    line.update();
    // 3) Remove gold that is marked as collected or stone that is marked as collected
    removeCollectedItems();
  }

  private void checkCollision() {
    // If line is swinging or grabbing outward, we can check collision
    // In your game logic, you might only check collision in certain states
    if (line.getLineState() == LineState.SWING || line.getLineState() == LineState.GRAB) {
      int tipX = line.getEndX();
      int tipY = line.getEndY();

      for (Gold gold : goldList) {
        if (!gold.isCollected() && isColliding(tipX, tipY, gold)) {
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

      for(Stone stone : stoneList) {
        if(!stone.isCollected() && isColliding(tipX, tipY, stone)) {
          System.out.println("Collision detected with a stone piece!");
          line.setGrabbedStone(stone);
          line.setLineState(LineState.RETRACT);
          break;
        }
      }
    }
  }

  private boolean isColliding(int x, int y, Gold gold) {
    return x > gold.getX() && x < gold.getX() + gold.getWidth()
        && y > gold.getY() && y < gold.getY() + gold.getHeight();
  }

  private boolean isColliding(int x, int y, Stone stone) {
    return x > stone.getX() && x < stone.getX() + stone.getWidth()
        && y > stone.getY() && y < stone.getY() + stone.getHeight();
  }



  private void removeCollectedItems() {
    // Optionally remove any gold that isCollected from the list,
    // so it's no longer drawn or processed.
    Iterator<Gold> iterator = goldList.iterator();
    while (iterator.hasNext()) {
      Gold g = iterator.next();
      if (g.isCollected()) {
        iterator.remove();
      }
    }

    Iterator<Stone> iterator1 = stoneList.iterator();
    while (iterator1.hasNext()) {
      Stone g = iterator1.next();
      if (g.isCollected()) {
        iterator1.remove();
      }
    }
  }

  // For the View
  public List<Gold> getGoldList() {
    return goldList;
  }
  public List<Stone> getStoneList() {
    return stoneList;
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
