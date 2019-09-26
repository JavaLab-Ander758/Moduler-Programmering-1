package Vehicle;

import java.util.Calendar;

public class Bicycle extends Vehicle{
    private int gears;
    private Calendar productionDate;

    public Bicycle() {
    }
    public Bicycle(String name, String colour, int price, int model, String serialNr, int direction, int gears) {
        super(name, colour, price, model, serialNr, direction);
        this.setGears(gears);
        this.productionDate = Calendar.getInstance();
    }

    @Override
    public void setAllFields() {
        super.setAllFields();
        System.out.print("Gears:");
        setGears(input.nextInt());
        setProductionDate(Calendar.getInstance());
    }
    @Override
    public void turnRight(int degrees) {
        direction += degrees;
        System.out.println("Sykkel snudd %s grader til høyre");
    }
    @Override
    public void turnLeft(int degrees) {
        direction -= degrees;
        System.out.println("Sykkel snudd %s grader til venstre");
    }
    public int getGears() {
        return gears;
    }
    public void setGears(int gears) {
        this.gears = gears;
    }
    public Calendar getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(Calendar productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return String.format("%s Gears: %d Production date: %s",super.toString(), gears, getProductionDate().getTime());
    }
}