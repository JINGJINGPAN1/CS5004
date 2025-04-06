package model;

public class Line {
  // start coordinate
  private int startX, startY;
  // length
  private int length;
  // angleFactor（0~1）
  private double angleFactor;
  // 1: Swing to right, -1: Swing to left
  private int direction;
  // line state
  private LineState lineState = LineState.SWING;

  // swing angle factor range
  private static final double MIN_ANGLE = 0.1;
  private static final double MAX_ANGLE = 0.9;
  private static final double ANGLE_STEP = 0.005;

  // retractable range
  private static final int MIN_LENGTH = 100;
  private static final int MAX_LENGTH = 500;
  private static final int DELTA_LENGTH = 10;

  public Line(int startX, int startY, int initialLength, int initialDirection, double initialAngleFactor) {
    this.startX = startX;
    this.startY = startY;
    this.length = initialLength;
    this.direction = initialDirection;
    this.angleFactor = initialAngleFactor;
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

  // swing
  private void swing() {
    if (angleFactor < MIN_ANGLE) {
      direction = 1;
    } else if (angleFactor > MAX_ANGLE) {
      direction = -1;
    }
    angleFactor += ANGLE_STEP * direction;
  }

  // grab
  private void grab() {
    if (length < MAX_LENGTH) {
      length += DELTA_LENGTH;
    } else {
      setLineState(LineState.RETRACT);
    }
  }

  // retract
  private void retract() {
    if (length > MIN_LENGTH) {
      length -= DELTA_LENGTH;
    } else {
      setLineState(LineState.SWING);
    }
  }

  // endpoint of X
  public int getEndX() {
    return (int) (startX + length * Math.cos(angleFactor * Math.PI));
  }

  // endpoint of Y
  public int getEndY() {
    return (int) (startY + length * Math.sin(angleFactor * Math.PI));
  }

  public void setLineState(LineState state) {
    this.lineState = state;
  }

  public LineState getLineState() {
    return lineState;
  }

  // start grabbing
  public void startGrabbing() {
    setLineState(LineState.GRAB);
  }

  // start retracting
  public void startRetracting() {
    setLineState(LineState.RETRACT);
  }

  // getter / setter
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
}
