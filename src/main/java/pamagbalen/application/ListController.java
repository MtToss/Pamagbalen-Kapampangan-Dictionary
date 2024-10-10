package pamagbalen.application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ListController {

    @FXML
    private HBox alphabetContainer;

    private ListContentContainerController listContentController; 
    private MainWindowController mainWindowController;

    public void initialize() {
        createAlphabetButtons();
        initializeListContentController();
    }

    private void initializeListContentController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pamagbalen/ListContentContainer.fxml"));
            @SuppressWarnings("unused")
            Parent content = loader.load();

            listContentController = loader.getController();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAlphabetButtons() {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            char currentLetter = letter;

            Button letterButton = new Button(String.valueOf(currentLetter).toUpperCase());
            letterButton.setOnAction(event -> onLetterClicked(currentLetter));
            letterButton.setStyle("-fx-font-size: 16px; -fx-background-color: #e5b6b6;");

            letterButton.setOnMouseEntered(e -> {
                letterButton.setStyle("-fx-font-size: 16px; -fx-background-color: #eb8686;");
            });
            
            letterButton.setOnMouseExited(e -> {
                letterButton.setStyle("-fx-font-size: 16px;  -fx-background-color: #e5b6b6;"); 
            });

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

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
