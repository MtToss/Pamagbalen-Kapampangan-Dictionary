package pamagbalen.application.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/pamagbalen/MainWindow.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setWidth(1500);
            primaryStage.setHeight(850);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kapampangan Translator");
            primaryStage.show();

        } 
        catch (IOException e) {
            System.out.println("Window Error: " + e.getMessage()); 
            System.out.println("FAILED TEST 2 - APP");
        }
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
    public static void main(String[] args) {
        launch(args);
    }
}