package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ResourceLoader {
  public static BufferedImage loadImage(String path) {
    try (InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path)) {
      if (stream != null) {
        return ImageIO.read(stream);
      } else {
        System.err.println("Error: Image not found - " + path);
      }
    } catch (IOException e) {
      System.err.println("Error loading image: " + path);
      e.printStackTrace();
    }
    return null;
  }

}
