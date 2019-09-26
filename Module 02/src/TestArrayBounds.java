import java.util.Scanner;

public class TestArrayBounds {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int[] myArray = new int[100];
		
		// Fyll array med tall opp til 10000
		for (int i = 0; i < 100; i++)
			myArray[i] = (int)(Math.random() * 10000 + 1);
		
		System.out.print("Enter an index: ");
		int index = input.nextInt();
		
		// Fang eventuell error
		try {
			System.out.println(myArray[index]);
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			System.out.printf("Out of Bounds");
		}
	}

}
