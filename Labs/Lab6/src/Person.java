public class Person {
  private String firstName;
  private String lastName;
  private int yearOfBirth;

  public Person(String firstName, String lastName, int yearOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfBirth = yearOfBirth;
  }

  public String getFirstName(){
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public int getYearOfBirth() {
    return this.yearOfBirth;
  }

  public int getAge(){
    return 2025 - this.yearOfBirth;
  }

  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }

  /**
   * Returns a string representation of this person with first name, last name and its age
   * @return a formatted string
   */
  public String toString(){
    String name = "First Name: " + this.firstName + " Last Name: " + this.lastName;
    String age = String.valueOf(this.getAge());
    String str = "Name: "+ name + "\nAge: " + age;

    return str;
  }
}

