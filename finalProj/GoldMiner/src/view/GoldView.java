package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.Gold;
import utils.ResourceLoader;

public class GoldView {
  private Gold gold;
  // examples: 3 gold pictures
  private BufferedImage goldImage0;
  private BufferedImage goldImage1;
  private BufferedImage goldImage2;

  public GoldView(Gold gold) {
    this.gold = gold;
    goldImage0 = ResourceLoader.loadImage("resources/imgs/gold0.gif");
    goldImage1 = ResourceLoader.loadImage("resources/imgs/gold1.gif");
    goldImage2 = ResourceLoader.loadImage("resources/imgs/gold2.gif");
  }

  public void draw(Graphics g) {
    if (goldImage0 != null) {
      g.drawImage(goldImage0,
          gold.getGold0X(), gold.getGold0Y(),
          gold.getGold0Width(), gold.getGold0Height(),
          null);
    }
    if (goldImage1 != null) {
      g.drawImage(goldImage1,
          gold.getGold1X(), gold.getGold1Y(),
          gold.getGold1Width(), gold.getGold1Height(),
          null);
    }
    if (goldImage2 != null) {
      g.drawImage(goldImage2,
          gold.getGold2X(), gold.getGold2Y(),
          gold.getGold2Width(), gold.getGold2Height(),
          null);
    }
  }
}
