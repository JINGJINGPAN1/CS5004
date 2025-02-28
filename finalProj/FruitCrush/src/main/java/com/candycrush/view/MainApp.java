package com.candycrush.view;

import javax.swing.JFrame;

public class MainApp extends JFrame {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Fruit Crush");
    frame.setSize(600, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    StartPanel startPanel = new StartPanel(frame);
    frame.add(startPanel);

    frame.setVisible(true);

  }


}
