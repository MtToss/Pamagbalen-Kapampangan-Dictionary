package pamagbalen.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    VBox wordofTheDayContainer= null;
    VBox contentContainer = null;

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

            FXMLLoader wordofTheDayLoader = new FXMLLoader(getClass().getResource("/pamagbalen/WordofTheDay.fxml"));
            wordofTheDayContainer = wordofTheDayLoader.load();
            WordofTheDayController wordofTheDayController = wordofTheDayLoader.getController();
            wordofTheDayController.setWordOfTheDay();

            subContainer.getChildren().add(wordofTheDayContainer);

            System.out.println("PASSED TEST 1 - MainWindowController");
        } 
        catch (IOException e) {
            e.printStackTrace(); System.out.println("FAILED TEST 1 - Main Window Controller");
        }
    }

    @FXML
    private void buttonClicked() {
        String wordContainer = searchTextField.getText();
        subContainer.getChildren().remove(wordofTheDayContainer);

        
        try {
            FXMLLoader contentContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/ContentContainer.fxml"));
            contentContainer = contentContainerLoader.load();
            ContentContainerController contentContainerController = contentContainerLoader.getController();

            contentContainerController.getWordSearched(wordContainer);
            subContainer.getChildren().add(contentContainer);
            subContainer.getChildren().add(wordofTheDayContainer);

            HBox.setMargin(contentContainer, new Insets(0,10,0,5));
            HBox.setMargin(wordofTheDayContainer, new Insets(0,5,0,10));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}