package pamagbalen.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private AnchorPane mainContainer; 

    @FXML
    private HBox subContainer;

    @FXML
    public void initialize() {
        loadTagalogTextArea();
    }

    private void loadTagalogTextArea() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pamagbalen/TagalogColumn.fxml"));
            VBox tagalogTextArea = loader.load();
            
            subContainer.getChildren().add(tagalogTextArea);
            System.out.println("PASSED TEST 1 - MainWindowController");
        } 
        catch (IOException e) {
            e.printStackTrace(); System.out.println("FAILED TEST 1 - MainWindowController");
        }
    }
}