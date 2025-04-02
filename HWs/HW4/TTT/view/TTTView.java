package view;

import javax.swing.*;
import java.awt.*;
import controller.TTTController;
import model.Player;
import model.TTTModel;

/**
 * The graphical user interface for the Tic Tac Toe game.
 */
public class TTTView {
  private final JFrame frame;
  private final JButton[][] buttons;
  private final JLabel statusLabel;
  private TTTController controller;
  private TTTModel model;
  private int size;

  /**
   * Constructs the Tic Tac Toe game GUI.
   */
  public TTTView(TTTModel model) {
    this.size = model.getBoardSize();
    frame = new JFrame("Tic Tac Toe");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 350);
    frame.setLayout(new BorderLayout());
    frame.setLocationRelativeTo(null);

    // Status label at the top
    statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
    frame.add(statusLabel, BorderLayout.NORTH);

    // Game board in the center
    JPanel boardPanel = new JPanel(new GridLayout(size, size));
    buttons = new JButton[size][size];
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        buttons[row][col] = new JButton();
        buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
        final int r = row, c = col;
        buttons[row][col].addActionListener(e -> controller.handleMove(r, c));
        boardPanel.add(buttons[row][col]);
      }
    }
    frame.add(boardPanel, BorderLayout.CENTER);

    // Reset button at the bottom
    JButton resetButton = new JButton("Reset Game");
    resetButton.addActionListener(e -> controller.resetGame());
    frame.add(resetButton, BorderLayout.SOUTH);

    frame.setVisible(true);
  }

  /**
   * Links the controller to the view.
   */
  public void setController(TTTController controller) {
    this.controller = controller;
  }

  /**
   * Updates the game board and status label based on the current model state.
   */
  public void updateView() {
    TTTModel model = controller.getModel();

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        Player player = model.getMark(row, col);
        buttons[row][col].setText(player == null ? "" : player.toString());
        buttons[row][col].setEnabled(!model.isGameOver() && player == null);
      }
    }

    // Update status label
    if (model.isGameOver()) {
      Player winner = model.getWinner();
      statusLabel.setText(winner == null ? "Game Over: Draw!" : "Player " + winner + " wins!");
    } else {
      statusLabel.setText("Player " + model.getCurrentPlayer() + "'s turn");
    }
  }

  /**
   * Displays an error message.
   */
  public void showError(String message) {
    JOptionPane.showMessageDialog(frame, message, "Invalid Move", JOptionPane.ERROR_MESSAGE);
  }
}
