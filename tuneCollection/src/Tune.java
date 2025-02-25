// STUDENTS DO NOT NEED TO MODIFY THIS FILE

/**
 * Defines an Interface for Tunes
 * <p></p>
 * There may be more than one implementation of Tunes
 */
public interface Tune extends Comparable<Tune> {

    /**
     * Returns the title of the Tune as a String
     */
    String getTitle();

    /**
     * Returns the main artist for the Tune as a Person
     */
    Person getArtist();

    /**
     * Returns the genre of the Tune, an Enum such as JAZZ, FOLK, HIPHOP, POP
     */
    Genre getGenre();

    /**
     * Returns the tempo of the Tune as an int
     * <p></p>
     * Tempo is measured in beats per minute (BPM), an int
     * Tempo must be positive
     */
    int getTempo();

    /**
     * Returns the filePath for this tune, as a string
     */
    String getFilePath();

    /**
     * Calls third party software or a library to play this tune from a file.
     * <p></p>
     * @return boolean (true if playing the tune was successful)
     */
    // DIFFERENT METHODS ARE NEEDED to play .mp3 versus .wav files.
    // Different subclasses of AbstractTune must implement
    // appropriate playTune methods for their instances of Tunes.
    boolean playTune();

    // The following are inherited from Object but we require our own.
    /**
     * Override toString method to include all attributes in the string
     */
    @Override
    String toString();

    /**
     * Override equals method to consider all attributes
     */
    @Override
    boolean equals(Object o);

    /**
     * Override hashCode method for consistency with overridden equals
     */
    @Override
    int hashCode();
}
