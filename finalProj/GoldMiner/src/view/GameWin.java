package view;

import controller.GameController;
import java.util.List;
import model.Gold;
import model.Item;
import model.Line;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.LineState;
import model.Stone;

public class GameWin extends JFrame {
  private GameController gameController;

  // Initialize all the views in the game window
  private BackgroundView backgroundView;
  private LineView lineView;
//  private GoldView goldView;
//  private StoneView stoneView;
  private ItemView itemView;
  private GamePanel gamePanel;

  public GameWin() {
    // initialize Controller
    this.gameController = new GameController();

    // get models from controller
    Line line = gameController.getLine();
//    List<Gold> goldList = gameController.getGoldList();
//    List<Stone> stoneList = gameController.getStoneList();
    List<Item> itemList = gameController.getItemList();

    // initialize all the view
    backgroundView = new BackgroundView();
    lineView = new LineView(line);
//    goldView = new GoldView(goldList);
//    stoneView = new StoneView(stoneList);
    itemView = new ItemView(itemList);

    // put all the views on game panel
    gamePanel = new GamePanel(gameController, backgroundView, lineView, itemView);

    // add panel to game window
    add(gamePanel);
  }

  public void launch() {
    setTitle("Gold Miner");
    setSize(600, 800);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // mouse listening: left->grab
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getButton() == MouseEvent.BUTTON1) {
          // Only allow grabbing if line is in Swing or Grab
          if(gameController.getLine().getLineState() == LineState.GRAB ||
          gameController.getLine().getLineState() == LineState.SWING){
            // game controller to start grabbing
            gameController.startGrabbing();
          }else{
            System.out.println("Cannot grab now, line is retracting.");
          }
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
