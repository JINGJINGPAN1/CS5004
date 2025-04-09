package model;

public abstract class Item {
  protected int x, y;
  protected int width, height;
  protected boolean collected = false;

  public Item(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  // For collision checks
  public int getX() { return x; }
  public int getY() { return y; }
  public int getWidth() { return width; }
  public int getHeight() { return height; }

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

  public boolean isCollected() { return collected; }
  public void setCollected(boolean collected) { this.collected = collected; }

  // If you have different logic for gold vs. stone,
  // you can define an abstract method or override in child classes:
  public abstract int getScoreValue(int width, int height);
  public abstract double computeRetractSpeed(int width, int height);
}
