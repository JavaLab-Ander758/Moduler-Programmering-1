import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadScores {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter file name: ");
		java.io.File sourceFile = new java.io.File(input.nextLine());
		input.close();
		
		ArrayList<Integer> myScores = new ArrayList<>();
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(sourceFile);
			while (fileScanner.hasNext()) {
				if (fileScanner.hasNextInt())
					myScores.add(fileScanner.nextInt());
				else 
					fileScanner.next();
			}
			
			int sum = 0;
			for (int score : myScores)
				sum += score;
			System.out.printf("Total is: %s \n", sum);
			System.out.printf("Average is: %.2f", (double)sum/myScores.size());
		
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist! :(");
		} finally {
			if (fileScanner != null)
				fileScanner.close();
		}		
	}
}