package com.candycrush.tile;

import javax.swing.ImageIcon;

public class Tile {
  private TileType type;
  private ImageIcon imageIcon;
  private int x;
  private int y;

  public Tile(TileType type, String imagePath, int x, int y) {
    this.type = type;
    this.imageIcon = new ImageIcon(imagePath);
    this.x = x;
    this.y = y;
  }

  public TileType getType() {
    return type;
  }

  public ImageIcon getImageIcon() {
    return imageIcon;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public static Tile createTile(TileType type, int x, int y){
    String imagePath = "images/fruiticon/" + type.name().toLowerCase() +".png";
    return new Tile(type, imagePath, x, y);
  }
}
