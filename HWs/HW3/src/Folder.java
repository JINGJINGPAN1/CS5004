import java.util.ArrayList;
import java.util.List;

/**
 * Represents a folder (directory) in the file system composite structure.
 * A Folder can contain other FileSys entries (Files or Folders).
 */
public class Folder extends FileSys{
  private List<FileSys> children;

  /**
   * Constructs a new Folder with the given name.
   * @param name the name of the folder
   */
  public Folder(String name){
    super(name);
    children = new ArrayList<>();
  }

  /**
   * Adds a child node (File or Folder) to this folder.
   * @param child the child node to be added
   */
  public void addChild(FileSys child){
    children.add(child);
  }

  /**
   * Prints the folder's name with 'd ' prefix and recursively prints
   * all child nodes with increased indentation.
   * @param indentation the current indentation string
   */
  @Override
  public void prettyPrintName(String indentation) {
    System.out.println(indentation + "d " + getName());
    for(FileSys child : children){
      child.prettyPrintName(indentation + "  ");
    }
  }
}