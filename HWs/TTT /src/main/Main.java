package main;

import controller.TTTController;
import model.TTTModel;
import model.TTTModelImpl;
import view.TTTView;

/**
 * Main entry point for the Tic Tac Toe game.
 */
public class Main {
  public static void main(String[] args) {
    TTTModel model = new TTTModelImpl(3);
    TTTView view = new TTTView(model);
    new TTTController(model, view);
  }
}
