package model;

/**
 * Represents the Model component for a Tic Tac Toe game.
 * Manages the game state, including the board, current player, and game over conditions.
 */
public interface TTTModel {

  /**
   * Returns the size of the board (N x N).
   * @return the board size
   */
  int getBoardSize();

  //BLACK BOX TEST Assertions:
  //1: After initializing the model with a board size of 3, getBoardSize() should return 3.
  //2: After initializing the model with a board size of 4, getBoardSize() should return 4

  /**
   * Returns the current player whose turn it is. Returns null if the game is over.
   * @return the current player, or null if the game is over
   */
  Player getCurrentPlayer();

  //BLACK BOX TEST Assertions:
  // 1: After initializing the model, getCurrentPlayer() should return model.Player.X.
  // 2: After a valid move by model.Player.X, getCurrentPlayer() should return model.Player.O.

  /**
   * Places the current player's mark on the specified cell and switches turns.
   * @param row the row index (0-based)
   * @param col the column index (0-based)
   * @throws IllegalArgumentException if row or column is out of bounds
   * @throws IllegalStateException if the cell is occupied or the game is over
   */
  void makeMove(int row, int col) throws IllegalArgumentException, IllegalStateException;
  // BLACK BOX TEST Assertions:
  // 1: Calling makeMove(0, 0) on an empty board should place model.Player.X at position (0, 0) and switch the current player to model.Player.O.
  // 2: Calling makeMove(0, 0) on a cell already occupied by model.Player.X should throw an IllegalStateException.

  /**
   * Returns the player who has placed a mark at the specified cell, or null if empty.
   * @param row the row index (0-based)
   * @param col the column index (0-based)
   * @return the player at the specified cell, or null if empty
   * @throws IllegalArgumentException if row or column is out of bounds
   */
  Player getMark(int row, int col) throws IllegalArgumentException;
  // BLACK BOX TEST Assertions:
  // 1: After makeMove(1, 1) by model.Player.X, getMark(1, 1) should return model.Player.X.
  // 2: After initializing the model, getMark(2, 2) should return null (indicating an empty cell).

  /**
   * Checks if the game is over (win or draw).
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();
  // BLACK BOX TEST Assertions:
  // 1: After initializing the model, isGameOver() should return false.
  // 2: After a sequence of moves that results in a win for model.Player.X, isGameOver() should return true.

  /**
   * Returns the winner of the game. Returns null if the game is a draw or not over.
   * @return the winning player, or null
   */
  Player getWinner();
  // BLACK BOX TEST Assertions:
  // 1: After a sequence of moves that results in a win for model.Player.X, getWinner() should return model.Player.X.
  // 2: After a sequence of moves that results in a draw, getWinner() should return null.
  /**
   * Resets the game to its initial state: empty board, current player set to X, game over cleared.
   */
  void reset();
  // BLACK BOX TEST Assertions:
  // 1: After calling reset() on a game in progress, getCurrentPlayer() should return model.Player.X.
  // 2: After calling reset() on a game in progress, isGameOver() should return false.
}