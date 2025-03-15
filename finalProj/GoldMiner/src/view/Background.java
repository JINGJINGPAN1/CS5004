package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.ResourceLoader;

public class Background {
  private BufferedImage bgImage;
  private BufferedImage bgImage1;
  private BufferedImage personImage;

  public Background() {
    bgImage = ResourceLoader.loadImage("resources/imgs/bg.jpg");
    if(bgImage == null) {
      System.err.println("Background image not found");
    }

    bgImage1 = ResourceLoader.loadImage("resources/imgs/bg1.jpg");
    if(bgImage1 == null) {
      System.err.println("Background image1 not found");
    }
    personImage = ResourceLoader.loadImage("resources/imgs/peo.png");
    if(personImage == null) {
      System.err.println("Person image not found");
    }

    System.out.println("Background image loaded");

  }

  public void paintSelf(Graphics g) {
    if(bgImage1 != null) {
      g.drawImage(bgImage1, 0, 0, null);
    }

    if(bgImage != null) {
      g.drawImage(bgImage, 0, 200, null);
    }

    if(personImage != null) {
      g.drawImage(personImage, 310, 50, null);
    }

    System.out.println("Background image painting");
  }
}
