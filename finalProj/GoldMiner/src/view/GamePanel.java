package view;

import controller.GameController;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.GameTimer;
import model.Item;

/**
 * Responsible for painting models in the Jpanel
 */
public class GamePanel extends JPanel {
  private GameController gameController;
  private BackgroundView backgroundView;
  private LineView lineView;
//  private GoldView goldView;
//  private StoneView stoneView;
  private ItemView itemView;
  private ScoreView scoreView;
  private GameTimerView gameTimerView;
  private LevelView levelView;

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
    // paint gold
//    if (goldView != null) {
//      goldView.draw(g);
//    }
//
//    // paint stone
//    if (stoneView != null) {
//      stoneView.draw(g);
//    }

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
