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


public class TowerOfHanoi extends Application {
    private String toBeReturned = "";
    private int moveCount;

    public void start(Stage primaryStage) throws Exception {

        //<editor-fold desc="GUI">
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 500, 530);
        primaryStage.setTitle("Tower of Hanoi");
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setResizable(false);

        VBox border = new VBox();
        border.setAlignment(Pos.CENTER);
        root.getChildren().add(border);

        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(15, 15, 15, 15));
        topBox.setAlignment(Pos.CENTER);
        Button findMovesButton = new Button("Find moves");
        HBox.setHgrow(findMovesButton, Priority.ALWAYS);
        TextField inputDisks = new TextField();
        inputDisks.setPromptText("Number of disks");
        HBox.setHgrow(inputDisks, Priority.ALWAYS);
        topBox.getChildren().addAll(inputDisks, findMovesButton);
        border.getChildren().add(topBox);

        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(10, 10, 10, 10));
        TextArea outputText = new TextArea();
        VBox.setVgrow(outputText, Priority.ALWAYS);
        outputText.setPrefHeight(scene.getHeight());
        bottomBox.getChildren().add(outputText);
        border.getChildren().add(bottomBox);
        //</editor-fold>

        //<editor-fold desc="Button">
        findMovesButton.setOnMouseClicked(e -> {
            toBeReturned = "";
            moveCount = 0;

            if (inputDisks.getText().matches(("-?\\d+"))) {
                if (Integer.valueOf(inputDisks.getText()) > 0) {
                    outputText.appendText("Moves are:");
                    moveDisks(Integer.valueOf(inputDisks.getText()), 'A', 'C', 'B');
                    String test = String.format("%s \n\nNumber of calls to the methods is: %s \n\n", toBeReturned, moveCount);
                    outputText.appendText(test);
                } else
                    outputText.appendText("Gi inn positivt heltall \n\n");
            } else
                outputText.appendText("Kun heltall er gyldig \n\n");
        });
        //</editor-fold>
    }

    private String incrementInt(int integer){
        moveCount++;
        integer = moveCount;
        return Integer.toString(integer);
    }
    private void moveDisks(int n, char fromTower, char toTower, char auxTower) {
        if (n == 1) { // Stopping condition
            toBeReturned += String.format("\n  Move number: %s \t move disk %s from %s to %s", incrementInt(moveCount), n, fromTower, toTower);
        } else {
            moveDisks(n - 1, fromTower, auxTower, toTower);
            toBeReturned += String.format("\n  Move number: %s \t move disk %s from %s to %s", incrementInt(moveCount), n, fromTower, toTower);
            moveDisks(n - 1, auxTower, toTower, fromTower);
        }
    }
}