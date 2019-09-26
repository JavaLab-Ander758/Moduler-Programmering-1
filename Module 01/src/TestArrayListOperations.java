import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class TestArrayListOperations {

	public static void main(String[] args) {		
		ArrayListOperations testArray = new ArrayListOperations();
		Scanner input = new Scanner(System.in);
		
		System.out.print("Gi inn Integer i arrayListen: ");
		int addedNumber = input.nextInt();
		testArray.addNumber(addedNumber);
		System.out.print("Fjern et av tallene i arrayListen(1->10 eller nummeret du la inn): ");
		int removeNumber = input.nextInt();
		testArray.removeNumber(removeNumber);
		
		System.out.printf("\nDoes list contain %s? %s \n", addedNumber, (testArray.checkAdded(addedNumber)) ? "Yes" : "No");
		System.out.printf("Does list contain %s? %s \n\n", removeNumber, (testArray.checkRemoved(removeNumber)) ? "Yes" : "No");
		
		testArray.manualShuffle();
		testArray.printArrayList();
		System.out.println();
		testArray.orderArrayListDescending();
		testArray.printArrayList();
	}
}

class ArrayListOperations {
	private Integer[] array = new Integer[10];
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private int added, removed;
	
	public ArrayListOperations() {
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
			arrayList.add(i, array[i]);
		}
	}

	public void addNumber(int number) {
		// Gi inn 
		arrayList.add(number);
		added = number;
	}
	
	public void removeNumber(int removeNumber) {
		// Gi inn nummer som skal fjernes
		arrayList.remove(arrayList.indexOf(removeNumber));
	}
	public boolean checkAdded(int addedNumber) {
		if (arrayList.contains(addedNumber))
			return true;
		else
			return false;
	}
	public boolean checkRemoved(int removedNumber) {
		if (arrayList.contains(removedNumber))
			return true;
		else
			return false;
	}
	
	public void manualShuffle() {
		Random rgen = new Random();
		
		for (int i = 0; i < arrayList.size(); i++) {
			int randomPosition = rgen.nextInt(arrayList.size());
			int temp = arrayList.get(i);
			arrayList.set(i, arrayList.get(randomPosition));
			arrayList.set(randomPosition, temp);
		}

	}
	
	public void orderArrayListDescending() {
		Collections.sort(arrayList, Collections.reverseOrder());
	}
	
	public void printArrayList() {
		for (int i = 0; i < arrayList.size(); i++)
			System.out.print(arrayList.get(i) + " ");
	}
}