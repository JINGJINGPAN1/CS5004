package view;

import controller.GameController;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameWin extends JFrame {
  private GameController gameController;
  private GamePanel gamePanel;

  public GameWin() {
    gameController = new GameController();
    gamePanel = new GamePanel(gameController);
    add(gamePanel);
  }

  public void launch() {
    setTitle("Gold Miner");
    setSize(768, 1000);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // set time, update every 16 millisecond
    Timer timer = new Timer(16, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Timer triggered");
        gameController.update();
        repaint();
      }
    });
    timer.start();
    System.out.println("Timer started");
  }

  @Override
  public void paint(Graphics g) {
    System.out.println("paint method called");
    super.paint(g);
    gameController.draw(g);
  }
}