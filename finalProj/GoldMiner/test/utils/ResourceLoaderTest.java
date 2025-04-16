package utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Test class for ResourceLoader.
 */
class ResourceLoaderTest {
  BufferedImage validImage;
  BufferedImage invalidImage;

  @BeforeEach
  void setUp() {
    validImage = ResourceLoader.loadImage("resources/imgs/test.png");
    invalidImage = ResourceLoader.loadImage("invalid.png");
  }

  /**
   * Test the loadImage method of ResourceLoader.
   * It should return a valid BufferedImage for a valid path and null for an invalid path.
   */
  @Test
  void loadImage() {
    assertNotNull(validImage, "Image should be loaded successfully");
    assertNull(invalidImage, "Invalid image should return null");
  }
}