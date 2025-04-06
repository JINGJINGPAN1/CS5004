package view;

import controller.GameController;
import model.Gold;
import model.Line;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {
  private GameController gameController;

  // Initialize all the views in the game window
  private BackgroundView backgroundView;
  private LineView lineView;
  private GoldView goldView;
  private GamePanel gamePanel;

  public GameWin() {
    // initialize Controller
    this.gameController = new GameController();

    // get models from controller
    Line line = gameController.getLine();
    Gold gold = gameController.getGold();

    // initialize all the view
    backgroundView = new BackgroundView();
    lineView = new LineView(line);
    goldView = new GoldView(gold);

    // put all the views on game panel
    gamePanel = new GamePanel(gameController, backgroundView, lineView, goldView);

    // add panel to game window
    add(gamePanel);
  }

  public void launch() {
    setTitle("Gold Miner");
    setSize(768, 1000);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // mouse listening: left->grab
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getButton() == MouseEvent.BUTTON1) {
          // game controller to start grabbing
          gameController.startGrabbing();
        }
      }
    });

    // set a clock: update every 16 seconds
    Timer timer = new Timer(16, e -> {
      // controller to update
      gameController.update();
      // repaint
      repaint();
    });
    timer.start();
  }
}
