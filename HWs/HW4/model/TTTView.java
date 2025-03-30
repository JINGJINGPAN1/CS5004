import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The graphical user interface for the Tic Tac Toe game using Swing.
 */
public class TTTView {
  private final TTTModel model;
  private final JFrame frame;
  private final JButton[][] buttons;
  private final JLabel statusLabel;

  /**
   * Constructs the Tic Tac Toe game GUI.
   */
  public TTTView() {
    model = new TTTModelImpl(3); // Standard 3x3 Tic Tac Toe
    frame = new JFrame("Tic Tac Toe");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 350);
    frame.setLayout(new BorderLayout());

    // Status label at the top
    statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
    frame.add(statusLabel, BorderLayout.NORTH);

    // Game board in the center
    JPanel boardPanel = new JPanel(new GridLayout(3, 3));
    buttons = new JButton[3][3];
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        buttons[row][col] = new JButton();
        buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
        buttons[row][col].addActionListener(new ButtonClickListener(row, col));
        boardPanel.add(buttons[row][col]);
      }
    }
    frame.add(boardPanel, BorderLayout.CENTER);

    // Reset button at the bottom
    JButton resetButton = new JButton("Reset Game");
    resetButton.addActionListener(e -> resetGame());
    frame.add(resetButton, BorderLayout.SOUTH);

    updateView();
    frame.setVisible(true);
  }

  /**
   * Updates the game board and status label based on the current model state.
   */
  private void updateView() {
    // Update buttons
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
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
   * Resets the game to its initial state.
   */
  private void resetGame() {
    model.reset();
    updateView();
  }

  /**
   * Action listener for the game board buttons.
   */
  private class ButtonClickListener implements ActionListener {
    private final int row;
    private final int col;

    public ButtonClickListener(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        model.makeMove(row, col);
        updateView();
      } catch (IllegalArgumentException | IllegalStateException ex) {
        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Invalid Move", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Main method to start the game.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(TTTView::new);
  }
}