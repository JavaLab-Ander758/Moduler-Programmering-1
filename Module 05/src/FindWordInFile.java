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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindWordInFile extends Application {
    private int fileCount = 0, dirCount = 0, successCount = 0;
    private String toFind;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /* GUI */
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000,700);
        primaryStage.setTitle("Find word in files");
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setResizable(false);

        VBox border = new VBox(15);
        border.setAlignment(Pos.CENTER);
        root.getChildren().add(border);

        HBox topBox = new HBox(15);
        topBox.setPadding(new Insets(15, 15, 5, 15));
        topBox.setAlignment(Pos.CENTER);
        TextField inputFilePath = new TextField("Directory or filename");
            HBox.setHgrow(inputFilePath, Priority.ALWAYS);
        TextField inputToFind = new TextField("Word");
            HBox.setHgrow(inputToFind, Priority.ALWAYS);
        Button srcButton = new Button("Search");
            HBox.setHgrow(srcButton, Priority.ALWAYS);
        topBox.getChildren().addAll(inputFilePath, inputToFind, srcButton);

        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(10, 10, 10, 10));
        TextArea outputText = new TextArea();
            VBox.setVgrow(outputText, Priority.ALWAYS);
            outputText.setPrefHeight(scene.getHeight());
        bottomBox.getChildren().add(outputText);
        border.getChildren().addAll(topBox, bottomBox);
        /* GUI */

        srcButton.setOnMouseClicked(e -> {
            File file = new File(inputFilePath.getText());
            if (!file.exists()) {
                outputText.appendText("Directory/File not found, check your syntax! \n\n");
                return;
            }

            // exists
            toFind = inputToFind.getText();
            outputText.appendText("Search start. \n----------------------------------------\n");
            fileCount = 0; dirCount = 0; successCount = 0;

            if (file.isFile()) {
                outputText.appendText(searchInFile(file));
            } else if (file.isDirectory()) {
                outputText.appendText(searchInDir(file));
                dirCount++;
            }
            outputText.appendText(String.format("----------------------------------------\nSearch end. \nSearched: %s-directories and %s-files, found %s-occurrences of '%s' \n\n\n", dirCount, fileCount, successCount, toFind));
        });
    }

    public String searchInDir(File dir) {
        String toBeReturned = "";

        File[] fileArr = dir.listFiles();
        for (File i : fileArr) {
            if (i.isDirectory()) {
                toBeReturned += searchInDir(i);
                dirCount++;
            } else if (i.isFile()) {
                toBeReturned += searchInFile(i);
            }
        }
        return toBeReturned;
    }

    public String searchInFile(File file) {
        String toBeReturned = "";

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);

            String buffer = "";
            while (fileScanner.hasNextLine()) {
                buffer = fileScanner.nextLine();

                if (buffer.contains(toFind)) {
                    successCount++;
                    toBeReturned += String.format("%s:      %s\n", file.getPath(), buffer);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found int method searchInFile");
        } finally {
            fileScanner.close();
        }

        fileCount++;
        return toBeReturned;
    }
}