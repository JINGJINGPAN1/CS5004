package model;
/**
 * Class representing a Gold item in the game.
 * This class extends the Item class and provides specific implementations for gold items.
 */
public class Gold extends Item {
  public Gold(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  /**
   * Method to get the score value based on the dimensions of the gold item.
   * @return The score value based on the dimensions.
   */
  @Override
  public int getScoreValue() {
    int baseScore = 50;
    int area = width * height;
    if (area < 1000) {
      return baseScore;
    } else if (area < 4000) {
      return baseScore * 2;
    } else {
      return baseScore * 5;
    }
  }

  /**
   * Method to compute the retract speed based on the dimensions of the gold item.
   * @return The retract speed based on the dimensions.
   */
  @Override
  public double computeRetractSpeed() {
    double baseRetractSpeed = 5.0;
    int area = width * height;
    if (area < 1000) {
      return baseRetractSpeed;
    } else if (area < 4000) {
      return baseRetractSpeed * 0.9;
    } else {
      return baseRetractSpeed * 0.7;
    }
  }

  /** Getter of x
   * @return x
   */
  public int getX() {
    return x;
  }

  /**
   * Setter of x
   * @param x x
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Getter of y
   * @return y
   */
  public int getY() {
    return y;
  }


  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public boolean isCollected() {
    return collected;
  }

  public void setCollected(boolean collected) {
    this.collected = collected;
  }
}