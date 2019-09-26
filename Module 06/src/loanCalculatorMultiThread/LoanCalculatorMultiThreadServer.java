package loanCalculatorMultiThread;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import loanCalculator.Loan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class LoanCalculatorMultiThreadServer extends Application {
    private TextArea ta;
    private int clientNo = 0;
    private ArrayList<Loan> loanList;
    private int loanCount = 0;

    @Override
    public void start(Stage primaryStage) {
        loanList = new ArrayList<>();
        setUpAndDisplayPrimaryStage(primaryStage);

        new Thread(() -> {
            try {
                // Create a Server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                writeToLog(String.format("%s started at %s \n", getClass().getSimpleName(), new Date()));

                while (true) {
                    // Listen for a new connection request
                    Socket socket = serverSocket.accept();
                    clientNo++;

                    // Append Client connection status
                    appendClientConnection(socket);

                    // Create and start a new Thread for the connection
                    new Thread(new HandleAClient(socket, clientNo)).start();
                }
            } catch (IOException ex) {
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
    private void writeToLog(String string) {
        Platform.runLater(() -> ta.appendText(string));
    }
    private void appendObject(Loan loan, int clientNumber) {
        writeToLog("--------------------\n");
        writeToLog(String.format("  <-> Client: %s <-> \n", clientNumber));
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
    private void appendClientConnection(Socket socket) {
        // Display the Client number
        writeToLog(String.format("\nStarting Thread for Client %s at %s \n", clientNo, new Date()));

        // Find Client 'host name' and 'IP address'
        InetAddress inetAddress =  socket.getInetAddress();
        writeToLog(String.format("Client %s's host name is %s \n", clientNo, inetAddress.getHostName()));
        writeToLog(String.format("Client %s's IP Address is %s \n\n", clientNo, inetAddress.getHostName()));
    }

    // Define the Thread class for handling new connection
    class HandleAClient implements Runnable{
        private Socket socket;
        private int clientNumber;

        private HandleAClient(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
        }

        @Override
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                // Continuously serve the Client
                while (true) {
                    Loan loan = new Loan();

                    // Read variables from Client
                    loan.setAnnualInterestRate(inputFromClient.readDouble());
                    loan.setNumberOfYears(inputFromClient.readInt());
                    loan.setLoanAmount(inputFromClient.readDouble());

                    // Send calculated result back to client
                    outputToClient.writeDouble(loan.getMonthlyPayment());
                    outputToClient.writeDouble(loan.getTotalPayment());

                    addLoanToLoanList(loan);

                    appendObject(loan, clientNumber);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        // Generate the GUI for Server
        launch(args);
    }
}
