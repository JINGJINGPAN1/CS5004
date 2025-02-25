/*
 STUDENTS SHOULD IMPLEMENT THE TWO SORTING METHODS BELOW
 Completing other coding is not required in this file.
*/

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * Concrete Class to implement a Simple Playlist
 */
public class PlayList {
  private List<Tune> myPlayList;

  /** Constructor, creates a new, empty PlayList
   * <p></p>
   * Expects no parameters
   */
  public PlayList() {
    myPlayList = new LinkedList<>();
  }

  /**
   * Adds a new Tune to the end of the PlayList
   * <p></p>
   * @param tune is of type Tune
   */
  public void addTune(Tune tune) {
    myPlayList.add(tune);
  }

  /**
   * Gets the Tune from index position i
   * <p></p>
   * @param i is an integer index into the list
   * @return a Tune
   * If i is out of bounds, throws IndexOutOfBoundsException
   */
  Tune getTune(int i) throws IndexOutOfBoundsException {
    return myPlayList.get(i);
  }

  /**
   * Removes all occurrences of a Tune from the PlayList
   * <p></p>
   * @param tune is of type Tune
   * @return true iff the tune was found
   */
  boolean remove(Tune tune) {
    return myPlayList.remove(tune);
  }

  /** Returns the size of the PlayList as an int
   * <p></p>
   * @return int indicating the number of Tunes in the PlayList
   */
  int size() {
    return myPlayList.size();
  }

  /** Sorts by title, IGNORING CASE
   * <p></p>
   * returns void -- the PlayList is sorted in place
   * "By title, ignoring case" to be considered the NATURAL ordering
   */
  void sortByTitle() {
    // STUDENT TO IMPLEMENT THIS METHOD. REMEMBER TO IGNORE CASE.
    Collections.sort(myPlayList, Comparator.comparing(Tune::getTitle));
  }

  /**
   * Sorts so that the slower tempo items are at the front
   * <p></p>
   * returns void -- the PlayList is sorted in place
   */
  void sortByTempo() {
    // STUDENT TO IMPLEMENT THIS METHOD
    Collections.sort(myPlayList, new TempoComparator());
  }

  /**
   * Returns PlayList as a string suitable for printing
   * <p></p>
   * @return a String with 1 Tune per line
   * Tune.toString determines how to print each Tune
   * The returned list is enclosed in {curly braces}.
   */
  @Override
  public String toString() {
    String soFar = "{\n";
    for (Tune aTune : myPlayList) {
      soFar += aTune.toString();
    }
    return soFar + "\n}";
  }
}
