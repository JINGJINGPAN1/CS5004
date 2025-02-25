import java.util.Comparator;

/**
 * TO BE COMPLETED BY STUDENT
 */
public class TempoComparator implements Comparator<Tune> {

  /**
   * Compares two Tunes based on their tempo in BPM (an integer)
   * <p></p>
   * @param tune1 the first object to be compared.
   * @param tune2 the second object to be compared.
   * @return int according to Java conventions
   */
  @Override
  public int compare(Tune tune1, Tune tune2) {
    if(tune1.getTempo() < tune2.getTempo()){
      return -1;
    }else if(tune1.getTempo() > tune2.getTempo()){
      return 1;
    }
    return 0;
  }
}
