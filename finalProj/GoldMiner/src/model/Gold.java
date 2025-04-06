package model;

public class Gold {
  // Example: 3 golds
  private int gold0X, gold0Y, gold0Width, gold0Height;
  private int gold1X, gold1Y, gold1Width, gold1Height;
  private int gold2X, gold2Y, gold2Width, gold2Height;

  public Gold() {
    // coordinate examples
    gold0X = 300;
    gold0Y = 500;
    gold0Width = 52;
    gold0Height = 52;

    gold1X = 500;
    gold1Y = 300;
    gold1Width = 82;
    gold1Height = 82;

    gold2X = 100;
    gold2Y = 400;
    gold2Width = 102;
    gold2Height = 102;
  }

  // Getter
  public int getGold0X() { return gold0X; }
  public int getGold0Y() { return gold0Y; }
  public int getGold0Width() { return gold0Width; }
  public int getGold0Height() { return gold0Height; }

  public int getGold1X() { return gold1X; }
  public int getGold1Y() { return gold1Y; }
  public int getGold1Width() { return gold1Width; }
  public int getGold1Height() { return gold1Height; }

  public int getGold2X() { return gold2X; }
  public int getGold2Y() { return gold2Y; }
  public int getGold2Width() { return gold2Width; }
  public int getGold2Height() { return gold2Height; }
}
