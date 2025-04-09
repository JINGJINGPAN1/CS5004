package model;

import java.awt.Color;

public class Stone extends Item{
  public Stone(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  @Override
  public int getScoreValue(int width, int height) {
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

  @Override
  public double computeRetractSpeed(int width, int height) {
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
