/**
 * STUDENTS DO NOT NEED TO MODIFY THIS FILE
 */
public class WavTune extends AbstractTune {

  /**
   * Constructor for .wav-based Tunes
   * <p></p>
   */
  public WavTune(String title,
      Person artist,
      Genre genre,
      int temp,
      String filePath) {
    super(title, artist, genre, temp, filePath);
  }

  /**
   * Play this tune from the file named in its filePath attribute.
   * <p></p>
   * WavTune adds a specific method for playing a .wav file. (Mp3Tune requires a different
   * implementation of this method.) Dynamic dispatch allows the correct playTune method to be
   * invoked by Java automatically, based on the concrete subclass.
   *
   * @return boolean, true iff the tune was successfully played.
   */
  // STUDENTS ARE NOT EXPECTED TO TEST THIS METHOD ON THE MIDTERM
  @Override
  public boolean playTune() {
    // Stubbed out version for the midterm
    // A real version should verify the file's extension etc.
    try {
      String msg = "WavTune at " +
          this.getFilePath() +
          "played successfully.";
      System.out.println(msg);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}