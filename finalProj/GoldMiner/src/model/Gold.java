package model;

/**
 * Represents a Gold item in the game.
 * <p>
 * This class extends {@link Item} and overrides methods specific to gold,
 * such as computing score and retract speed based on size.
 * </p>
 */
public class Gold extends Item {

  /**
   * Constructs a Gold object with the given position and size.
   *
   * @param x      The x-coordinate of the gold.
   * @param y      The y-coordinate of the gold.
   * @param width  The width of the gold item.
   * @param height The height of the gold item.
   */
  public Gold(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  /**
   * Returns the score value of the gold based on its size.
   * <ul>
   *   <li>Area &lt; 1000: 50 points</li>
   *   <li>1000 ≤ Area &lt; 4000: 100 points</li>
   *   <li>Area ≥ 4000: 250 points</li>
   * </ul>
   *
   * @return The score value for this gold item.
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
   * Computes the retract speed of the gold item based on its size.
   * <ul>
   *   <li>Area &lt; 1000: 5.0 speed</li>
   *   <li>1000 ≤ Area &lt; 4000: 4.5 speed</li>
   *   <li>Area ≥ 4000: 3.5 speed</li>
   * </ul>
   *
   * @return The retract speed.
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

  /**
   * Gets the x-coordinate of the gold.
   *
   * @return The x value.
   */
  public int getX() {
    return x;
  }

  /**
   * Sets the x-coordinate of the gold.
   *
   * @param x The new x value.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Gets the y-coordinate of the gold.
   *
   * @return The y value.
   */
  public int getY() {
    return y;
  }

  /**
   * Sets the y-coordinate of the gold.
   *
   * @param y The new y value.
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Gets the width of the gold item.
   *
   * @return The width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets the width of the gold item.
   *
   * @param width The new width.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Gets the height of the gold item.
   *
   * @return The height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the height of the gold item.
   *
   * @param height The new height.
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Checks if the gold item has been collected.
   *
   * @return True if collected, false otherwise.
   */
  public boolean isCollected() {
    return collected;
  }

  /**
   * Sets the collected state of the gold item.
   *
   * @param collected True if collected, false otherwise.
   */
  public void setCollected(boolean collected) {
    this.collected = collected;
  }
}