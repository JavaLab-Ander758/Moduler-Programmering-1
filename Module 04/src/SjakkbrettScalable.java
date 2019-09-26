import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class SjakkbrettScalable extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage){
        final int SIZE = 8, BOARD_SIZE = 800;
        double width = BOARD_SIZE, height = BOARD_SIZE;
        double tempX = 0, tempY = 0;

        // Create a scene and place it in the stage
        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);
        primaryStage.setTitle("Sjakkbrett");
        primaryStage.setScene(scene);

        for (int i = 0, count = 1; i < SIZE; i++, count++) {
            if (i != 0) {
                tempY += scene.getHeight() / SIZE;
                tempX = 0;
            }
            for (int j = 0; j < SIZE; j++, count++) {
                Rectangle rectangle = new Rectangle(tempX, tempY, scene.getWidth() / SIZE, scene.getHeight() / SIZE);
                if (count % 2 == 0)
                    rectangle.setFill(Color.BLACK);
                else
                    rectangle.setFill(Color.WHITE);

                rectangle.heightProperty().bind(pane.heightProperty().divide(SIZE));
                rectangle.widthProperty().bind(pane.widthProperty().divide(SIZE));
                rectangle.yProperty().bind(pane.heightProperty().divide(SIZE).multiply(j));
                rectangle.xProperty().bind(pane.widthProperty().divide(SIZE).multiply(i));

                pane.getChildren().add(rectangle);
                tempX += scene.getWidth() / SIZE;
            }
        }
        primaryStage.show(); // Display the stage
    }
    /**
     * The main method is only needed for the IDE with limited JavaFX support. Not needed running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}