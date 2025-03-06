package com.candycrush.view;

import com.candycrush.tile.Tile;
import com.candycrush.tile.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel {
  private Tile[][] grid;
  private final int GRID_SIZE = 8;
  private final int TILE_SIZE = 60; // Increased for better visibility
  private JLabel[][] tileLabels;
  private Tile firstTile;  // First clicked tile
  private Tile secondTile; // Second clicked tile

  public GamePanel(JFrame frame) {
    System.out.println("GamePanel initialized");

    // Ensure grid is initialized before any UI updates
    grid = new Tile[GRID_SIZE][GRID_SIZE];
    tileLabels = new JLabel[GRID_SIZE][GRID_SIZE];
    initializeGrid(); // Populate the grid with tiles

    setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

    // Add tiles to the layout
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        tileLabels[i][j] = new JLabel();
        tileLabels[i][j].setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        tileLabels[i][j].setIcon(resizeImageIcon(grid[i][j].getImageIcon(), TILE_SIZE, TILE_SIZE));
        add(tileLabels[i][j]);

        final int x = i;
        final int y = j;
        tileLabels[i][j].addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            handleTileClick(x, y);
          }
        });
      }
    }
  }

  // Initialize the grid with random tiles
  private void initializeGrid() {
    System.out.println("Initializing grid...");
    Random rand = new Random();
    TileType[] tileTypes = TileType.values();

    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        TileType randomType = tileTypes[rand.nextInt(tileTypes.length)];
        grid[i][j] = Tile.createTile(randomType, i, j);
        System.out.println("Tile created at (" + i + ", " + j + ") with type " + randomType);
      }
    }
  }

  // Resize the tile images
  private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
    if (icon == null || icon.getImage() == null) {
      System.out.println("Image loading failed, ImageIcon is null");
      return null;
    }
    Image img = icon.getImage();
    Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImg);
  }

  // Handle tile click events
  private void handleTileClick(int x, int y) {
    if (firstTile == null) {
      firstTile = grid[x][y]; // Store first clicked tile
    } else {
      secondTile = grid[x][y]; // Store second clicked tile
      if (areAdjacent(firstTile, secondTile)){
        swapTiles(firstTile, secondTile);
      }
      else{
        System.out.println("Tiles are not adjacent! Swap not allowed.");
        // for bug
        System.out.println("x1: " + firstTile.getX());
        System.out.println("y1: " + firstTile.getY());
        System.out.println("x2: " + secondTile.getX());
        System.out.println("y2: " + secondTile.getY());
      }

      firstTile = null; // Reset selection
      secondTile = null; // Reset selection
    }
  }

  private boolean areAdjacent(Tile tile1, Tile tile2) {
      int x1 = tile1.getX();
      int y1 = tile1.getY();
      int x2 = tile2.getX();
      int y2 = tile2.getY();
      return Math.abs(x1 - x2) + Math.abs(y1 - y2) == 1;
  }

  // Swap two tiles and update the UI
  private void swapTiles(Tile tile1, Tile tile2) {
    if (tile1 != null && tile2 != null) {
      int x1 = tile1.getX();
      int y1 = tile1.getY();
      int x2 = tile2.getX();
      int y2 = tile2.getY();

      // temporarily Swap tiles in the grid
      grid[x1][y1] = tile2;
      tile2.setX(x1);
      tile2.setY(y1);
      grid[x2][y2] = tile1;
      tile1.setX(x2);
      tile1.setY(y2);

      if(isValidMatch(x1, y1) || isValidMatch(x2, y2)){
        System.out.println("Valid Swap, tiles remain Swapped");
        removeMatchesAndCollapse();
        // Update UI
        updateUI();
      }else{
        System.out.println("Invalid Swap, tiles are not Swapped");
        grid[x1][y1] = tile1;
        tile1.setX(x1);
        tile1.setY(y1);
        grid[x2][y2] = tile2;
        tile2.setX(x2);
        tile2.setY(y2);
      }

    }
  }

  private void removeMatchesAndCollapse() {
    boolean[][] toRemove = new boolean[GRID_SIZE][GRID_SIZE];
    boolean hasMatches = false;

    // Step 1: Mark matched tiles
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        if (isValidMatch(i, j)) {
          toRemove[i][j] = true;
          hasMatches = true;
        }
      }
    }

    // Step 2: Remove matched tiles
    if (hasMatches) {
      for (int i = 0; i < GRID_SIZE; i++) {
        for (int j = 0; j < GRID_SIZE; j++) {
          if (toRemove[i][j]) {
            grid[i][j] = null;
          }
        }
      }

      // Step 3: Collapse the board (tiles fall down)
      collapseTiles();

      // Step 4: Generate new tiles
      generateNewTiles();

      // Step 5: Recursively check if new matches are formed
      removeMatchesAndCollapse();
    }
  }

  private void collapseTiles() {
    for (int j = 0; j < GRID_SIZE; j++) { // Iterate columns
      for (int i = GRID_SIZE - 1; i >= 0; i--) { // Start from bottom row
        if (grid[i][j] == null) {
          int k = i;
          while (k > 0 && grid[k][j] == null) {
            k--;
          }
          if (grid[k][j] != null) { // Swap non-null tile down
            grid[i][j] = grid[k][j];
            grid[k][j] = null;
            grid[i][j].setX(i);
            grid[i][j].setY(j);
          }
        }
      }
    }
  }

  private void generateNewTiles() {
    Random rand = new Random();
    TileType[] tileTypes = TileType.values();

    for (int j = 0; j < GRID_SIZE; j++) {
      for (int i = 0; i < GRID_SIZE; i++) {
        if (grid[i][j] == null) {
          TileType randomType = tileTypes[rand.nextInt(tileTypes.length)];
          grid[i][j] = Tile.createTile(randomType, i, j);
        }
      }
    }
  }
  
  private boolean isValidMatch(int x, int y) {
    TileType type = grid[x][y].getType();
    return (countMatchingTile(x, y, 1, 0, type) + countMatchingTile(x, y, -1, 0, type) >= 2) ||
        (countMatchingTile(x, y, 0, 1, type) + countMatchingTile(x, y, 0, -1, type) >= 2);
  }

  private int countMatchingTile(int x, int y, int i, int j, TileType type) {
    int count = 0;
    int newX = x + i;
    int newY = y + j;
    while (newX >= 0 && newX < GRID_SIZE && newY >= 0 && newY < GRID_SIZE && grid[newX][newY].getType() == type) {
      count++;
      newX += i;
      newY += j;
    }
    return count;
  }

  // Update the UI to reflect changes in the grid
  public void updateUI() {
    if (grid == null) {
      System.out.println("ERROR: Grid is null in updateUI!");
      return;
    }

    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        if (grid[i][j] != null) {
          tileLabels[i][j].setIcon(resizeImageIcon(grid[i][j].getImageIcon(), TILE_SIZE, TILE_SIZE));
        } else {
          tileLabels[i][j].setIcon(null);
        }
      }
    }
    revalidate();
    repaint();
  }
}
