package pamagbalen.application;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ListController {

    @FXML
    private HBox alphabetContainer;

    public void initialize() {
        createAlphabetButtons();
    }

    private void createAlphabetButtons() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            char currentLetter = letter; 
    
            Button letterButton = new Button(String.valueOf(currentLetter));
            letterButton.setOnAction(event -> onLetterClicked(currentLetter));
            letterButton.setStyle("-fx-font-size: 16px; -fx-padding: 10;");
    
            alphabetContainer.getChildren().add(letterButton);
        }
    }

    private void onLetterClicked(char letter) {
        System.out.println("Letter clicked: " + letter);
        filterWordListByLetter(letter);
    }

    private void filterWordListByLetter(char letter) {

        System.out.println("Filtering words starting with: " + letter);
    }

    public Map<Object, Object> getProperties() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProperties'");
    }
}
