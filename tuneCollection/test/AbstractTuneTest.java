import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// STUDENT TO TEST ALL METHODS IN AbstractTune
// TWO ASSERTIONS PER METHOD, THREE FOR COMPARISONS

class AbstractTuneTest {
  private Mp3Tune mp3Tune1;
  private Mp3Tune mp3Tune2;
  private WavTune wavTune1;


  Person person1;
  Person person2;

  @BeforeEach
  void setUp() {
    person1 = new Person("Jingjing", 2000);
    person2 = new Person("Lily", 1998);

    mp3Tune1 = new Mp3Tune("Big City", person1, Genre.CLASSICAL, 50, "/jingjing/tune/classic");
    mp3Tune2 = new Mp3Tune("Little World", person2, Genre.HIPHOP, 20, "/lily/tune/hiphop");

    wavTune1 = new WavTune("cutter", person1, Genre.POP, 10, "/jingjing/tune/pop");

  }

  @Test
  void testInvalidConstructor() {
    // I just gave tests to test Invalid constructor here.
    Genre genre1 = Genre.CLASSICAL;
    int tempo1 = 120;
    String filePath1 = "/music/classical.mp3";

    assertThrows(IllegalArgumentException.class, () ->
        new Mp3Tune("Symphony No. 5", null, genre1, tempo1, filePath1)
    );

    Genre genre2 = Genre.CLASSICAL;
    int tempo2 = 120;
    String filePath2 = "/music/classical.mp3";

    assertThrows(IllegalArgumentException.class, () ->
        new Mp3Tune("", person1, genre2, tempo2, filePath2)
    );
  }

  @Test
  void testValidConstructor() {
    // Valid object creation should not throw an exception
    assertDoesNotThrow(() -> mp3Tune1);
    assertDoesNotThrow(() -> mp3Tune2);
  }

  @Test
  void testToString() {
    String expected1 = "Tune Details:\n"
        + "Title: Big City\n"
        + "Artist: Jingjing\n"
        + "Genre: Classical\n"
        + "Tempo: 50\n"
        + "FilePath: /jingjing/tune/classic\n";
    assertEquals(expected1, mp3Tune1.toString());

    String expected2 = "Tune Details:\n"
        + "Title: cutter\n"
        + "Artist: Jingjing\n"
        + "Genre: Pop\n"
        + "Tempo: 10\n"
        + "FilePath: /jingjing/tune/pop\n";
    assertEquals(expected2, wavTune1.toString());

  }

  @Test
  void getTitle() {
    assertEquals("Big City", mp3Tune1.getTitle());
    assertEquals("Little World", mp3Tune2.getTitle());
  }

  @Test
  void compareTo() {
    Mp3Tune mp3Tune3 = new Mp3Tune("big City", person1, Genre.CLASSICAL, 50, "/jingjing/tune/classic");

    assertEquals(0, mp3Tune1.compareTo(mp3Tune3));
    assertTrue(mp3Tune1.compareTo(mp3Tune2) < 0);
    assertTrue(mp3Tune2.compareTo(mp3Tune1) > 0);

  }

  @Test
  void testGetTitle() {
    assertEquals("Big City", mp3Tune1.getTitle());
    assertEquals("Little World", mp3Tune2.getTitle());
  }

  @Test
  void getArtist() {
    assertEquals(person1, mp3Tune1.getArtist());
    assertEquals(person2, mp3Tune2.getArtist());
  }

  @Test
  void getGenre() {
    assertEquals(Genre.CLASSICAL, mp3Tune1.getGenre());
    assertEquals(Genre.HIPHOP, mp3Tune2.getGenre());
  }

  @Test
  void getTempo() {
    assertEquals(50, mp3Tune1.getTempo());
    assertEquals(20, mp3Tune2.getTempo());
  }

  @Test
  void getFilePath() {
    assertEquals("/jingjing/tune/classic", mp3Tune1.getFilePath());
    assertEquals("/lily/tune/hiphop", mp3Tune2.getFilePath());
  }

  @Test
  void testEquals() {
    Mp3Tune mp3Tune3 = new Mp3Tune("Big City", person1, Genre.CLASSICAL, 50, "/jingjing/tune/classic");

    assertFalse(mp3Tune1.equals(mp3Tune2));
    assertTrue(mp3Tune1.equals(mp3Tune3));

  }

  @Test
  void testHashCode() {
    assertNotEquals(mp3Tune1.hashCode(), mp3Tune2.hashCode());

    Mp3Tune mp3Tune3 = new Mp3Tune("Big City", person1, Genre.CLASSICAL, 50, "/jingjing/tune/classic");
    assertEquals(mp3Tune1.hashCode(), mp3Tune3.hashCode());

  }

  @Test
  void testToString1() {
  }

  @Test
  void testCompareTo() {
  }
}
