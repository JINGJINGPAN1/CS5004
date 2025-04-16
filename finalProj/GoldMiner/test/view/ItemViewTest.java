package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.awt.Graphics;
import java.awt.image.AbstractMultiResolutionImage;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import model.Gold;
import model.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import utils.ResourceLoader;

/**
 * ItemViewTest is a test class for the ItemView class.
 * It contains unit tests to verify the functionality of the ItemView class.
 */
class ItemViewTest {
  Graphics graphics;
  BufferedImage image;
  Gold gold;
  Stone stone;

  @BeforeEach
  void setUp() {
    image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    graphics = image.getGraphics();
    gold = mock(Gold.class);
    stone = mock(Stone.class);
  }

  /**
   * Test for the draw method in ItemView.
   */
  @Test
  void draw() {
    // test with mock gold and stone
    when(gold.getX()).thenReturn(1);
    when(gold.getY()).thenReturn(2);
    when(gold.getWidth()).thenReturn(3);
    when(gold.getHeight()).thenReturn(4);
    when(gold.isCollected()).thenReturn(false);

    when(stone.getX()).thenReturn(5);
    when(stone.getY()).thenReturn(6);
    when(stone.getWidth()).thenReturn(7);
    when(stone.getHeight()).thenReturn(8);
    when(stone.isCollected()).thenReturn(false);

    try (MockedStatic<ResourceLoader> loader = mockStatic(ResourceLoader.class)) {
      loader.when(() -> ResourceLoader.loadImage("resources/imgs/gold.png"))
          .thenReturn(image);
      loader.when(() -> ResourceLoader.loadImage("resources/imgs/stone.png"))
          .thenReturn(image);
      ItemView itemView = new ItemView(List.of(gold, stone));
      assertDoesNotThrow(() -> itemView.draw(graphics));
    }

    // test with collected image
    when(gold.isCollected()).thenReturn(true);
    when(stone.isCollected()).thenReturn(true);
    try (MockedStatic<ResourceLoader> loader = mockStatic(ResourceLoader.class)) {
      loader.when(() -> ResourceLoader.loadImage("resources/imgs/gold.png"))
          .thenReturn(image);
      loader.when(() -> ResourceLoader.loadImage("resources/imgs/stone.png"))
          .thenReturn(image);
      ItemView itemView = new ItemView(List.of(gold, stone));
      assertDoesNotThrow(() -> itemView.draw(graphics));
    }
  }

  /**
   * Test for the setItemList method in ItemView.
   */
  @Test
  void setItemList() {
    try (MockedStatic<ResourceLoader> loader = mockStatic(ResourceLoader.class)) {
      loader.when(() -> ResourceLoader.loadImage(anyString())).thenReturn(image);
      ItemView itemView = new ItemView(Arrays.asList());
      assertDoesNotThrow(() -> itemView.setItemList(Arrays.asList(mock(Gold.class))));
    }
  }
}