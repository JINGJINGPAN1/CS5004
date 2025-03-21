/**
 * Demonstrates the composite file system structure with a sample hierarchy.
 */
public class Main {
  public static void main(String[] args) {
    Folder root = new Folder("root");
    Folder home = new Folder("home");
    root.addChild(home);

    Folder mlmiller = new Folder("mlmiller");
    home.addChild(mlmiller);

    File markFileA = new File("markFileA");
    File markFileB = new File("markFileB");
    mlmiller.addChild(markFileA);
    mlmiller.addChild(markFileB);

    root.prettyPrintName("");

  }
}
