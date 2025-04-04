package controller;

import model.TTTModel;
import view.TTTView;

/**
 * Controller for Tic Tac Toe game. Handles communication between Model and View.
 */
public class TTTController {
  private final TTTModel model;
  private final TTTView view;

  /**
   * Constructs a Tic Tac Toe controller.
   */
  public TTTController(TTTModel model, TTTView view) {
    this.model = model;
    this.view = view;
    view.setController(this);
  }

  /**
   * Get the model
   * @return model
   */
  public TTTModel getModel() {
    return model;
  }

  /**
   * Handles a player's move.
   */
  public void handleMove(int row, int col) {
    try {
      model.makeMove(row, col);
      view.updateView();
    } catch (IllegalArgumentException | IllegalStateException ex) {
      view.showError(ex.getMessage());
    }
  }

  /**
   * Resets the game.
   */
  public void resetGame() {
    model.reset();
    view.updateView();
  }
}
