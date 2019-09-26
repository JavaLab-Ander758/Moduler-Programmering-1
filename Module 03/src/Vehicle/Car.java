package Vehicle;

import java.util.Calendar;

public class Car extends Vehicle {
    private int power;
    private Calendar productionDate;

    public Car() {
    }
    public Car(String name, String colour, int price, int model, String serialNr, int direction, int power) {
        super(name, colour, price, model, serialNr, direction);
        this.power = power;
        setProductionDate(Calendar.getInstance());
    }

    @Override
    public void setAllFields() {
        super.setAllFields();
        System.out.print("Power: ");
        setPower(input.nextInt());
        setProductionDate(Calendar.getInstance());
    }
    @Override
    public void turnRight(int degrees) {
        int dir;
        if (degrees >= 0 && degrees <= 360) {
            dir = super.getDirection() + degrees;
            super.setDirection(dir);
        }
        if (super.getDirection() > 360)
            super.setDirection(super.getDirection() - 360);
    }
    @Override
    public void turnLeft(int degrees) {
        int dir;
        if (degrees >= 0 && degrees <= 360) {
            dir = super.getDirection() - degrees;
            super.setDirection(dir);
        }
        if (super.getDirection() < 0)
            super.setDirection(super.getDirection() + 360);
    }

    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public Calendar getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(Calendar productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return String.format("%s Power: %d Production date: %s", super.toString(), power, getProductionDate().getTime());
    }
}