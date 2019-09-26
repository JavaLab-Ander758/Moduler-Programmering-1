package loanCalculator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class LoanCalculatorClient extends Application {
    private TextField tfAnnualInterestRate;
    private TextField tfNumOfYears;
    private TextField tfLoanAmount;
    private Button btSubmit;
    private TextArea ta;
    private DataOutputStream osToServer;
    private DataInputStream isFromServer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUpAndDisplayPrimaryStage(primaryStage);
        connectToServer();

        // Grab values from user TextFields -> Send values to Server -> Display results in TextArea 'ta'
        btSubmit.setOnMouseClicked(e ->{
            ta.clear();

            try {
                double annualInterestRate   = Double.parseDouble(tfAnnualInterestRate.getText());
                int numberOfYears           = Integer.parseInt(tfNumOfYears.getText());
                double loanAmount           = Double.parseDouble(tfLoanAmount.getText());

                contactServerForResult(annualInterestRate, numberOfYears, loanAmount);
            } catch (Exception ex) {
                appendError(ex.toString());
            }
        });
    }

    private GridPane getGridPane(){
        GridPane topPane = new GridPane();
        topPane.setAlignment(Pos.TOP_CENTER);
        topPane.setPadding(new Insets(1));
        topPane.setHgap(10);
        topPane.setVgap(3);
        topPane.add(new Label("Annual Interest Rate"), 0, 0); // Column->vertical Row->horizontal
        topPane.add(new Label("Number Of Years"), 0, 1);
        topPane.add(new Label("Loan Amount"), 0, 2);
        tfAnnualInterestRate = new TextField();
            tfAnnualInterestRate.setPromptText("Double");
        tfNumOfYears = new TextField();
            tfNumOfYears.setPromptText("Integer");
        tfLoanAmount = new TextField();
            tfLoanAmount.setPromptText("Double");
        topPane.add(tfAnnualInterestRate, 1, 0);
        topPane.add(tfNumOfYears, 1, 1);
        topPane.add(tfLoanAmount, 1, 2);
        btSubmit = new Button("Submit");
        topPane.add(btSubmit, 3, 1);
        return topPane;
    }
    private void initializeTextFields(ArrayList<TextField> tfList){
        // Make TextFields Double-restricted
        for (TextField tf : tfList) {
            tf.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("[0-9]+\\.?([0-9]+)?") ) // "\\d*"
                        tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }
    }
    private void setUpAndDisplayPrimaryStage(Stage primaryStage){
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 350, 200);
        primaryStage.setTitle("Loan Calculator, Client");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        VBox border = new VBox();
        border.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(border);

        GridPane topPane = getGridPane();

        ta = new TextArea();
        ta.setPromptText("Calculation from 'Server-side' goes here");
        ta.setFont(Font.font("Monospaced")); // For better spacing

        initializeTextFields(new ArrayList<TextField>(){{add(tfAnnualInterestRate); add(tfNumOfYears); add(tfLoanAmount);}});

        border.getChildren().addAll(topPane, ta);
    }
    private void connectToServer(){
        // Create a Socket to connect to the server
        try {
            Socket socket   = new Socket("localhost", 8000);
            isFromServer    = new DataInputStream(socket.getInputStream());
            osToServer      = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            appendError(ex.toString());
        }
    }
    private void contactServerForResult(double annualInterestRate, int numberOfYears, double loanAmount){
        try {
            writeToLog(String.format("Annual interest rate:     %.2f \n", annualInterestRate));
            writeToLog(String.format("Number of years:          %s   \n", numberOfYears));
            writeToLog(String.format("Loan Amount:              %.2f \n", loanAmount));

            osToServer.writeDouble(annualInterestRate);
            osToServer.flush();
            osToServer.writeInt(numberOfYears);
            osToServer.flush();
            osToServer.writeDouble(loanAmount);
            osToServer.flush();

            writeToLog(String.format("Monthly payment:          %.2f \n", isFromServer.readDouble()));
            writeToLog(String.format("Total Payment:            %.2f \n", isFromServer.readDouble()));
        } catch (IOException ex) {
            ex.printStackTrace();
            appendError(ex.toString());
        }
    }
    private void writeToLog(String string){
        Platform.runLater(() -> ta.appendText(string));
    }
    private void appendError(String string){
        writeToLog("\nError-> " + string + "\n--------------\n");
    }
    public static void main(String[] args){
        // Generate the GUI for Client
        launch(args);
    }
}