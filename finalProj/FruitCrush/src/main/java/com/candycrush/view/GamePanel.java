package com.candycrush.view;

import com.candycrush.tile.Tile;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
  private Tile[][] grid;
  private final int GRID_SIZE = 15;
  private final int TILE_SIZE = 30;

  public GamePanel(JFrame frame) {
    grid = new Tile[GRID_SIZE][GRID_SIZE];
    initializeGrid();


    // set layout
//    setLayout(new BorderLayout());
    setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

    // add tile to layout
    for(int i = 0; i < GRID_SIZE; i++){
      for(int j = 0; j < GRID_SIZE; j++){
        JLabel tileLabel = new JLabel();
        tileLabel.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        tileLabel.setIcon(resizeImageIcon(grid[i][j].getImageIcon(), TILE_SIZE, TILE_SIZE));
        add(tileLabel);
      }
    }
  }

  // initializeGrid
  private void initializeGrid(){
    Random rand = new Random();
    for(int i = 0; i < GRID_SIZE; i++){
      for(int j = 0; j < GRID_SIZE; j++){
        int type = rand.nextInt(5);
        switch (type){
          case 0:
            grid[i][j] = Tile.createApple();
            break;
          case 1:
            grid[i][j] = Tile.createGrapes();
            break;
          case 2:
            grid[i][j] = Tile.createKiwi();
            break;
          case 3:
            grid[i][j] = Tile.createOrange();
            break;
          case 4:
            grid[i][j] = Tile.createWatermelon();
            break;
        }

      }
    }
  }

  private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
    Image img = icon.getImage();
    Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImg);
  }

}
