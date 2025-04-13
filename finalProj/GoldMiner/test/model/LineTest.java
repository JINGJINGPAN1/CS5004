package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * This class tests the Line class.
 */
class LineTest {
  Line line1;
  Line line2;
  Line line3;

  @BeforeEach
  void setUp() {
    line1 = new Line(100, 100, 200, 1, 0.5);
    line2 = new Line(200, 200, 300, -1, 0.8);
    line3 = spy(new Line(300, 300, 400, 1, 0.2));
  }

  /**
   * Test the update method of the Line class.
   */
  @Test
  void update() {
    line3.setLineState(LineState.SWING);
    line3.update();
    verify(line3).swing();
    verify(line3, never()).grab();
    verify(line3, never()).retract();
  }

  /**
   * Test the getEndX method of the Line class.
   */
  @Test
  void getEndX() {
    assertEquals(100, line1.getEndX());
    assertEquals(-42, line2.getEndX());
  }

  @Test
  void getEndY() {
  }

  @Test
  void setLineState() {
  }

  @Test
  void getLineState() {
  }

  @Test
  void startGrabbing() {
  }

  @Test
  void startRetracting() {
  }

  @Test
  void getStartX() {
  }

  @Test
  void setStartX() {
  }

  @Test
  void getStartY() {
  }

  @Test
  void setStartY() {
  }

  @Test
  void getLength() {
  }

  @Test
  void getAngleFactor() {
  }

  @Test
  void getDirection() {
  }

  @Test
  void setDirection() {
  }

  @Test
  void getGrabbedItem() {
  }

  @Test
  void setGrabbedItem() {
  }

  @Test
  void reset() {
  }
}