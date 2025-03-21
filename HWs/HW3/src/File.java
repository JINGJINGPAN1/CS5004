/**
 * Represents a file in the file system composite structure. A File is a leaf node
 * and cannot contain other entries.
 */
public class File extends FileSys{

  /**
   * Constructs a new File with the given name.
   * @param name the name of the file
   */
  public File(String name){
    super(name);
  }

  /**
   * Prints the file's name with the specified indentation.
   * @param indentation the current indentation string
   */
  @Override
  public void prettyPrintName(String indentation) {
    System.out.println(indentation + getName());
  }
}
