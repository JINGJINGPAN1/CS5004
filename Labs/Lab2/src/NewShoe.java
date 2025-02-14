public class NewShoe {
  private int shoeSize;
  private Type shoeType;
  private Brand brand;
  private Color color;

  public NewShoe(int size, Type type, Brand brand, Color color) {
    if (brand == Brand.NIKE && type == Type.DRESS) {
      throw new IllegalArgumentException("Nike does not sell dress shoes.");
    }
    this.shoeSize = size;
    this.shoeType = type;
    this.brand = brand;
    this.color = color;
  }

  public int getShoeSize() {
    return shoeSize;
  }

  public Type getShoeType() {
    return shoeType;
  }
  public Brand getBrand() {
    return brand;
  }
  public Color getColor() {
    return color;
  }

  public String toString() {
    String str = "Shoe Details:\n";
    int size = getShoeSize();
    String brandName = getBrandName();
    String colorName = getColorName();
    String typeName = getShoeTypeName();

    str += "Size: " + size + "\n";
    str += "Brand: " + brandName + "\n";
    str += "Color: " + colorName + "\n";
    str += "Type: " + typeName;

    return str;
  }

  private String getBrandName() {
    switch (this.brand) {
      case NIKE:
        return "Nike";
      case ADIDAS:
        return "Adidas";
      case PUMA:
        return "Puma";
      case NEW_BALANCE:
        return "New Balance";
      default:
        return "Unknown";
    }
  }

  public String getShoeTypeName() {
    switch (this.shoeType) {
      case BOOT:
        return "Boot";
      case DRESS:
        return "Dress";
      case SNEAKER:
        return "Sneaker";
      case CASUAL:
        return "Casual";
      default:
        return "Unknown";
    }
  }


  private String getColorName() {
    switch (this.color) {
      case RED:
      case GREEN:
      case YELLOW:
        return "Primary";
      case ORANGE:
      case PURPLE:
      case PINK:
        return "Pastel";
      default:
        return "Neutral";
    }
  }

}

