import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pendulum extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        PendulumPane pane = new PendulumPane();
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Pendulum");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            pane.next();
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        pane.setOnMouseClicked(e -> {
           if (animation.getStatus() == Animation.Status.PAUSED)
               animation.play();
           else
               animation.pause();
        });
        pane.requestFocus();
        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP)
                animation.setRate(animation.getRate() * 2);
            if (e.getCode() == KeyCode.DOWN)
                animation.setRate(animation.getRate() / 2);
        });
    }
}

class PendulumPane extends Pane {
    private final double WIDTH = 300, HEIGHT = 200, PENDULUM_RADIUS = WIDTH / 2;
    private double xCeil = WIDTH / 2, yCeil = HEIGHT / 8;
    private int angle = 120;
    private boolean direction = true; // 1-R 0-L

    private Line line = new Line(xCeil, yCeil, 0, 0);
    private Circle c1 = new Circle( xCeil, yCeil, 5);
    private Circle c2 = new Circle(10);


    public PendulumPane(){
        getChildren().addAll(line, c1, c2);
        next();
    }

    public void next(){
        if (angle == 120)
            direction = true;
        else if (angle == 60)
            direction = false;

        if (direction)
            angle--;
        else
            angle++;

        double xBall = xCeil + PENDULUM_RADIUS * Math.cos(Math.toRadians(angle));
        double yBall = yCeil + PENDULUM_RADIUS * Math.sin(Math.toRadians(angle));

        line.setEndX(xBall);
        line.setEndY(yBall);
        c2.setCenterX(xBall);
        c2.setCenterY(yBall);

        System.out.printf("angle: %s \n", angle);
    }
}