// STUDENTS DO NOT NEED TO MODIFY THIS FILE //

/** Minimal Person class for use on the midterm.
 * THIS ONE DOES NOT NEED JAVADOCS OR TESTING FOR THE MIDTERM
 */

/**
 * Constructor for a minimal Person for the midterm
 */
public class Person {
  private String name;
  private int birthYear;

  public Person(String name, int birthYear) {
    this.name = name;
    this.birthYear = birthYear;
  }

  public String getName() {
    return name;
  }

  public int getBirthYear() {
    return birthYear;
  }
}
