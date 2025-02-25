import java.awt.Shape;
import java.util.Objects;

/**
 * Abstract Tune class helps implement the Tune interface
 *
 * This class implements the {@link Tune} interface and provides common functionality
 * that can be shared across all concrete shape classes (e.g., MP3Tune, WavTune)
 * See AbstractTuneTest as well
 */
//BUG Tune is an interface, Use implements when a class implements an interface.
//public abstract class AbstractTune extends Tune, Comparable<Tune> {
public abstract class AbstractTune implements Tune, Comparable<Tune> {
//  BUG
//  the instance variables in AbstractTune should be protected, not private or public.
//  If they were private, subclasses would need getters to access them.
//  private String title;
//  private Person artist;
//  private Genre genre;
//  private int tempo;
//  private String filePath;

  protected String title;
  protected Person artist;
  protected Genre genre;
  protected int tempo;
  protected String filePath;

  /**
   * Constructs an AbstractTune object with a title, artist, genre, tempo and filePath.
   * @param title A string representing the title
   * @param artist A Person representing the artist
   * @param genre The genre type(CLASSICAL, HIPHOP, JAZZ, POP, ROCK)
   * @param tempo An Integer representing tempo
   * @param filePath A string representing the filePath
   */

  //BUG:In the constructor, the code does not throw an exception. just emit the "throws IllegalArgumentException "
  public AbstractTune(String title, Person artist, Genre genre, int tempo, String filePath) throws IllegalArgumentException {
      if (title == null || title.isEmpty() || artist == null || genre == null || filePath == null || filePath.isEmpty()) {
        throw new IllegalArgumentException("Invalid input: Title, artist, genre, and filePath cannot be null or empty.");
      }
      if (tempo <= 0) {
        throw new IllegalArgumentException("Tempo must be greater than zero.");
      }
      this.title = title;
      this.artist = artist;
      this.genre = genre;
      this.tempo = tempo;

      //BUG
      // filePath should be used here
      this.filePath = filePath;
  }

  /**
   * Gets the title of the Tune
   *
   * @return Return the title
   */
  public String getTitle () {
    return this.title;
  }

  /**
   * Gets the tune's artist
   *
   * @return the artist as a person
   */
  @Override
  public Person getArtist() {
    return this.artist;
  }

  /**
   * Gets the genre of the Tune
   *
   * @return the genre
   */
  @Override
  public Genre getGenre() {
    return this.genre;
  }

  /**
   * Gets the tempo of the Tune
   *
   * @return the tempo of the tune as an integer
   */

  @Override
  public int getTempo() {
    return this.tempo;
  }

  /**
   * Gets the filePath of the Tune
   *
   * @return the file path of the tune as a string
   */

  @Override
  public String getFilePath() {
    return this.filePath;
  }



  /**
   * Checks if the Tune object is equal to another Object.
   * Compares all fields for equality.
   * @param other the object to compare with
   * @return true if the objects are equal, false otherwise
   */

  // BUG
  // incorrect Override signature
  //  The equals method must override Object's equals method,
  // which has the signature: public boolean equals(Object obj)
  // However, the provided code, the type of parameter in the equals(...) is Tune class
  // this is not correct.

  @Override
  public boolean equals (Object other) {
    if(this == other){
      return true;
    }

    if(other == null){
      return false;
    }
    if(!(other instanceof Tune)){
      return false;
    }
    Tune otherTune = (Tune) other;

    return this.title.equals(otherTune.getTitle())
        && this.artist == otherTune.getArtist()
        && this.genre == otherTune.getGenre()
        && this.tempo == otherTune.getTempo()
        && this.filePath.equals(otherTune.getFilePath());
  }

  /**
   * Generates a hash code for the Tune object based on its fields.
   *
   * @return the hash code of the Tune object
   */
  @Override
  public int hashCode () {
    // Student to complete
    return Objects.hash(title, artist, genre, tempo, filePath);
  }

//  /** Overriding toString for Tunes to include all attributes */
//  @Override
//  public String toString () {
//    // Student to complete
//    return "";
//  }


  /**
   * Returns a string representation of the Tune object, including all attributes.
   *
   * @return a formatted string with the tune's details
   */
  @Override
  public String toString() {
    String str = "Tune Details:\n";
    str += "Title: " + getTitle() + "\n";
    str += "Artist: " + getArtist().getName() + "\n";
    switch (this.genre) {
      case CLASSICAL:
        str += "Genre: Classical" + "\n";
        break;
      case HIPHOP:
        str += "Genre: Hiphop" + "\n";
        break;
      case JAZZ:
        str += "Genre: Jazz" + "\n";
        break;
      case POP:
        str += "Genre: Pop" + "\n";
        break;
      default:
        str += "Genre: Rock" + "\n";
    }
    str += "Tempo: " + getTempo() + "\n";
    str += "FilePath: " + getFilePath() + "\n";
    return str;
  }

  /**
   * Comparable Interface for comparing based on Title
   * <p></p>
   * 
   * @param other -- a Tune
   * @return negative, 0, or positive int based on Title
   *         Comparison should ignore case (so A equals a)
   */
  @Override
  public int compareTo(Tune other) {
    // STUDENT TO IMPLEMENT
    return this.title.compareToIgnoreCase(other.getTitle());
  }
}
