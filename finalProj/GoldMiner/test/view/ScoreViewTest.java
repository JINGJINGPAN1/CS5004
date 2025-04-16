package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Test class for ScoreView.
 */
class ScoreViewTest {
  Score mockScore;
  Graphics graphics;
  ScoreView scoreView;

  @BeforeEach
  void setUp() {
    mockScore = mock(Score.class);
    graphics = mock(Graphics.class);
    scoreView = new ScoreView(mockScore);
  }

  /**
   * Test the draw method of ScoreView.
   */
  @Test
  void draw() {
    scoreView.draw(graphics);
    verify(graphics).setColor(Color.WHITE);
    verify(graphics).setFont(argThat(font ->
            font.getFamily().equals("Comic Sans MS") &&
            font.getStyle() == Font.BOLD &&
            font.getSize() == 25
    ));
    verify(graphics).drawString("Score: 0", 20, 70);
  }
}