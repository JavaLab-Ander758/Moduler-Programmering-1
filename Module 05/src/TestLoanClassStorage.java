import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class TestLoanClassStorage extends Application {
    private DropShadow shadow = new DropShadow();
    private String initTextField = String.format("|%-15s|%-30s|%-30s|%-30s| \n", "Object#", "annualInterestRate", "numberOfYears", "loanAmount");
    private int i = 1;
    private boolean clearCont = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Check if File loanObjects exists, if not create one */
        String workingPath = System.getProperty("user.dir") + "\\loanObjects";
        File objFile = new File(workingPath);

        //<editor-fold desc="GUI">
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 300);
        primaryStage.setTitle(this.getClass().getName());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        VBox border = new VBox();
        border.setAlignment(Pos.CENTER);
        root.getChildren().add(border);

        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(15, 15, 15, 15));
        topBox.setAlignment(Pos.CENTER);
        TextField inputAnnualInterestRate = new TextField();
            inputAnnualInterestRate.setPromptText("annualInterestRate");
            HBox.setHgrow(inputAnnualInterestRate, Priority.ALWAYS);
        TextField inputNumberOfYears = new TextField();
            inputNumberOfYears.setPromptText("numberOfYears");
            HBox.setHgrow(inputNumberOfYears, Priority.ALWAYS);
        TextField inputLoanAmount = new TextField();
            inputLoanAmount.setPromptText("loanAmount");
            HBox.setHgrow(inputLoanAmount, Priority.ALWAYS);
        Button appendToGUIButton = new Button("Legg til element i din liste"); // Legg object til midlertidig arrayList
            HBox.setHgrow(appendToGUIButton, Priority.ALWAYS);
        Button resetButton = new Button("Reset");
            HBox.setHgrow(resetButton, Priority.ALWAYS);
        topBox.getChildren().addAll(inputAnnualInterestRate, inputNumberOfYears, inputLoanAmount, appendToGUIButton, resetButton);
        border.getChildren().add(topBox);

        HBox midBox = new HBox(10);
        midBox.setPadding(new Insets(0, 15, 0, 15));
        midBox.setAlignment(Pos.CENTER);
        Button clearFileButton = new Button("Clear File");
            HBox.setHgrow(clearFileButton, Priority.ALWAYS);
        Button writeTempObjectsButton = new Button("Skriv element(er) til fil"); // Skrive midlertidig arrayList til default arrayList;
            HBox.setHgrow(writeTempObjectsButton, Priority.ALWAYS);
        Button showObjectsFromFileButton = new Button("Vis objekter fra fil");
            HBox.setHgrow(showObjectsFromFileButton, Priority.ALWAYS);
        midBox.getChildren().addAll(writeTempObjectsButton, showObjectsFromFileButton, clearFileButton);
        border.getChildren().add(midBox);

        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(10, 10, 10, 10));
        TextArea outputText = new TextArea();
        VBox.setVgrow(outputText, Priority.ALWAYS);
            outputText.setPrefHeight(scene.getHeight());
            outputText.appendText(initTextField);
            outputText.setFont(Font.font("Monospaced")); // Change font to not make table shift right
        bottomBox.getChildren().add(outputText);
        border.getChildren().add(bottomBox);
        //</editor-fold>

        ArrayList<Loan> tempObjects = new ArrayList<>();
        //<editor-fold desc="Buttons">
        appendToGUIButton.setOnMouseClicked(e ->{
            if (checkIfEmptyStrings(new String[]{inputAnnualInterestRate.getText(), inputNumberOfYears.getText(), inputLoanAmount.getText()})){
                if (i == 0)
                    i++;
                Loan loan = new Loan(Integer.valueOf(inputAnnualInterestRate.getText()), Integer.valueOf(inputNumberOfYears.getText()), Integer.valueOf(inputLoanAmount.getText()));
                outputText.appendText(String.format("| %-13d | %-28s | %-28s | %-28s | \n", i, loan.getAnnualInterestRate(), loan.getNumberOfYears(), loan.getLoanAmount()));
                tempObjects.add(loan);
                if (i != 0)
                    i++;
            }
        });
        writeTempObjectsButton.setOnMouseClicked(e ->{
            if (tempObjects.isEmpty())
                outputText.appendText("ArrayList empty! Did not write! \n");
            else {
                ArrayList<Loan> initialObjList = new ArrayList<>();
                initialObjList = getObjectsFromFile(initialObjList, objFile);
                initialObjList.addAll(tempObjects);
                writeObjectsToFile(initialObjList, objFile);
                tempObjects.clear();
                outputText.setText(initTextField);
                i = 1;
                buttonEnabler(appendToGUIButton, true);
            }
        });
        showObjectsFromFileButton.setOnMouseClicked(e ->{
            if (objFile.length() == 0) {
                outputText.appendText("No objects in file, did not fetch! \n");
                return;
            }
            ArrayList<Loan> objectsFromFile = getObjectsFromFile(new ArrayList<Loan>(), objFile);
            for (int i = 0; i < objectsFromFile.size(); i++)
                outputText.appendText(String.format("| %-13d | %-28s | %-28s | %-28s | \n", i+1, objectsFromFile.get(i).getAnnualInterestRate(), objectsFromFile.get(i).getNumberOfYears(), objectsFromFile.get(i).getLoanAmount()));
            buttonEnabler(appendToGUIButton, false);
            buttonEnabler(writeTempObjectsButton, false);
            buttonEnabler(showObjectsFromFileButton, false);
        });
        resetButton.setOnMouseClicked(e ->{
            i = 0;
            tempObjects.clear();
            outputText.setText(initTextField);
            buttonEnabler(appendToGUIButton, true);
            buttonEnabler(writeTempObjectsButton, true);
            buttonEnabler(showObjectsFromFileButton, true);
            clearTextFields(new TextField[]{inputAnnualInterestRate, inputNumberOfYears, inputLoanAmount});
        });
        clearFileButton.setOnMouseClicked(e ->{
            if (clearCont){
                clearFileButton.setText("Are you sure?");
                clearCont = false;
            } else {
                clearFileButton.setText("Clear File");
                clearFile(objFile);
                clearCont = true;
                outputText.setText(initTextField);
            }
        });
        //</editor-fold>
    }
    private ArrayList getObjectsFromFile(ArrayList emptyObjectList, File file){
        boolean cont = true;
        try {
            FileInputStream fi = new FileInputStream(file.getPath());
            ObjectInputStream oi = new ObjectInputStream(fi);
            while (cont) {
                Object obj = oi.readObject();
                if (obj != null)
                    emptyObjectList.add(obj);
                else
                    cont = false;
            }
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }
        return emptyObjectList;
    }
    private void writeObjectsToFile(ArrayList objectList, File file){
        try {
            FileOutputStream fileOut = new FileOutputStream(file.getPath());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            for (Object obj : objectList)
                objectOut.writeObject(obj);
            objectOut.close();

            System.out.printf("Object(s) written to '%s' \n", file.getName());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in writing to file");
        }
    }
    private void buttonEnabler(Button button, boolean condition){
        if (condition){
            button.setDisable(false);
            button.setEffect(null);
        } else {
            button.setDisable(true);
            button.setEffect(shadow);
        }

    }
    private boolean fileExist(File file){
        if (!(file.exists() && !file.isDirectory()))
            return false;
        else
            return true;
    }
    private void createFileInDir(File file){
        try {
            FileWriter fw = new FileWriter(file.getPath());
            System.out.println("file did not exist... Making File 'loanObjects'");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("Could not write File '%s'!", file.getName());
        }
    }
    private void clearFile(File file){
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (Exception e) {
            System.out.printf("Error in clearFile('%s') %n", file.getName());
        }

    }
    private boolean checkIfEmptyStrings(String[] strings){
        boolean temp = true;
        for (String str : strings)
            if (str.equals("")){
                temp = false;
                break;
            }
        return temp;
    }
    private void clearTextFields(TextField[] textFields){
        for (TextField obj : textFields)
            obj.clear();
    }
}

class Loan implements Serializable {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;

    /** Default constructor */
    public Loan() {
        this(2.5, 1, 1000);
    }

    /** Construct a loan with specified annual interest rate,
     number of years and loan amount
     */
    public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new java.util.Date();
    }

    /** Return annualInterestRate */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /** Set a new annualInterestRate */
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /** Return numberOfYears */
    public int getNumberOfYears() {
        return numberOfYears;
    }

    /** Set a new numberOfYears */
    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /** Return loanAmount */
    public double getLoanAmount() {
        return loanAmount;
    }

    /** Set a newloanAmount */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    /** Find monthly payment */
    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                (Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12)));
        return monthlyPayment;
    }

    /** Find total payment */
    public double getTotalPayment() {
        double totalPayment = getMonthlyPayment() * numberOfYears * 12;
        return totalPayment;
    }

    /** Return loan date */
    public java.util.Date getLoanDate() {
        return loanDate;
    }
}