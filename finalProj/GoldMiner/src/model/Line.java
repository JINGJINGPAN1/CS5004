package model;

import java.awt.Rectangle;

public class Line {
  // current start coordinates
  private int startX, startY;
  // current length of the line
  private int length;
  // current angle factor (range 0~1)
  private double angleFactor;
  // 1: Swing to right, -1: Swing to left
  private int direction;
  // current state of the line (swing, grab, retract)
  private LineState lineState = LineState.SWING;

  // Constants for swing behavior
  private static final double MIN_ANGLE = 0.05;
  private static final double MAX_ANGLE = 0.95;
  private static final double ANGLE_STEP = 0.005;

  // Constants for retractable range
  private static final int MIN_LENGTH = 50;
  private static final int MAX_LENGTH = 1000;
  private static final int DELTA_LENGTH = 10;

  // base retract speed
  private double BASE_RETRACT_SPEED = 5.0;

  // Reference to the item currently grabbed (null if none)
  private Item grabbedItem = null;

  // Fields to store the initial state for resetting
  private final int INITIAL_START_X;
  private final int INITIAL_START_Y;
  private final int INITIAL_LENGTH;
  private final int INITIAL_DIRECTION;
  private final double INITIAL_ANGLE_FACTOR;

  public Line(int startX, int startY, int initialLength, int initialDirection, double initialAngleFactor) {
    this.startX = startX;
    this.startY = startY;
    this.length = initialLength;
    this.direction = initialDirection;
    this.angleFactor = initialAngleFactor;

    // Save initial values for reset later
    this.INITIAL_START_X = startX;
    this.INITIAL_START_Y = startY;
    this.INITIAL_LENGTH = initialLength;
    this.INITIAL_DIRECTION = initialDirection;
    this.INITIAL_ANGLE_FACTOR = initialAngleFactor;
  }

  public void update() {
    switch (lineState) {
      case SWING:
        swing();
        break;
      case GRAB:
        grab();
        break;
      case RETRACT:
        retract();
        break;
      default:
        throw new IllegalStateException("Unknown LineState: " + lineState);
    }
  }

  // Swing behavior: adjust angleFactor based on direction
  private void swing() {
    if (angleFactor < MIN_ANGLE) {
      direction = 1;
    } else if (angleFactor > MAX_ANGLE) {
      direction = -1;
    }
    angleFactor += ANGLE_STEP * direction;
  }

  // Grab behavior: extend the line until reaching maximum length or hitting bounds
  private void grab() {
    if (length < MAX_LENGTH) {
      length += DELTA_LENGTH;
      int tipX = getEndX();
      int tipY = getEndY();
      if (tipX < 0 || tipX > 600 || tipY < 0 || tipY > 800) {
        setLineState(LineState.RETRACT);
      }
    } else {
      setLineState(LineState.RETRACT);
    }
  }

  // Retract behavior: shorten the line while moving the grabbed item if exists
  private void retract() {
    double retractSpeed = (grabbedItem != null)
        ? grabbedItem.computeRetractSpeed()
        : BASE_RETRACT_SPEED;

    if (length > MIN_LENGTH) {
      length -= retractSpeed;

      if (grabbedItem != null && !grabbedItem.isCollected()) {
        int tipX = getEndX();
        int tipY = getEndY();
        // Update grabbed item's position to center it on the line tip
        grabbedItem.setX(tipX - grabbedItem.getWidth() / 2);
        grabbedItem.setY(tipY - grabbedItem.getHeight() / 2);
      }
    } else {
      // Once retracted completely, mark the grabbed item as collected if exists.
      if (grabbedItem != null) {
        grabbedItem.setCollected(true);
        grabbedItem = null;
      }
      setLineState(LineState.SWING);
    }
  }

  // Calculate the x-coordinate of the line tip.
  public int getEndX() {
    return (int) (startX + length * Math.cos(angleFactor * Math.PI));
  }

  // Calculate the y-coordinate of the line tip.
  public int getEndY() {
    return (int) (startY + length * Math.sin(angleFactor * Math.PI));
  }

  public void setLineState(LineState state) {
    this.lineState = state;
  }

  public LineState getLineState() {
    return lineState;
  }

  // Start grabbing by setting the state to GRAB.
  public void startGrabbing() {
    setLineState(LineState.GRAB);
  }

  // Start retracting by setting the state to RETRACT.
  public void startRetracting() {
    setLineState(LineState.RETRACT);
  }

  // Getters and setters for the line's properties
  public int getStartX() {
    return startX;
  }

  public void setStartX(int startX) {
    this.startX = startX;
  }

  public int getStartY() {
    return startY;
  }

  public void setStartY(int startY) {
    this.startY = startY;
  }

  public int getLength() {
    return length;
  }

  public double getAngleFactor() {
    return angleFactor;
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public Item getGrabbedItem() {
    return grabbedItem;
  }

  public void setGrabbedItem(Item grabbedItem) {
    this.grabbedItem = grabbedItem;
  }

  /**
   * Resets the line to its initial state. This method is called when entering the next level.
   */
  public void reset() {
    this.startX = INITIAL_START_X;
    this.startY = INITIAL_START_Y;
    this.length = INITIAL_LENGTH;
    this.direction = INITIAL_DIRECTION;
    this.angleFactor = INITIAL_ANGLE_FACTOR;
    this.lineState = LineState.SWING;
    this.grabbedItem = null;
  }

  public Rectangle getHookBounds() {
    int hookSize = 30; // Change this value as needed to match the hook image size
    int tipX = getEndX();
    int tipY = getEndY();
    return new Rectangle(tipX - hookSize / 2, tipY - hookSize / 2, hookSize, hookSize);
  }
}
