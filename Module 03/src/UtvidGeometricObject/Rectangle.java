package UtvidGeometricObject;

public class Rectangle extends GeometricObject {
	private double width, height;
	
	public Rectangle(){
		this.width = 1.0;
		this.height = 1.0;
	}
	public Rectangle(double side){
		this.width = side;
		this.height = side;
	}
	public Rectangle(double width, double height){
		this.width = width;
		this.height = height;
	}
	public Rectangle(double width, double height, String color, Boolean filled){
		super(color, filled);
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getArea() {
		return width * height;
	}
	public double getPerimeter() {
		return 2 * (width + height);
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
		return String.format("Width: %.2f Height: %.2f \n%s", width, height, super.toString());
	}
	
}
