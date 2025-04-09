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
    goldImg = ResourceLoader.loadImage("resources/imgs/gold0.gif");
    stoneImg = ResourceLoader.loadImage("resources/imgs/rock1.png");
  }

  public void draw(Graphics g) {
    for (Item item : itemList) {
      if (!item.isCollected()) {
        if (item instanceof Gold && goldImg != null) {
          g.drawImage(goldImg, item.getX(), item.getY(), item.getWidth(), item.getHeight(), null);
        } else if (item instanceof Stone && stoneImg != null) {
          g.drawImage(stoneImg, item.getX(), item.getY(), item.getWidth(), item.getHeight(), null);
        }
      }
    }
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }
}

