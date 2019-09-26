package TriangleKlassen;

import java.util.Scanner;

public class TestTriangle {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Side 1? ");
		double side1 = input.nextDouble();
		System.out.print("Side 2? ");
		double side2 = input.nextDouble();
		System.out.print("Side 3? ");
		double side3 = input.nextDouble();
		input.close();
		
		Triangle myTriangle = new Triangle(side1, side2, side3, "Pink", false);
		
		System.out.printf("\nArea: %.2f \n", myTriangle.getArea());
		System.out.printf("Perimeter: %.2f \n\n", myTriangle.getPerimeter());
		System.out.printf("%s", myTriangle.toString());
	}

}
