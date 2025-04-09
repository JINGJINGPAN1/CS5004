package model;

import java.awt.Color;

public class Stone extends Item{
  public Stone(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  @Override
  public int getScoreValue(int width, int height) {
    int area = width * height;
    int baseScore = 50;
    if(area < 1000) {
      return baseScore;
    }else if(area < 4000){
      return baseScore * 2;
    }else{
      return baseScore * 5;
    }
  }

  @Override
  public int computeSpeedForGoldSize(int width, int height) {
    int BASE_RETRACT_SPEED = 5;
    int area = width * height;
    if(area < 1000) {
      BASE_RETRACT_SPEED *= 0.5;
    }else if(area < 4000){
      BASE_RETRACT_SPEED *= 0.2;
    }else{
      BASE_RETRACT_SPEED *= 0.1;
    }
    return BASE_RETRACT_SPEED;
  }
}
