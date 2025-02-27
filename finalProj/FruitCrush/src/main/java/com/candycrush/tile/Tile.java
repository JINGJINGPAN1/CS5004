package com.candycrush.tile;

import javax.swing.ImageIcon;

public class Tile {
  private String type;
  private ImageIcon imageIcon;

  public Tile(String type, String imagePath) {
    this.type = type;
    this.imageIcon = new ImageIcon(imagePath);
  }

  public String getType() {
    return type;
  }

  public ImageIcon getImageIcon() {
    return imageIcon;
  }

  public static Tile createApple(){
    return new Tile("apple", "images/fruiticon/apple.png");
  }

  public static Tile createGrapes(){
    return new Tile("grapes", "images/fruiticon/grapes.png");

  }

  public static Tile createOrange(){
    return new Tile("orange", "images/fruiticon/orange.png");
  }

  public static Tile createKiwi(){
    return new Tile("kiwi", "images/fruiticon/kiwi.png");
  }

  public static Tile createWatermelon(){
    return new Tile("watermelon", "images/fruiticon/watermelon.png");
  }

}
