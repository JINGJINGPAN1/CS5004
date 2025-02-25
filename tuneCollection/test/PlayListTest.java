import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class PlayListTest {
  private PlayList playlist1;
  private PlayList playlist2;

  private Tune mp3Tune1;
  private Tune mp3Tune2;

  private Tune wavTune1;
  private Tune wavTune2;

  Person person1;
  Person person2;
  @BeforeEach
  void setUp() {
    playlist1 = new PlayList();
    playlist2 = new PlayList();
    person1 = new Person("Jingjing", 2000);
    person2 = new Person("Lily", 1998);

    mp3Tune1 = new Mp3Tune("Big City", person1, Genre.CLASSICAL, 50, "/jingjing/tune/classic");
    mp3Tune2 = new Mp3Tune("Little World", person2, Genre.HIPHOP, 20, "/lily/tune/hiphop");

    wavTune1 = new WavTune("cutter", person1, Genre.POP, 10, "/jingjing/tune/pop");
    wavTune2 = new WavTune("Cat", person2, Genre.JAZZ, 810, "/lily/tune/jazz");

  }

  @Test
  void addItem() {
    playlist1.addTune(mp3Tune1);
    assertEquals(1, playlist1.size());

    playlist1.addTune(mp3Tune2);
    assertEquals(2, playlist1.size());
  }

  @Test
  void getItem() {
    playlist1.addTune(mp3Tune1);
    assertEquals(mp3Tune1, playlist1.getTune(0));

    playlist1.addTune(mp3Tune2);
    assertEquals(mp3Tune2, playlist1.getTune(1));

  }

  @Test
  void remove() {
    playlist1.addTune(mp3Tune1);
    assertTrue(playlist1.remove(mp3Tune1));

    playlist1.addTune(mp3Tune2);
    assertTrue(playlist1.remove(mp3Tune2));

  }

  @Test
  void size() {
    playlist1.addTune(mp3Tune1);
    playlist1.addTune(mp3Tune2);
    assertEquals(2, playlist1.size());
  }

  @Test
  void sortByTitle() {
    playlist1.addTune(mp3Tune1);
    playlist1.addTune(mp3Tune2);
    playlist1.addTune(wavTune1);
    playlist1.addTune(wavTune2);
    playlist1.sortByTitle();

    assertEquals(mp3Tune1, playlist1.getTune(0)); // "Big City"
    assertEquals(wavTune2, playlist1.getTune(1)); // "Cat"
    assertEquals(wavTune1, playlist1.getTune(2)); // "cutter"
    assertEquals(mp3Tune2, playlist1.getTune(3)); // "Little World"

  }

  @Test
  void sortByTempo() {
    playlist1.addTune(mp3Tune1);
    playlist1.addTune(mp3Tune2);
    playlist1.sortByTempo();
    assertEquals(mp3Tune2, playlist1.getTune(0));
    assertEquals(mp3Tune1, playlist1.getTune(1));

    playlist2.addTune(wavTune1);
    playlist2.addTune(wavTune2);
    playlist2.sortByTempo();
    assertEquals(wavTune1, playlist2.getTune(0));
    assertEquals(wavTune2, playlist2.getTune(1));
  }

  @Test
  void testToString() {
  }
}
