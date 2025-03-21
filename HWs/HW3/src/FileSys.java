/**
 * Abstract class representing a file system entry in a composite structure.
 * This can be either a Folder (directory) or a File.
 */
public abstract class FileSys {
  private String name;

  /**
   * Constructs a new file system entry with the given name.
   * @param name the name of the file system entry
   */
  public FileSys(String name) {
    this.name = name;
  }

  /**
   * Returns the name of this file system entry.
   * @return the name of the entry
   */
  public String getName() {
    return this.name;
  }

  /**
   * Prints the name of this entry with the specified indentation.
   * Subclasses must implement this to define their specific printing behavior.
   * @param indentation the string used for indentation (e.g., spaces)
   */
  public abstract void prettyPrintName(String indentation);

}
