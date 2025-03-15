package view;

import java.awt.Color;
import java.awt.Graphics;
import model.LineState;

public class Line {
  int startX, startY, endX, endY;
  int length;
  double angleFactor;
  int direction;
  private static final double MIN_ANGLE = 0.1;
  private static final double MAX_ANGLE = 0.9;
  private static final double ANGLE_STEP = 0.005;
  private static final double MAX_LENGTH = 500;
  private static final double MIN_LENGTH = 100;
  private LineState lineState = LineState.SWING;

  public Line(int startX, int startY, int endX, int endY, int len, int direction, double angleFactor) {
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
    this.length = len;
    this.direction = direction;
    this.angleFactor = angleFactor;
//    System.out.println("Line initialized");
  }

  public void swing() {
    if (angleFactor < MIN_ANGLE) {
      direction = 1;
    } else if (angleFactor > MAX_ANGLE) {
      direction = -1;
    }
    angleFactor += ANGLE_STEP * direction;
  }

  public void grab(){
    if(length < MAX_LENGTH){
      length += 10;
    }else{
//      angleFactor = MIN_ANGLE; start from the angle of start point
      setState(LineState.RETRACT); // grab finished, switch to retracting state
    }
  }

  public void retract(){
    if(length > MIN_LENGTH){
      length -= 10;
    }else{
//      angleFactor = MIN_ANGLE; // start from the angle of start point
      setState(LineState.SWING); // retract finished, switch to swinging state
    }
  }

  public void setState(LineState lineState) {
    this.lineState = lineState;
  }

  public LineState getState() {
    return lineState;
  }

  public void update(){
    switch(lineState){
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
        throw new IllegalStateException("Unknown state: " + lineState);
    }

    System.out.println("Line state: " + lineState);
    System.out.println("Length: " + length);

  }

  public void paintSelf(Graphics g) {
    endX = (int) (startX + length * Math.cos(angleFactor * Math.PI));
    endY = (int) (startY + length * Math.sin(angleFactor * Math.PI));

    System.out.println("Drawing line from (" + startX + "," + startY + ") to (" + endX + "," + endY + ")");
    g.setColor(Color.RED);
    g.drawLine(startX, startY, endX, endY);
  }
}