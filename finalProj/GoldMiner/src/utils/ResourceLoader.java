package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * ResourceLoader is a utility class for loading images from the resources folder.
 */
public class ResourceLoader {
  /**
   * Loads an image from the specified path in the resources folder.
   * @param path The path to the image file relative to the resources folder.
   * @return A BufferedImage object representing the loaded image, or null if the image could not be loaded.
   */
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
