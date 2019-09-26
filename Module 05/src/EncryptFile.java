import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class EncryptFile extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        /* GUI */
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 130);
        primaryStage.setTitle("Encrypt File");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        @SuppressWarnings("Duplicate")
        VBox border = new VBox();
        border.setAlignment(Pos.CENTER);
        root.getChildren().add(border);

        @SuppressWarnings("Duplicate")
        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(15, 15, 15, 15));
        topBox.setAlignment(Pos.CENTER);
        TextField inputSourcePath = new TextField("File to encrypt");
            HBox.setHgrow(inputSourcePath, Priority.ALWAYS);
        TextField inputTargetPath = new TextField("Save encrypted file to");
            HBox.setHgrow(inputTargetPath, Priority.ALWAYS);
        Button encryptButton = new Button("Encrypt");
        topBox.getChildren().addAll(inputSourcePath, inputTargetPath, encryptButton);
        border.getChildren().add(topBox);

        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(10, 10, 10, 10));
        TextArea outputText = new TextArea();
        bottomBox.getChildren().add(outputText);
        border.getChildren().add(bottomBox);
        /* GUI */

        encryptButton.setOnMouseClicked(e -> {
            File sourceFile = new File(inputSourcePath.getText());
            File targetFile = new File(inputTargetPath.getText());

            if (sourceFile.isFile() && targetFile.isFile())
                outputText.appendText(encryptFile(sourceFile, targetFile) + "\n");
            else
                outputText.appendText("Either sourcePath or targetPath are invalid paths, directories not allowed! \n");
        });
    }
    public String encryptFile(File sourcePath, File targetPath) {
        try {
            // Create an input stream
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourcePath));

            // Create an output stream
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetPath));

            // Read a byte from input and write to output
            int r, bytesRead = 0;
            byte i = 5;
            while ((r = input.read()) != -1) {
                output.write(r + i);
                System.out.println(bytesRead++);
                output.flush();
            }
        } catch (Exception ex) {
            System.out.println("Error during encryption!");
        } finally {
            System.out.println("Encrypted successfully to targetPath\n----\n");
        }
        return String.format("File %s has been encrypted", sourcePath);
    }
}
