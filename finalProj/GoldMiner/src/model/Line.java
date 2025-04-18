package model;

import java.awt.Rectangle;

/**
 * Represents a swinging line that can extend to grab items and retract back.
 * <p>
 * The line has three states: SWING, GRAB, and RETRACT, and behaves differently in each state.
 * It interacts with {@link Item} objects by grabbing and pulling them during retraction.
 * </p>
 */
public class Line {

  // --- Instance Fields ---

  /** Current x-coordinate where the line starts. */
  private int startX;

  /** Current y-coordinate where the line starts. */
  private int startY;

  /** Current length of the line. */
  private int length;

  /**
   * A factor (0.0 to 1.0) representing the current swing angle.
   * Used to calculate the rotation of the line.
   */
  private double angleFactor;

  /** Direction of swinging: 1 for right, -1 for left. */
  private int direction;

  /** Current state of the line (swinging, grabbing, or retracting). */
  private LineState lineState = LineState.SWING;

  // --- Constants for swing behavior ---

  private static final double MIN_ANGLE = 0.05;
  private static final double MAX_ANGLE = 0.95;
  private static final double ANGLE_STEP = 0.005;

  // --- Constants for length and speed ---

  private static final int MIN_LENGTH = 50;
  private static final int MAX_LENGTH = 1000;
  private static final int DELTA_LENGTH = 10;
  private double BASE_RETRACT_SPEED = 5.0;

  /** The item currently being grabbed (null if none). */
  private Item grabbedItem = null;

  // --- Fields for resetting state ---

  private final int INITIAL_START_X;
  private final int INITIAL_START_Y;
  private final int INITIAL_LENGTH;
  private final int INITIAL_DIRECTION;
  private final double INITIAL_ANGLE_FACTOR;

  /**
   * Constructs a Line object with specified position, initial length, direction, and angle.
   *
   * @param startX            Initial x-coordinate.
   * @param startY            Initial y-coordinate.
   * @param initialLength     Starting length of the line.
   * @param initialDirection  Swing direction (1 for right, -1 for left).
   * @param initialAngleFactor Initial swing angle factor (range 0.0 to 1.0).
   */
  public Line(int startX, int startY, int initialLength, int initialDirection, double initialAngleFactor) {
    this.startX = startX;
    this.startY = startY;
    this.length = initialLength;
    this.direction = initialDirection;
    this.angleFactor = initialAngleFactor;

    this.INITIAL_START_X = startX;
    this.INITIAL_START_Y = startY;
    this.INITIAL_LENGTH = initialLength;
    this.INITIAL_DIRECTION = initialDirection;
    this.INITIAL_ANGLE_FACTOR = initialAngleFactor;
  }

  /**
   * Updates the line's behavior based on its current state.
   */
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

  /**
   * Handles the swinging motion by adjusting the angle factor.
   */
  protected void swing() {
    if (angleFactor < MIN_ANGLE) {
      direction = 1;
    } else if (angleFactor > MAX_ANGLE) {
      direction = -1;
    }
    angleFactor += ANGLE_STEP * direction;
  }

  /**
   * Handles the grabbing motion by extending the line.
   * If it reaches out of bounds or the max length, it switches to retracting.
   */
  protected void grab() {
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

  /**
   * Handles the retracting motion. If an item is grabbed, it moves with the line.
   * When the line is fully retracted, the item is marked as collected.
   */
  protected void retract() {
    double retractSpeed = (grabbedItem != null)
        ? grabbedItem.computeRetractSpeed()
        : BASE_RETRACT_SPEED;

    if (length > MIN_LENGTH) {
      length -= retractSpeed;

      if (grabbedItem != null && !grabbedItem.isCollected()) {
        int tipX = getEndX();
        int tipY = getEndY();
        grabbedItem.setX(tipX - grabbedItem.getWidth() / 2);
        grabbedItem.setY(tipY - grabbedItem.getHeight() / 2);
      }
    } else {
      if (grabbedItem != null) {
        grabbedItem.setCollected(true);
        grabbedItem = null;
      }
      setLineState(LineState.SWING);
    }
  }

  /**
   * Calculates the x-coordinate of the line's tip.
   *
   * @return x-coordinate of the end point.
   */
  public int getEndX() {
    return (int) (startX + length * Math.cos(angleFactor * Math.PI));
  }

  /**
   * Calculates the y-coordinate of the line's tip.
   *
   * @return y-coordinate of the end point.
   */
  public int getEndY() {
    return (int) (startY + length * Math.sin(angleFactor * Math.PI));
  }

  /**
   * Sets the current state of the line.
   *
   * @param state The new line state.
   */
  public void setLineState(LineState state) {
    this.lineState = state;
  }

  /**
   * Gets the current state of the line.
   *
   * @return The current {@link LineState}.
   */
  public LineState getLineState() {
    return lineState;
  }

  /**
   * Starts the grabbing process by switching the state to GRAB.
   */
  public void startGrabbing() {
    setLineState(LineState.GRAB);
  }

  /**
   * Starts the retracting process by switching the state to RETRACT.
   */
  public void startRetracting() {
    setLineState(LineState.RETRACT);
  }

  // --- Getters and Setters ---

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
   * Resets the line to its initial state.
   * This is typically called when restarting or beginning a new level.
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

  // Optional utility method (commented out)
  /*
  public Rectangle getHookBounds() {
    int hookSize = 30;
    int tipX = getEndX();
    int tipY = getEndY();
    return new Rectangle(tipX - hookSize / 2, tipY - hookSize / 2, hookSize, hookSize);
  }
  */
}
