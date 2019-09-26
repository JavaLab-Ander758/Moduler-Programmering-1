import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class TegneSirkelOgFirkant extends Application {
    boolean isChecked; // Circle=true, Square=false;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        // Create Scene
        Scene scene = new Scene(root, 800,800);
        primaryStage.setTitle("Tegn rektangel og sirkel");
        primaryStage.setScene(scene);
        primaryStage.show();


        // Left
        VBox leftVBox = getVBox();
        root.setLeft(leftVBox);
        Button redButton = new Button("Rød");
        Button blueButton = new Button("Blå");
        leftVBox.getChildren().addAll(redButton, blueButton);
        leftVBox.setAlignment(Pos.CENTER_LEFT);

        // Right
        VBox rightVBox = getVBox();
        rightVBox.setPadding(new Insets(15, 15, 15, 15));
        root.setRight(rightVBox);
        Button rotateButton = new Button("Roter");
        rightVBox.getChildren().add(rotateButton);
        rightVBox.setAlignment(Pos.CENTER_RIGHT);

        // Bottom
        HBox bottomHBox = getHBox();
        root.setBottom(bottomHBox);
        Button squareButton = new Button("Firkant");
        Button circleButton = new Button("Sirkel");
        bottomHBox.getChildren().addAll(squareButton, circleButton);
        bottomHBox.setAlignment(Pos.BOTTOM_CENTER);

        // Top
        VBox topVBox = getVBox();
        root.setTop(topVBox);
        Text squareText = new Text("404_square not found");
        Text circleText = new Text("404_circle not found");
        topVBox.getChildren().addAll(squareText, circleText);
        topVBox.setAlignment(Pos.TOP_CENTER);

        // Center
        BorderPane center = new BorderPane();
        root.setCenter(center);
        center.setStyle("-fx-border-style: solid; -fx-border-width: 1 1 1 1");


        /* Button actions */
        Circle circle = new Circle(20, Color.BLACK);
        circleButton.setOnAction(e -> {
            root.getChildren().remove(circle);
            root.getChildren().add(circle);
            isChecked = true;
            double xMin = leftVBox.getWidth() + circle.getRadius(), xMax = scene.getWidth() - leftVBox.getWidth() - circle.getRadius();
            circle.setCenterX(getRandomInRange(xMin, xMax) );
            double yMin = topVBox.getHeight() + circle.getRadius(), yMax = scene.getHeight() - bottomHBox.getHeight() - circle.getRadius();
            circle.setCenterY(getRandomInRange(yMin, yMax) );
            circleText.setText(String.format("Circle: x_Pos: %.2f, y_Pos: %.2f", circle.getCenterX() - circle.getRadius(), circle.getCenterY() - circle.getRadius() ));
        });

        Rectangle rectangle = new Rectangle(40, 40, Color.BLACK);
        squareButton.setOnAction(e -> {
            root.getChildren().remove(rectangle);
            root.getChildren().add(rectangle);
            isChecked = false;
            rectangle.setX(getRandomInRange(leftVBox.getWidth(), scene.getWidth() - leftVBox.getWidth() - rectangle.getWidth() ));
            rectangle.setY(getRandomInRange(topVBox.getHeight(), scene.getHeight() - bottomHBox.getHeight() - rectangle.getHeight() ));
            squareText.setText(String.format("Square: x_Pos: %.2f, y_Pos: %.2f", rectangle.getX(), rectangle.getY() ));
        });
        redButton.setOnAction(e -> {
            if (isChecked)
                circle.setFill(Color.RED);
            else
                rectangle.setFill(Color.RED);
        });
        blueButton.setOnAction(e -> {
            if (isChecked)
                circle.setFill(Color.BLUE);
            else
                rectangle.setFill(Color.BLUE);
        });
        rotateButton.setOnAction(e -> {
            rectangle.setRotate(rectangle.getRotate() + 45);
        });
        circle.setOnMouseClicked(e -> {
            isChecked = true;
        });
        rectangle.setOnMouseClicked(e -> {
            isChecked = false;

        });
    }
    private HBox getHBox(){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 15, 15, 15));
        return hBox;
    }
    private VBox getVBox(){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 15, 15, 15));
        return vBox;
    }

    public double getRandomInRange(double xMin, double xMax){
        Random random = new Random();
        return xMin + (xMax - xMin) * random.nextDouble();
    }
}