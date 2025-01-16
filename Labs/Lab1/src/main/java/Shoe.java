/*
 * The Shoe class represents a shoe with attributes such as type, color, brand, and size.
 * It provides methods to retrieve these attributes and display the shoe's details as a string.
 */
public class Shoe {
  // Attributes to describe the shoe
  private String type;  // The type of shoe (e.g., sneakers, boots)
  private String color; // The color of the shoe
  private String brand; // The brand of the shoe
  private double size;  // The size of the shoe

  /**
   * Constructor to initialize a Shoe object with specific attributes.
   * @param type  The type of shoe (e.g., sneakers, boots)
   * @param color The color of the shoe
   * @param brand The brand of the shoe
   * @param size  The size of the shoe
   */
  public Shoe(String type, String color, String brand, double size) {
    this.type = type;
    this.color = color;
    this.brand = brand;
    this.size = size;
  }

  // Getter method for the type of the shoe
  public String getType() {
    return type;
  }

  // Getter method for the color of the shoe
  public String getColor() {
    return color;
  }

  // Getter method for the brand of the shoe
  public String getBrand() {
    return brand;
  }

  // Getter method for the size of the shoe
  public double getSize() {
    return size;
  }

  /**
   * Returns a string representation of the Shoe object.
   * @return A string containing the shoe's type, color, brand, and size.
   */
  public String toString() {
    String str;
    str = "Type: " + type + "\nColor: " + color + "\nBrand: " + brand + "\nSize: " + size;
    return str;
  }

}
