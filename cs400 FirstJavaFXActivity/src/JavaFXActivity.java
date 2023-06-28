import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
// these imports are used for the First JavaFX Activity
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;


public class JavaFXActivity extends Application {
    @Override
    public void start(final Stage stage) {
        // update this method definition to complete the First JavaFX Activity
        Group group = new Group();
        Scene scene = new Scene(group,320,240);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}