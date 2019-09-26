package UtvidGeometricObject;

public class Circle extends GeometricObject {
	private double radius;
	
	public Circle(){
		this.radius = 1.0;
	}
	public Circle(double radius){
		this.radius = radius;
	}
	public Circle(double radius, String color, boolean filled){
		super(color, filled);
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}
	public double getPerimeter() {
		return Math.PI * radius;
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
		return String.format("Radius: %.2f \n%s", radius, super.toString());
	}
	

}