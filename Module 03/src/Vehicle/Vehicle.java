package Vehicle;

import java.util.Scanner;

public abstract class Vehicle {
    private String colour, name, serialNr;
    private int model, price;
    public int direction;
    private double speed;
    protected Scanner input = new Scanner(System.in);

    public Vehicle() {
    }
    public Vehicle(String name, String colour, int price, int model, String serialNr, int direction) {
        this.setColour(colour);
        this.setName(name);
        this.setSerialNr(serialNr);
        this.setModel(model);
        this.setPrice(price);
        this.setDirection(direction);
    }

    public void setAllFields() {
        System.out.println("Input vehicle data: ");
        System.out.print("Name: ");
        this.setName(input.nextLine());
        System.out.print("Colour: ");
        this.setColour(input.nextLine());
        System.out.print("SerialNr: ");
        this.setSerialNr(input.nextLine());
        System.out.print("Model: ");
        this.setModel(input.nextInt());
        System.out.print("Price: ");
        this.setPrice(input.nextInt());
    }
    public abstract void turnLeft(int degrees);
    public abstract void turnRight(int degrees);

    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSerialNr() {
        return serialNr;
    }
    public void setSerialNr(String serialNr) {
        this.serialNr = serialNr;
    }
    public int getModel() {
        return model;
    }
    public void setModel(int model) {
        this.model = model;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String toString() {
        return String.format("Name: %s Colour: %s Price: %s Model: %s SerialNr: %s Direction: %s Speed: %.2f "
                , name, colour, price, model, serialNr, direction, speed);
    }

}