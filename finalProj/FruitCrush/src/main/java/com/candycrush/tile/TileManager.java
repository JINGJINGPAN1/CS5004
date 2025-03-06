//package com.candycrush.tile;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class TileManager {
//  // 检测匹配的元素
//  public static List<Tile> detectMatches(Tile[][] grid) {
//    List<Tile> matches = new ArrayList<>();
//    boolean[][] checked = new boolean[grid.length][grid[0].length];
//
//    // 检测行匹配
//    for (int row = 0; row < grid.length; row++) {
//      for (int col = 0; col < grid[row].length - 2; col++) {
//        if (grid[row][col] != null && !checked[row][col]) {
//          TileType type = grid[row][col].getType();
//          List<Tile> tempMatches = new ArrayList<>();
//          tempMatches.add(grid[row][col]);
//
//          for (int k = 1; k < 3; k++) {
//            if (grid[row][col + k] != null && grid[row][col + k].getType() == type) {
//              tempMatches.add(grid[row][col + k]);
//              checked[row][col + k] = true;
//            } else {
//              break;
//            }
//          }
//
//          if (tempMatches.size() >= 3) {
//            matches.addAll(tempMatches);
//          }
//        }
//      }
//    }
//
//    // 检测列匹配
//    for (int col = 0; col < grid[0].length; col++) {
//      for (int row = 0; row < grid.length - 2; row++) {
//        if (grid[row][col] != null && !checked[row][col]) {
//          TileType type = grid[row][col].getType();
//          List<Tile> tempMatches = new ArrayList<>();
//          tempMatches.add(grid[row][col]);
//
//          for (int k = 1; k < 3; k++) {
//            if (grid[row + k][col] != null && grid[row + k][col].getType() == type) {
//              tempMatches.add(grid[row + k][col]);
//              checked[row + k][col] = true;
//            } else {
//              break;
//            }
//          }
//
//          if (tempMatches.size() >= 3) {
//            matches.addAll(tempMatches);
//          }
//        }
//      }
//    }
//
//    return matches;
//  }
//
//  // 消除匹配的元素
//  public static void clearMatches(Tile[][] grid, List<Tile> matches) {
//    for (Tile tile : matches) {
//      grid[tile.getX()][tile.getY()] = null; // 假设有 getX() 和 getY() 方法
//    }
//  }
//
//  // 填充空位
//  public static void fillEmptySpaces(Tile[][] grid) {
//    Random rand = new Random();
//    TileType[] tileTypes = TileType.values(); // 获取所有的 TileType
//    for (int col = 0; col < grid[0].length; col++) {
//      for (int row = grid.length - 1; row >= 0; row--) {
//        if (grid[row][col] == null) {
//          // 让上方的元素下落
//          for (int r = row - 1; r >= 0; r--) {
//            if (grid[r][col] != null) {
//              grid[row][col] = grid[r][col];
//              grid[r][col] = null;
//              break;
//            }
//          }
//        }
//      }
//      // 生成新元素填充顶部
//      // 这里可以调用 Tile.createTile 方法生成新元素
//      // 生成新元素填充顶部
//      for (int row = 0; row < grid.length; row++) {
//        if (grid[row][col] == null) {
//          TileType randomType = tileTypes[rand.nextInt(tileTypes.length)];
//          grid[row][col] = Tile.createTile(randomType, row, col);
//        }
//      }
//    }
//  }
//}
