package pamagbalen.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ListController {

    @FXML
    private HBox alphabetContainer;

    private ListContentContainerController listContentController; 
    private List<String[]> wordList = new ArrayList<>();
    private MainWindowController mainWindowController;

    public void initialize() {
        createAlphabetButtons();
        initializeListContentController();
    }

    private void initializeListContentController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pamagbalen/ListContentContainer.fxml"));
            Parent content = loader.load();

            listContentController = loader.getController();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAlphabetButtons() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            char currentLetter = letter;

            Button letterButton = new Button(String.valueOf(currentLetter));
            letterButton.setOnAction(event -> onLetterClicked(currentLetter));
            letterButton.setStyle("-fx-font-size: 16px;");

            HBox.setMargin(letterButton, new Insets(10, 5, 10, 5));
            alphabetContainer.getChildren().add(letterButton);
        }
    }

    private void onLetterClicked(char letter) {
        if (listContentController != null) {
            mainWindowController.loadListContentContainer(letter);
        } else {
            System.out.println("ListContentContainerController not initialized.");
        }
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
