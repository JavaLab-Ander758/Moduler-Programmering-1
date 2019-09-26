package UtvidGeometricObject;

public class TestGeometricObjects {
	public static String getClassName(Object o){
		return o.getClass().getSimpleName();
	}
	
	public static void main(String[] args) {
		
		GeometricObject circle1 = new Circle(2);
		GeometricObject circle2 = new Circle(4);
		GeometricObject a = max(circle1, circle2);
		System.out.printf("The biggest circle: \n%s \n\n", a.toString());
		
		GeometricObject rectangle1 = new Rectangle(2, 4000);
		GeometricObject rectangle2 = new Rectangle(4, 6);
		GeometricObject b = max(rectangle1, rectangle2);
		System.out.printf("The biggest rectangle: \n%s \n\n", b.toString());
		
		GeometricObject triangle1 = new Triangle(9, 10, 9);
		GeometricObject triangle2 = new Triangle(2, 3, 4);
		GeometricObject c = max(triangle1, triangle2);
		System.out.printf("The biggest triangle: \n%s \n\n", c.toString());
		
		System.out.print("The biggest geometric object is: ");
		if (a.getArea() > b.getArea() && a.getArea() > c.getArea())
			System.out.printf("%s \n%s", getClassName(a), a.toString());
		else if (b.getArea() > a.getArea() && b.getArea() > c.getArea())
			System.out.printf("%s \n%s", getClassName(b), b.toString());
		else
			System.out.printf("%s \n%s", getClassName(c), c.toString());
	
	}

	public static GeometricObject max(GeometricObject obj1, GeometricObject obj2) {
	  if (obj1.compareTo(obj2) == 1)
		  return obj1;
	  else if (obj1.compareTo(obj2) == -1)
		  return obj2;
	  else
		  return null;
    }

}
