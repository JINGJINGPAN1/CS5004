package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.ResourceLoader;

public class Gold {
  private BufferedImage goldImage0;
  private BufferedImage goldImage1;
  private BufferedImage goldImage2;

  public Gold() {
    goldImage0 = ResourceLoader.loadImage("resources/imgs/gold0.gif");
    if(goldImage0 == null) {
      System.out.println("Gold image0 not found");
    }
    goldImage1 = ResourceLoader.loadImage("resources/imgs/gold1.gif");
    if(goldImage1 == null) {
      System.out.println("Gold image1 not found");
    }

    goldImage2 = ResourceLoader.loadImage("resources/imgs/gold2.gif");
    if(goldImage2 == null) {
      System.out.println("Gold image2 not found");
    }
  }

  public void paintSelf(Graphics g) {
    g.drawImage(goldImage0, 300, 500, 52, 52, null);
    g.drawImage(goldImage1, 500, 300, 82, 82, null);
    g.drawImage(goldImage2, 100, 400, 102, 102, null);
  }

}
