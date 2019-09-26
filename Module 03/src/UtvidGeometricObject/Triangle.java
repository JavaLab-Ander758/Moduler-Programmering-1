package UtvidGeometricObject;

public class Triangle extends GeometricObject {
	private double side1, side2, side3;
	
	public Triangle() {
		this.side1 = 1.0;
		this.side2 = 1.0;
		this.side3 = 1.0;
		}
	public Triangle(double side) {
		this.side1 = side;
		this.side2 = side;
		this.side3 = side;
	}
	public Triangle(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	public Triangle(double side1, double side2, double side3, String color, boolean filled) {
		super(color, filled);
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	public double getSide1() {
		return side1;
	}
	public void setSide1(double side1) {
		this.side1 = side1;
	}
	public double getSide2() {
		return side2;
	}
	public void setSide2(double side2) {
		this.side2 = side2;
	}
	public double getSide3() {
		return side3;
	}
	public void setSide3(double side3) {
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
	public boolean equals(Object other) {
		if (equals(other))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return String.format("Sides: %s, %s, %s \n%s", side1, side2, side3, super.toString());
	}
	
	
	
}