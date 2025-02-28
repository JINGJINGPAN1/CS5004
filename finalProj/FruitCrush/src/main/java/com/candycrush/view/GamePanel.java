package com.candycrush.view;

import com.candycrush.tile.Tile;
import com.candycrush.tile.TileType;
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
  private final int GRID_SIZE = 8;
  private final int TILE_SIZE = 30;

  public GamePanel(JFrame frame) {
    grid = new Tile[GRID_SIZE][GRID_SIZE];
    initializeGrid();

    // set layout -- grid layout
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

  // initialize grid randomly
  private void initializeGrid(){
    Random rand = new Random();
    TileType[] tileTypes = TileType.values(); // get all the values from titleTypes
    for(int i = 0; i < GRID_SIZE; i++){
      for(int j = 0; j < GRID_SIZE; j++){
        TileType randomType = tileTypes[rand.nextInt(tileTypes.length)];
        grid[i][j] = Tile.createTile(randomType);
      }
    }
  }

  private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
    if(icon == null || icon.getImage() == null){
      System.out.println("Image loading failed, ImageIcon is null");
      return null;
    }
    Image img = icon.getImage();
    Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImg);
  }

}