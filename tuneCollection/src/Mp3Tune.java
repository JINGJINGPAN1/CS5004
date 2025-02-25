/**
 * STUDENTS DO NOT NEED TO MODIFY OR TEST THIS FILE
 */
public class Mp3Tune extends AbstractTune {

    /**
     * Constructor for .mp3-based Tunes
     */
    public Mp3Tune(String title,
		   Person artist,
		   Genre genre,
		   int temp,
		   String filePath) {
	  super(title, artist, genre, temp, filePath);
    }

  /**
   * Play tune from the file named in its filePath attribute.
   * <p></p>
   * Mp3Tune adds a specific method for playing an .mp3 file.
   * (WavTune would need a different implementation of this method.)
   * Dynamic dispatch allows the correct playTune method to be
   * invoked by Java automatically, based on the concrete subclass.
   * @return boolean, true iff the tune was successfully played.
   */
  @Override
  public boolean playTune() {
    // Stubbed out version for the midterm
    // A real version should verify the file's extension etc.
    // Different libraries and 3rd party code needed for .mp3 files.
    try {
      String msg = "Mp3Tune at " +
          this.getFilePath() +
          "played successfully.";
      System.out.println(msg);
      return true;
    } catch (Exception e) {
        return false;
      }
    }
  }