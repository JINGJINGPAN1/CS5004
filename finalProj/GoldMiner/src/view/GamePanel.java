package view;

import controller.GameController;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.GameTimer;
import model.Item;

/**
 * Main game panel that contains all the views.
 */
public class GamePanel extends JPanel {
  private GameController gameController; // the controller that manages the game logic
  private BackgroundView backgroundView; // the background view
  private LineView lineView; // the line view
//  private GoldView goldView;
//  private StoneView stoneView;
  private ItemView itemView;
  private ScoreView scoreView;
  private GameTimerView gameTimerView;
  private LevelView levelView;


  /**
   * Constructor for the GamePanel.
   * @param gameController the controller that manages the game logic
   * @param backgroundView the background view
   * @param lineView the line view
   * @param itemView the item view
   */
  public GamePanel(GameController gameController,
      BackgroundView backgroundView,
      LineView lineView,
      ItemView itemView) {
    this.gameController = gameController;
    this.backgroundView = backgroundView;
    this.lineView = lineView;
    this.scoreView = new ScoreView(gameController.getScore());
    this.levelView = new LevelView(gameController.getLevel());
    this.gameTimerView = new GameTimerView(gameController.getGameTimer());
    this.itemView = itemView;

    setDoubleBuffered(true);  // use double buffering to prevent flickering when updating the display
  }

  /**
   * Update the game state and repaint the panel.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // paint background
    if (backgroundView != null) {
      backgroundView.draw(g);
    }
    // paint line
    if (lineView != null) {
      lineView.draw(g);
    }

    if (itemView != null) {
      itemView.draw(g);
    }

    if (scoreView != null) {
      scoreView.draw(g);
    }

    if (gameTimerView != null) {
      gameTimerView.draw(g);
    }

    if (levelView != null) {
      levelView.draw(g);
    }

    // paint the latest list
    itemView.setItemList(gameController.getItemList());

    itemView.draw(g);


  }
}
