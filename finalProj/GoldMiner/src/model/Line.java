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

  // Constants for swing behavior
  private static final double MIN_ANGLE = 0.01;
  private static final double MAX_ANGLE = 0.99;
  private static final double ANGLE_STEP = 0.005;

  // Constants for retractable range
  private static final int MIN_LENGTH = 50;
  private static final int MAX_LENGTH = 1000;
  private static final int DELTA_LENGTH = 10;

  // base retract speed
  private double BASE_RETRACT_SPEED = 5.0;

  // Reference to whichever Gold/stone object we’re currently carrying (null if none)
//  private Gold grabbedGold = null;
//  private Stone grabbedStone = null;
  private Item grabbedItem = null;

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

      // After extending, check if the tip is out of bounds
      int tipX = getEndX();
      int tipY = getEndY();

      // If the tip is beyond the left/right/bottom/top edges of the game area,
      // we consider it "hitting a wall" and immediately switch to RETRACT
      if (tipX < 0 || tipX > 600 || tipY < 0 || tipY > 800) {
        setLineState(LineState.RETRACT);
      }
    } else {
      // once fully extended, switch to retract
      setLineState(LineState.RETRACT);
    }
  }

  // retract
  private void retract() {
    // figure out how fast we should retract
    double retractSpeed = (grabbedItem != null)
        ? grabbedItem.computeRetractSpeed(grabbedItem.getWidth(), grabbedItem.getHeight())
        : BASE_RETRACT_SPEED;

    if (length > MIN_LENGTH) {
      length -= retractSpeed;

      // If carrying gold, move it with the line tip
      if((grabbedItem != null && !grabbedItem.isCollected()) ) {
        int tipX = getEndX();
        int tipY = getEndY();

        // Center the gold on the line tip or offset as needed
        grabbedItem.setX(tipX - grabbedItem.getWidth() / 2);
        grabbedItem.setY(tipY - grabbedItem.getHeight() / 2);
      }
      // If carrying stone, move it with the line tip
//      if(grabbedStone != null && !grabbedStone.isCollected()) {
//        int tipX = getEndX();
//        int tipY = getEndY();
//        grabbedStone.setX(tipX - grabbedStone.getWidth() / 2);
//        grabbedStone.setY(tipY - grabbedStone.getHeight() / 2);
//      }

    } else {
      // Done retracting: if we had a grabbed gold, mark it collected
      if (grabbedItem != null) {
        grabbedItem.setCollected(true); // or move it off-screen
        grabbedItem = null;
      }
      setLineState(LineState.SWING);
    }
  }

//  private double calculateRetractSpeed() {
//    // if carrying gold, slow down or speed up based on gold size
//    if(grabbedItem != null) {
////      return computeSpeedForGoldSize(grabbedItem.getWidth(), grabbedItem.getHeight());
//      return grabbedItem.computeSpeedForGoldSize(grabbedItem.getWidth(), grabbedItem.getHeight());
//    }

    // if carrying gold, slow down or speed up based on stone size
//    if(grabbedStone != null) {
//      return computeSpeedForStoneSize(grabbedStone.getWidth(), grabbedStone.getHeight());
//    }
//    return BASE_RETRACT_SPEED;
//  }

//  private double computeSpeedForGoldSize(int width, int height) {
//    int area = width * height;
//    if(area < 1000) {
//      return BASE_RETRACT_SPEED;
//    }else if(area < 4000){
//      return BASE_RETRACT_SPEED * 0.9;
//    }else{
//      return BASE_RETRACT_SPEED * 0.7;
//    }
//  }

//  private double computeSpeedForStoneSize(int width, int height) {
//    int area = width * height;
//    if(area < 1000) {
//      return BASE_RETRACT_SPEED * 0.5;
//    }else if(area < 4000){
//      return BASE_RETRACT_SPEED * 0.2;
//    }else{
//      return BASE_RETRACT_SPEED * 0.1;
//    }
//  }

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

  // Gold getter/setter
//  public Gold getGrabbedGold() {
//    return grabbedGold;
//  }
//  public void setGrabbedGold(Gold grabbedGold) {
//    this.grabbedGold = grabbedGold;
//  }
//
//  public Stone getGrabbedStone() {
//    return grabbedStone;
//  }
//
//  public void setGrabbedStone(Stone grabbedStone) {
//    this.grabbedStone = grabbedStone;
//  }
  public Item getGrabbedItem() {
    return grabbedItem;
  }
  public void setGrabbedItem(Item grabbedItem) {
    this.grabbedItem = grabbedItem;
  }
}
