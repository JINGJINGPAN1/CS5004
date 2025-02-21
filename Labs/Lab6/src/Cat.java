import java.util.Objects;

/**
 * The Cat class represents a cat with attributes such as name, age, color, and owner.
 * It provides getter and setter methods, a custom toString representation,
 * and overridden equals and hashCode methods for object comparison and hashing.
 */
public class Cat {
  private String name;
  private int age;
  private Color color;
  private Person owner;

  /**
   * Constructs a Cat object with the specified attributes.
   *
   * @param name  the name of the cat
   * @param age   the age of the cat
   * @param color the color of the cat as a Color enum
   * @param owner the owner of the cat as a Person object
   */
  public Cat(String name, int age, Color color, Person owner) {
    this.name = name;
    this.age = age;
    this.color = color;
    this.owner = owner;
  }

  /**
   * Gets the cat's name.
   *
   * @return the name of the cat
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the cat's age.
   *
   * @return the age of the cat
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the cat's age.
   *
   * @param age the new age of the cat
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the color of the cat.
   *
   * @return the color of the cat as a Color enum
   */
  public Color getColor() {
    return color;
  }

  /**
   * Gets the cat's owner.
   *
   * @return the owner of the cat
   */
  public Person getOwner() {
    return owner;
  }

  /**
   * Sets the cat's owner.
   *
   * @param owner the new owner of the cat
   */
  public void setOwner(Person owner) {
    this.owner = owner;
  }

  /**
   * Returns a string representation of the Cat object, including all attributes.
   *
   * @return a formatted string with the cat's details
   */
  @Override
  public String toString() {
    String str = "Cat Details:\n";
    str += "Name: " + getName() + "\n";
    str += "Age: " + getAge() + "\n";
    switch (this.color) {
      case RED:
      case YELLOW:
        str += "Color: Primary" + "\n";
        break;
      case ORANGE:
        str += "Color: Pastel" + "\n";
        break;
      default:
        str += "Color: Neutral" + "\n";
    }
    str += "Owner: " + getOwner().getFirstName() + " " + getOwner().getLastName();
    return str;
  }

  /**
   * Checks if this Cat object is equal to another object.
   * Compares all fields for equality.
   *
   * @param o the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !(o instanceof Cat)) {
      return false;
    }
    Cat cat = (Cat) o;
    return this.name == cat.name
        && this.age == cat.age
        && this.color == cat.color
        && this.getOwner() == cat.getOwner();
  }

  /**
   * Generates a hash code for the Cat object based on its fields.
   *
   * @return the hash code of the Cat object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, age, color, owner);
  }
}
