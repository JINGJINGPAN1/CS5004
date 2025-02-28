package com.candycrush.tile;

import javax.swing.ImageIcon;

public class Tile {
  private TileType type;
  private ImageIcon imageIcon;

  public Tile(TileType type, String imagePath) {
    this.type = type;
    this.imageIcon = new ImageIcon(imagePath);
  }

  public TileType getType() {
    return type;
  }

  public ImageIcon getImageIcon() {
    return imageIcon;
  }

  public static Tile createTile(TileType type){
    String imagePath = "images/fruiticon/" + type.name().toLowerCase() +".png";
    return new Tile(type, imagePath);
  }
}
