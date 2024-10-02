package pamagbalen.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pamagbalen/MainWindow.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setWidth(1500);
            primaryStage.setHeight(1000);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kapampangan Translator");
            System.out.println("PASSED TEST 2 - APP");
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("Window Error: " + e.getMessage()); e.printStackTrace();
            System.out.println("FAILED TEST 2 - APP");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}