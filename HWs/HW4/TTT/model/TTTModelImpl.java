package model;

/**
 * Concrete implementation of the Tic Tac Toe Model interface.
 * Manages the game state, including the board, current player, and game outcome.
 */
public class TTTModelImpl implements TTTModel {

  private final int size;
  private Player[][] board;
  private Player currentPlayer;
  private boolean gameOver;
  private Player winner;

  private int[] rows;
  private int[] cols;
  private int mainDiagonal;
  private int antiDiagonal;

  /**
   * Constructs a Tic Tac Toe game model with the specified board size.
   * @param size The size of the board (N x N).
   */
  public TTTModelImpl(int size) {
    this.size = size;
    reset();
  }

  @Override
  public int getBoardSize() {
    return size;
  }

  @Override
  public Player getCurrentPlayer() {
    return gameOver ? null : currentPlayer;
  }

  @Override
  public void makeMove(int row, int col) {
    validateCell(row, col);
    checkGameActive();
    checkCellEmpty(row, col);

    // Place the current player's mark
    board[row][col] = currentPlayer;

    // Check for win or draw
    if (checkWin(currentPlayer, row, col)) {
      winner = currentPlayer;
      gameOver = true;
    } else if (isBoardFull()) {
      gameOver = true;
      winner = null;
    } else {
      switchPlayer();
    }
  }

  @Override
  public Player getMark(int row, int col) {
    validateCell(row, col);
    return board[row][col];
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public Player getWinner() {
    return winner;
  }

  @Override
  public void reset() {
    board = new Player[size][size];
    currentPlayer = Player.X;
    gameOver = false;
    winner = null;

    rows = new int[size];
    cols = new int[size];
    mainDiagonal = 0;
    antiDiagonal = 0;
  }

  // Helper Methods

  private void validateCell(int row, int col) {
    if (row < 0 || row >= size || col < 0 || col >= size) {
      throw new IllegalArgumentException("Invalid cell coordinates.");
    }
  }

  private void checkGameActive() {
    if (gameOver) {
      throw new IllegalStateException("Game is already over.");
    }
  }

  private void checkCellEmpty(int row, int col) {
    if (board[row][col] != null) {
      throw new IllegalStateException("Cell is already occupied.");
    }
  }

  private void switchPlayer() {
    currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
  }

  private boolean checkWin(Player player, int row, int col){
    int currentPlayerValue= getPlayerValue(player);
    rows[row] += currentPlayerValue;
    cols[col] += currentPlayerValue;

    if(row == col){
      mainDiagonal += currentPlayerValue;
    }

    if(row + col == size - 1){
      antiDiagonal += currentPlayerValue;
    }

    return Math.abs(rows[row]) == size || Math.abs(cols[col]) == size
        || Math.abs(mainDiagonal) == size || Math.abs(antiDiagonal) == size;

  }

  private int getPlayerValue(Player player) {
    return player == Player.X ? -1 : 1;
  }

  private boolean isBoardFull() {
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        if (board[r][c] == null) return false;
      }
    }
    return true;
  }
}
