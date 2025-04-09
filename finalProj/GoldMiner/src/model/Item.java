package model;

/**
 * Abstract class representing an item in the game.
 * This class serves as a base for different types of items.
 */
public abstract class Item {
  protected int x, y; // Position of the item
  protected int width, height; // Dimensions of the item
  protected boolean collected = false; // Flag to check if the item is collected

  /**
   * Constructor to initialize the item with its position and dimensions.
   *
   * @param x      The x-coordinate of the item.
   * @param y      The y-coordinate of the item.
   * @param width  The width of the item.
   * @param height The height of the item.
   */
  public Item(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  // For collision checks

  /**
   * Getter methods for the item's position and dimensions.
   * @return The x-coordinate, y-coordinate, width, and height of the item.
   */
  public int getX() { return x; }
  public int getY() { return y; }
  public int getWidth() { return width; }
  public int getHeight() { return height; }

  /**
   * Setter methods for the item's position and dimensions.
   */

  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }
  public void setWidth(int width) {
    this.width = width;
  }
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Method to check if the item is collected.
   * @return true if the item is collected, false otherwise.
   */
  public boolean isCollected() { return collected; }

  /**
   * Method to set the collected status of the item.
   * @param collected The new collected status.
   */
  public void setCollected(boolean collected) { this.collected = collected; }

  // If you have different logic for gold vs. stone,
  // you can define an abstract method or override in child classes:
  /**
   * Abstract method to get the score value of the item based on its dimensions.
   * @return The score value of the item.
   */
  public abstract int getScoreValue();
  /**
   * Abstract method to compute the retract speed of the item based on its dimensions.
   * @return The retract speed of the item.
   */
  public abstract double computeRetractSpeed();
}
