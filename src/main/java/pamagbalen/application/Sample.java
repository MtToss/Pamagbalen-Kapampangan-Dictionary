package pamagbalen.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Sample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pamagbalen/TagalogColumn.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setWidth(1500);
            primaryStage.setHeight(1000);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kapampangan Translator");
            System.out.println("PASSED TEST 1");
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("Window Error: " + e.getMessage()); e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
