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
            //search button click
            //ige get niya yung word
            // compare niya yung value ng kapampangan, english and tagalog word na meron tayo
            // tapos kapag meron makikita niya sa baba

            FXMLLoader filipinoContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/TagalogColumn.fxml"));
            VBox filipinoTextArea = filipinoContainerLoader.load();
            TagalogColumnController filipinoController = filipinoContainerLoader.getController();
            filipinoController.changeName("Filipino");
            filipinoController.changeBackgroundColor("magenta");

            FXMLLoader kapampanganContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/TagalogColumn.fxml"));
            VBox kapampanganTextArea = kapampanganContainerLoader.load();
            TagalogColumnController kapampanganController = kapampanganContainerLoader.getController();
            kapampanganController.changeName("Kapampangan");
            kapampanganController.changeBackgroundColor("#D0386A");

            FXMLLoader englishContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/TagalogColumn.fxml"));
            VBox englishTextArea = englishContainerLoader.load();
            TagalogColumnController englishController = englishContainerLoader.getController();
            englishController.changeName("English");
            englishController.changeBackgroundColor("#FF8383");

            subContainer.getChildren().add(filipinoTextArea);
            subContainer.getChildren().add(kapampanganTextArea);
            subContainer.getChildren().add(englishTextArea);
            System.out.println("PASSED TEST 1 - MainWindowController");
        } 
        catch (IOException e) {
            e.printStackTrace(); System.out.println("FAILED TEST 1 - MainWindowController");
        }
    }
}