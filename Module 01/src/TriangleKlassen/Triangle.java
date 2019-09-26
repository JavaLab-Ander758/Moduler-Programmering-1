package TriangleKlassen;

public class Triangle extends GeometricObject {
	private double side1, side2, side3;
	
	public Triangle() {
		setSides(1.0, 1.0, 1.0);
	}
	
	public Triangle(double side1, double side2, double side3) {
		this(side1, side2, side3,"white",false);
	}
	
	public Triangle(double side) {
		this(side, side, side,"white",false);
	}
	
	public Triangle(double side1, double side2, double side3, String color, boolean filled) {
		super(color, filled);
		setSides(side1, side2, side3);
	}
	
	public double[] getSides() {
		// Valgte � returnere et array her da det er flere elementer
		double sides[] = {side1, side2, side3};
		return sides;
	}
	
	public void setSides(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	public double getArea() {
		double s = (side1 + side2 + side3) / 2;
		return  Math.sqrt( s * (s - side1) * (s - side2) * (s - side3));
	}
	
	public double getPerimeter() {
		return side1 + side2 + side3;
	}
	
	@Override
	public String toString() {
		return String.format("Sides: %s, %s, %s \n%s", side1, side2, side3, super.toString());
	}
	
	
	
}