package Vehicle;

/**
 * TestVehicles oppretter Bicycle og Car objekter, legger disse i et ArrayList
 * Lar bruker manipulere data i arrayet på forskjellige måter
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleTest {
    Scanner input = new Scanner(System.in);
    String testName = "", testDirection = "";
    int testDegrees = 0;

    public static void main(String[] args) {


        VehicleTest vtest = new VehicleTest();
        try {
            vtest.menuLoop();
        } catch(IOException e) {
            System.out.println("IO Exception!");
            System.exit(1);
        } catch(CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException");
            System.exit(1);
        }
    }

    private void menuLoop() throws IOException, CloneNotSupportedException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Vehicle> arr=new ArrayList<Vehicle>();
        Vehicle vehicle;

        arr.add(new Car("Volvo","Black",85000,2010,"1010-11",163,0));
        arr.add(new Bicycle("Diamant","yellow",4000,1993,"BC100",10,0));
        arr.add(new Car("Ferrari Testarossa","red",1200000,1996,"A112",350,0));
        arr.add(new Bicycle("DBS","pink",5000,1994,"42",10,0));

        while(true) {
            System.out.println("1...................................New car");
            System.out.println("2...............................New bicycle");
            System.out.println("3......................Find vehicle by name");
            System.out.println("4..............Show data about all vehicles");
            System.out.println("5.......Change direction of a given vehicle");
            System.out.println("6..............................Exit program");
            System.out.println(".............................Your choice?");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    //legg til en ny bil
                    arr.add(new Car());
                    arr.get(arr.size() - 1).setAllFields();
                    break;
                case 2:
                    //legg til en ny sykkel
                    arr.add(new Bicycle());
                    arr.get(arr.size() - 1).setAllFields();
                    break;
                case 3:
                    //vis info om gitt kjøretøy
                    System.out.print("Name of vehicle: ");
                    testName = input.nextLine();
                    for (Vehicle i : arr)
                        if (testName.equals(i.getName()))
                            System.out.printf("%s \n\n", i.toString());
                    break;
                case 4:
                    //vis info om alle kjøretøy
                    for (Vehicle i : arr)
                        System.out.printf("%s \n", i.toString());
                    System.out.println();
                    break;
                case 5:
                    // Finn kjøretøy med gitt navn, sett ny retning
                    System.out.print("Name of vehicle: ");
                    testName = input.nextLine();
                    System.out.print("Direction [R/L]: ");
                    testDirection = input.nextLine();
                    System.out.print("Degrees [0-360]: ");
                    testDegrees = input.nextInt();
                    input.nextLine();

                    for (int i = 0; i < arr.size(); i++) {
                        if (testName.equals(arr.get(i).getName())) {
                            if (testDirection.toUpperCase().equals("R"))
                                arr.get(i).turnRight(testDegrees);
                            if (testDirection.toUpperCase().equals("L"))
                                arr.get(i).turnLeft(testDegrees);
                        }
                    }
                    break;
                case 6:
                    scan.close();
                    System.exit(0);
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
}