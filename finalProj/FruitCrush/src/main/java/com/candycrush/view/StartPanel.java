package com.candycrush.view;

import com.candycrush.tile.Tile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartPanel extends JPanel {
  private BufferedImage bgImage;
  private JFrame frame;

  public StartPanel(JFrame frame) {
    this.frame = frame;
    setLayout(new BorderLayout());
    // load bg picture
    try{
      bgImage = ImageIO.read(new File("images/background/background.png"));
    }catch(Exception e){
      e.printStackTrace();
    }

    // set title
    JLabel titleLabel = new JLabel("Welcome to Fruit Crush", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    add(titleLabel, BorderLayout.CENTER);


    // create start Button
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    JButton startButton = new JButton("Start");
    startButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO: START GAME
        System.out.println("Game start");

        GamePanel gamePanel = new GamePanel(frame);
        frame.setContentPane(gamePanel);
        frame.revalidate();
        frame.repaint();
      }
    });

    // create exit Button
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    buttonPanel.add(startButton);
    buttonPanel.add(exitButton);
    add(buttonPanel, BorderLayout.SOUTH);

  }

  // paint background
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if(bgImage != null){
      g.drawImage(bgImage, 0, 0, getWidth(),getHeight(), this);
    }
  }
}
