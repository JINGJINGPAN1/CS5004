package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.ResourceLoader;

public class BackgroundView {
  private BufferedImage bgImage;
  private BufferedImage bgImage1;
  private BufferedImage personImage;

  public BackgroundView() {
    bgImage = ResourceLoader.loadImage("resources/imgs/bg.jpg");
    bgImage1 = ResourceLoader.loadImage("resources/imgs/bg1.jpg");
    personImage = ResourceLoader.loadImage("resources/imgs/peo.png");
  }

  public void draw(Graphics g) {
    if (bgImage1 != null) {
      g.drawImage(bgImage1, 0, 0, null);
    }
    if (bgImage != null) {
      g.drawImage(bgImage, 0, 200, null);
    }
    if (personImage != null) {
      g.drawImage(personImage, 230, 50, null);
    }
  }
}
