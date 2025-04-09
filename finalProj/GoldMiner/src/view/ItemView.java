package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import model.Gold;
import model.Item;
import model.Stone;
import utils.ResourceLoader;

/**
 * ItemView is responsible for rendering items (Gold and Stone) on the screen.
 */
public class ItemView {
  private List<Item> itemList; // List of items to be rendered
  private BufferedImage goldImg; // Image for gold
  private BufferedImage stoneImg; // Image for stone

  /**
   * Constructor for ItemView.
   * @param itemList
   */
  public ItemView(List<Item> itemList) {
    this.itemList = itemList;
    goldImg = ResourceLoader.loadImage("resources/imgs/gold0.gif");
    stoneImg = ResourceLoader.loadImage("resources/imgs/rock1.png");
  }

  /**
   * Draws the items on the screen.
   * @param g Graphics object to draw on
   */
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

  /**
   * Sets the list of items to be rendered.
   * @param itemList List of items
   */
  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }
}

