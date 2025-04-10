package model;

import java.awt.Color;
/**
 * Class representing a Stone item in the game.
 * This class extends the Item class and provides specific implementations for stone items.
 */
public class Stone extends Item{
  /**
   * Constructor to initialize the stone with its position and dimensions.
   *
   * @param x      The x-coordinate of the stone.
   * @param y      The y-coordinate of the stone.
   * @param width  The width of the stone.
   * @param height The height of the stone.
   */
  public Stone(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  /**
   * Method to get the score value of the stone based on its dimensions.
   * @return The score value of the stone.
   */
  @Override
  public int getScoreValue() {
    int baseScore = 20;
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
   * Method to compute the retract speed of the stone based on its dimensions.
   * @return The retract speed of the stone.
   */
  @Override
  public double computeRetractSpeed() {
    double baseRetractSpeed = 5.0;
    int area = width * height;
    if (area < 1000) {
      return baseRetractSpeed * 0.5;
    } else if (area < 4000) {
      return baseRetractSpeed * 0.2;
    } else {
      return baseRetractSpeed * 0.1;
    }
  }
}
