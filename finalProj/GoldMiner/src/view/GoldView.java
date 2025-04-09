//package view;
//
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.util.List;
//import model.Gold;
//import utils.ResourceLoader;
//
//public class GoldView {
//  private List<Gold> goldList;
//  // Use a single gold image or multiple?
//  // Example: one image for all gold pieces:
//  private BufferedImage goldImage;
//
//  public GoldView(List<Gold> goldList) {
//    this.goldList = goldList;
//    goldImage = ResourceLoader.loadImage("resources/imgs/gold0.gif");
//  }
//
//  public void draw(Graphics g) {
//    if (goldImage != null) {
//      for (Gold gold : goldList) {
//        // draw each gold in the list
//        g.drawImage(
//            goldImage,
//            gold.getX(), gold.getY(),
//            gold.getWidth(), gold.getHeight(),
//            null
//        );
//      }
//    }
//  }
//}
