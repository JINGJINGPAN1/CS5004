package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import model.Gold;
import model.Item;
import model.Stone;
import utils.ResourceLoader;

public class ItemView {
  private List<Item> itemList;
  private BufferedImage goldImg;
  private BufferedImage stoneImg;

  public ItemView(List<Item> itemList) {
    this.itemList = itemList;
    goldImg = ResourceLoader.loadImage("imgs/gold0.png");
    stoneImg = ResourceLoader.loadImage("imgs/stone.png");
  }

  public void draw(Graphics g) {
    for (Item item : itemList) {
      if (!item.isCollected()) {
        if (item instanceof Gold) {
          g.drawImage(goldImg, item.getX(), item.getY(), item.getWidth(), item.getHeight(), null);
        } else if (item instanceof Stone) {
          g.drawImage(stoneImg, item.getX(), item.getY(), item.getWidth(), item.getHeight(), null);
        }
      }
    }
  }
}

