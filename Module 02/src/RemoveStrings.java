import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveStrings {

	public static void main(String[] args) {
		
		// Sjekk om rett antall argumenter
		if (args.length != 2) {
			System.out.println("\nUsage: java RemoveStrings \"stringToBeRemoved\" sourceFile.txt");
			System.out.println("Process finished with exit code 1 \n");
			System.exit(1);
		}
		
		final String STRING_TO_BE_REMOVED = args[0]; // <- Remove this string from argument file
		java.io.File sourceFile = new java.io.File(args[1]); // <- argument file
		ArrayList<String> myArrayList = new ArrayList<String>();
		
		// Fjern eventuelle Strings fra sourceFile
		Scanner fileScanner = null;
		PrintWriter writer = null;
		try {
			fileScanner = new Scanner(sourceFile);
			System.out.printf("\nReading and removing string '%s' from file '%s' \n", args[0], args[1]);
			
			while (fileScanner.hasNextLine())
				myArrayList.add(fileScanner.nextLine());
			for (int i = 0; i < myArrayList.size(); i++) 
				myArrayList.set(i, myArrayList.get(i).replaceAll(STRING_TO_BE_REMOVED, ""));
			
			writer = new PrintWriter(sourceFile);
			System.out.printf("Done! \nWriting back to file '%s' \n", args[0]);
			
			for (String myStringLine : myArrayList)
				writer.println(myStringLine);
			System.out.println("Done! \n");
		} catch (FileNotFoundException e) {
			System.out.printf("\nSource file '%s' not exist\n", args[1]);
			System.out.println("Process finished with exit code 2 \n");
			System.exit(2);
		} finally {
			if (fileScanner != null)
				fileScanner.close();
			if (writer != null)
				writer.close();
		}
		

		
		
	}
}