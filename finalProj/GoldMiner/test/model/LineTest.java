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

  /**
   * Test the getEndY method of the Line class.
   */
  @Test
  void getEndY() {
    assertEquals(300, line1.getEndY());
    assertEquals(376, line2.getEndY());
  }

  /**
   * Test the setLineState method of the Line class.
   */
  @Test
  void setLineState() {
    line1.setLineState(LineState.GRAB);
    assertEquals(LineState.GRAB, line1.getLineState());
    line2.setLineState(LineState.RETRACT);
    assertEquals(LineState.RETRACT, line2.getLineState());
  }

  /**
   * Test the getLineState method of the Line class.
   */
  @Test
  void getLineState() {
    line1.setLineState(LineState.SWING);
    assertEquals(LineState.SWING, line1.getLineState());
    line2.setLineState(LineState.GRAB);
    assertEquals(LineState.GRAB, line2.getLineState());
  }

  /**
   * Test the startGrabbing method of the Line class.
   */
  @Test
  void startGrabbing() {
    line1.startGrabbing();
    assertEquals(LineState.GRAB, line1.getLineState());
    line2.startGrabbing();
    assertEquals(LineState.GRAB, line2.getLineState());
  }

  /**
   * Test the startRetracting method of the Line class.
   */
  @Test
  void startRetracting() {
    line1.startRetracting();
    assertEquals(LineState.RETRACT, line1.getLineState());
    line2.startRetracting();
    assertEquals(LineState.RETRACT, line2.getLineState());
  }

  /**
   * Test the getStartX method of the Line class.
   */
  @Test
  void getStartX() {
    assertEquals(100, line1.getStartX());
    assertEquals(200, line2.getStartX());
  }

  /**
   * Test the setStartX method of the Line class.
   */
  @Test
  void setStartX() {
    line1.setStartX(150);
    assertEquals(150, line1.getStartX());
    line2.setStartX(250);
    assertEquals(250, line2.getStartX());
  }

  /**
   * Test the getStartY method of the Line class.
   */
  @Test
  void getStartY() {
    assertEquals(100, line1.getStartY());
    assertEquals(200, line2.getStartY());
  }

  /**
   * Test the setStartY method of the Line class.
   */
  @Test
  void setStartY() {
    line1.setStartY(150);
    assertEquals(150, line1.getStartY());
    line2.setStartY(250);
    assertEquals(250, line2.getStartY());
  }

  /**
   * Test the getLength method of the Line class.
   */
  @Test
  void getLength() {
    assertEquals(200, line1.getLength());
    assertEquals(300, line2.getLength());
  }

  /**
   * Test the getAngleFactor method of the Line class.
   */
  @Test
  void getAngleFactor() {
    assertEquals(.5, line1.getAngleFactor());
    assertEquals(.8, line2.getAngleFactor());
  }

  /**
   * Test the getDirection method of the Line class.
   */
  @Test
  void getDirection() {
    assertEquals(1, line1.getDirection());
    assertEquals(-1, line2.getDirection());
  }

  /**
   * Test the setDirection method of the Line class.
   */
  @Test
  void setDirection() {
    line1.setDirection(-1);
    assertEquals(-1, line1.getDirection());
    line2.setDirection(1);
    assertEquals(1, line2.getDirection());
  }

  /**
   * Test the getGrabbedItem method of the Line class.
   */
  @Test
  void getGrabbedItem() {
    assertNull(line1.getGrabbedItem());
    Item item = new Gold(0, 0, 1, 1);
    line2.setGrabbedItem(item);
    assertEquals(item, line2.getGrabbedItem());
  }

  /**
   * Test the setGrabbedItem method of the Line class.
   */
  @Test
  void setGrabbedItem() {
    Item item = new Gold(0, 0, 1, 1);
    line1.setGrabbedItem(item);
    assertEquals(item, line1.getGrabbedItem());
    line2.setGrabbedItem(null);
    assertNull(line2.getGrabbedItem());
  }

  /**
   * Test the reset method of the Line class.
   */
  @Test
  void reset() {
    line1.setStartX(150);
    line1.setStartY(150);
    line1.setDirection(0);
    line1.reset();
    assertEquals(100, line1.getStartX());
    assertEquals(100, line1.getStartY());
    assertEquals(200, line1.getLength());
    assertEquals(1, line1.getDirection());
    assertEquals(.5, line1.getAngleFactor());
    assertEquals(LineState.SWING, line1.getLineState());
    assertNull(line1.getGrabbedItem());

    line2.setStartX(250);
    line2.setStartY(250);
    line2.setGrabbedItem(new Stone(0, 0, 1, 1));
    line2.reset();
    assertEquals(200, line2.getStartX());
    assertEquals(200, line2.getStartY());
    assertEquals(300, line2.getLength());
    assertEquals(-1, line2.getDirection());
    assertEquals(.8, line2.getAngleFactor());
    assertEquals(LineState.SWING, line2.getLineState());
    assertNull(line2.getGrabbedItem());
  }
}