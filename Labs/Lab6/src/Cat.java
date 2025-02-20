public class Cat {
  private String name;
  private int age;
  private Color color;
  private Person owner;

  public Cat(String name, int age, Color color, Person owner) {
    this.name = name;
    this.age = age;
    this.color = color;
    this.owner = owner;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Color getColor() {
    return color;
  }


  public Person getOwner() {
    return owner;
  }

  public void setOwner(Person owner) {
    this.owner = owner;
  }


  public String toString() {
    String str = "Cat Details:\n";
    String name = getName();
    String age = getAge() + "";
    String color = getColorName();
    String ownerFirstName = getOwner().getFirstName();
    String ownerLastName = getOwner().getLastName();
    String ownerName = ownerFirstName + " " + ownerLastName;

    str += "Name: " + name + "\n";
    str += "Age: " + age + "\n";
    str += "Color: " + color + "\n";
    str += "Type: " + ownerName;

    return str;

  }

  private String getColorName() {
    switch (this.color) {
      case RED:
      case YELLOW:
        return "Primary";
      case ORANGE:
        return "Pastel";
      default:
        return "Neutral";
    }
  }

  public boolean equals(Cat otherCat) {
    if(this == otherCat){
      return true;
    }

    if(otherCat == null){
      return false;
    }

    if(!(otherCat instanceof Cat)){
      return false;
    }

    return this.name.equals(otherCat.getName()) && this.age == otherCat.getAge()
            && this.color == otherCat.getColor() && this.owner == otherCat.getOwner();
  }

}
