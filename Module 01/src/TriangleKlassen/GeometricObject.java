package TriangleKlassen;

public class GeometricObject {
  private String color;
  private boolean filled;
  private java.util.GregorianCalendar dateCreated;

  /**
   * Construct a default geometric object
   */
  public GeometricObject() {
    this("White", false);
  }

  /**
   * Construct a geometric object with the specified color and filled value
   */
  public GeometricObject(String color, boolean filled) {
    dateCreated = new java.util.GregorianCalendar();
    this.color = color;
    this.filled = filled;
  }

  /**
   * Return color
   */
  public String getColor() {
    return color;
  }

  /**
   * Set a new color
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Return filled. Since filled is boolean, its getter method is named isFilled
   */
  public boolean isFilled() {
    return filled;
  }

  /**
   * Set a new value for filled
   */
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  /**
   * Get dateCreated
   */
  public java.util.GregorianCalendar getDateCreated() {
    return dateCreated;
  }

  /**
   * Return a string representation of this object
   */
  public String toString() {
    return String.format("Created on: %tF %1$tT %nColor: %s %nIs filled: %s", dateCreated, color, filled ? "yes": "no");
  }
}