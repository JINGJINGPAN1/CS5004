package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.ResourceLoader;

/**
 * BackgroundView class is responsible for rendering the background and character images
 */
public class BackgroundView {
  private BufferedImage bgImage; // Background image
  private BufferedImage bgImage1; // Background image 1
  private BufferedImage personImage; // Character image

  /**
   * Constructor for BackgroundView
   * Loads the background and character images from resources
   */
  public BackgroundView() {
    bgImage = ResourceLoader.loadImage("resources/imgs/bg.jpg");
    bgImage1 = ResourceLoader.loadImage("resources/imgs/bg1.jpg");
    personImage = ResourceLoader.loadImage("resources/imgs/peo.png");
  }

  /**
   * Draws the background and character images on the provided Graphics object
   * @param g the Graphics object to draw on
   */
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
