//package view;
//
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.util.List;
//import model.Stone;
//import utils.ResourceLoader;
//
//public class StoneView {
//  private List<Stone> stoneList;
//  private BufferedImage stoneImage;
//
//  public StoneView(List<Stone> stoneList) {
//    this.stoneList = stoneList;
//    stoneImage = ResourceLoader.loadImage("resources/imgs/rock1.png");
//  }
//
//  public void draw(Graphics g) {
//    if (stoneImage != null) {
//      for (Stone stone : stoneList) {
//        g.drawImage(stoneImage, stone.getX(), stone.getY(), stone.getWidth(), stone.getHeight(), null);
//      }
//    }
//  }
//
//}
