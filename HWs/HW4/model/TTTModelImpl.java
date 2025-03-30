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

  private boolean checkWin(Player player, int row, int col) {
    return checkRow(player, row) || checkColumn(player, col)
        || checkMainDiagonal(player, row, col) || checkAntiDiagonal(player, row, col);
  }

  private boolean checkRow(Player player, int row) {
    for (int c = 0; c < size; c++) {
      if (board[row][c] != player) return false;
    }
    return true;
  }

  private boolean checkColumn(Player player, int col) {
    for (int r = 0; r < size; r++) {
      if (board[r][col] != player) return false;
    }
    return true;
  }

  private boolean checkMainDiagonal(Player player, int row, int col) {
    if (row != col) return false;
    for (int i = 0; i < size; i++) {
      if (board[i][i] != player) return false;
    }
    return true;
  }

  private boolean checkAntiDiagonal(Player player, int row, int col) {
    if (row + col != size - 1) return false;
    for (int i = 0; i < size; i++) {
      if (board[i][size - 1 - i] != player) return false;
    }
    return true;
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
