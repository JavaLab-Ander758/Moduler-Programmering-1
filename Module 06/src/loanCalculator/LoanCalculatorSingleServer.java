package loanCalculator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class LoanCalculatorSingleServer extends Application {
    private TextArea ta;
    private DataInputStream inputFromClient;
    private DataOutputStream outputToClient;
    private ArrayList<Loan> loanList;
    private int loanCount = 0;

    @Override
    public void start(Stage primaryStage) {
        loanList = new ArrayList<>();

        setUpAndDisplayPrimaryStage(primaryStage);

        new Thread( () -> {
            try {
                connectToClient();
                while (true) {
                    Loan loan = new Loan();
                    // Read variables from Client
                    loan.setAnnualInterestRate(inputFromClient.readDouble());
                    loan.setNumberOfYears(inputFromClient.readInt());
                    loan.setLoanAmount(inputFromClient.readDouble());

                    // Send calculated results back to Client
                    outputToClient.writeDouble(loan.getMonthlyPayment());
                    outputToClient.writeDouble(loan.getTotalPayment());

                    addLoanToLoanList(loan);

                    appendObject(loanList.get(loanCount));
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void setUpAndDisplayPrimaryStage(Stage primaryStage){
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 450, 200);
        primaryStage.setTitle("Loan Calculator, Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        ta = new TextArea();
        root.getChildren().add(ta);
    }
    private void connectToClient() {
        // Create a Socket to connect to the server
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            writeToLog(String.format("Server started at %s \n", new Date()));

            Socket socket = serverSocket.accept();
            writeToLog(String.format("Connected to client at %s \n", new Date()));

            inputFromClient = new DataInputStream(socket.getInputStream());
            outputToClient = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void writeToLog(String string) {
        Platform.runLater(() -> ta.appendText(string));
    }
    private void appendObject(Loan loan) {
        writeToLog("--------------------\n");
        writeToLog(String.format("Annual interest rate:     %.2f \n", loan.getAnnualInterestRate()));
        writeToLog(String.format("Number of years:          %s   \n", loan.getNumberOfYears()));
        writeToLog(String.format("Loan Amount:              %.2f \n", loan.getLoanAmount()));
        writeToLog(String.format("Monthly payment:          %.2f \n", loan.getMonthlyPayment()));
        writeToLog(String.format("Total payment:            %.2f \n", loan.getTotalPayment()));
        writeToLog("--------------------\n");
    }
    private void addLoanToLoanList(Loan loan) {
        try {
            loanList.add(loan);
            if (loanCount != 0)
                loanCount++;
        } catch (Exception ex) {
            writeToLog("Failed appending Loan to loanList");
        }
    }

    public static void main(String[] args){
        // Generate the GUI for Server
        launch(args);
    }
}
