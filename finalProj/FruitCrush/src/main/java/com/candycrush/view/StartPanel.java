package com.candycrush.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartPanel extends JPanel {
  private BufferedImage bgImage;
  public StartPanel(JFrame frame) {
    // load bg picture
    try{
      bgImage = ImageIO.read(new File("images/background/background.png"));
    }catch(Exception e){
      e.printStackTrace();
    }
    // set layout
    setLayout(new BorderLayout());

    // set title
    JLabel titleLabel = new JLabel("Welcome to Fruit Crush", SwingConstants.CENTER);
    add(titleLabel, BorderLayout.CENTER);

    // create start Button
    JButton startButton = new JButton("Start");
    startButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO: START GAME
        System.out.println("Game start");
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

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(startButton);
    buttonPanel.add(exitButton);
    add(buttonPanel, BorderLayout.SOUTH);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if(bgImage != null){
      g.drawImage(bgImage, 0, 0, getWidth(),getHeight(), this);
    }
  }

}
