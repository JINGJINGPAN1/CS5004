public abstract class ToDoItemAbs implements ToDoItem, Comparable<ToDoItem> {
  protected String description;
  protected int urgency;
  protected Importance importance;

  /**
   * Constructs a ToDoItemAbs with a description, importance, and urgency.
   *
   * @param description A string describing the task.
   * @param importance The importance level (LOW, MEDIUM, HIGH).
   * @param urgency An integer representing urgency (higher = more urgent).
   */
  public ToDoItemAbs(String description,
      Importance importance,
      int urgency) {
    this.description = description;
    this.importance = importance;
    this.urgency = urgency;
  }

  /**
   * Gets the description of the ToDoItem.
   *
   * @return The description as a String.
   */
  public String getDescription() {
    return this.description;
    }

  /**
   * Returns the importance of this item
   * <p></p>
   * @return Importance (an enum: LOW, MEDIUM, or HIGH)
   */
  @Override
  public Importance getImportance() {
    return this.importance;
  }

  /**
   * Gets the urgency of the ToDoItem.
   *
   * @return rgency of this item as an int
   */
  @Override
  public int getUrgency() {
    return this.urgency;
  }

  /**
   * Sets the urgency of the ToDoItem.
   *
   * @param urgency The new urgency value.
   */
  public void setUrgency(int urgency) {
    this.urgency = urgency;
  }

  /**
   * Comparable Interface for sorting by importance
   * @param other -- a ToDoItem
   * @return negative, 0, or positive int for LOW, MEDIUM, or HIGH
   */
  @Override
  public int compareTo(ToDoItem other) {
    // STUDENT TO IMPLEMENT THIS METHOD
    if(this.importance.ordinal() < other.getImportance().ordinal()){
      return -1;
    }
    else if(this.importance.ordinal() > other.getImportance().ordinal()){
      return 1;
    }
    return 0;
    }
}
