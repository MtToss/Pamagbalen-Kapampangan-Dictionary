package pamagbalen.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pamagbalen.application.tool.ListAbstract;

public class ListContentContainerController extends ListAbstract {
    
 
    private final List<String[]> wordList = new ArrayList<>();
    char alphabetContainer;
    VBox contentContainer = null;

    @FXML
    VBox subContainer;

    @FXML
    VBox indexContainer;

    @FXML
    Label labelAlphabet;
    
    @FXML
    Label indexLabel;

    @FXML
    ScrollPane listPane;

    @FXML
    public void initialize() {
        indexContainer.getChildren().clear();
        loadCSV("src/main/python/csv/kapampangan_translations.csv");
        filterWordListByLetter(alphabetContainer);

    }

    private void loadCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Kapampangan")) {
                    continue;
                }
                String[] words = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int i = 0; i < words.length; i++) {
                    words[i] = words[i].replace("\"", "").trim();
                }
                wordList.add(words);
            }
        } 
        catch (IOException e) {
            System.out.println("Load CSV Error: " + e.getMessage());  
        }
    }

    private void filterWordListByLetter(char letter) {
        List<String[]> filteredList = new ArrayList<>();

        for (String[] words : wordList) {
            if (words[0].toLowerCase().startsWith(String.valueOf(letter))) {
                filteredList.add(words);
            }
        }
        displayWords(filteredList);
    }

    public void displayWords(List<String[]> filteredList) {
        
        indexContainer.getChildren().clear();
        labelAlphabet.setText(String.valueOf(alphabetContainer).toUpperCase());

        animateLabel(indexLabel);
        animateLabel(labelAlphabet);
        animatelistContainer(listPane);
        

        for (String[] words : filteredList) {
            Label wordLabel = new Label(String.valueOf(capitalize(words[0]))); 
            wordLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Sitka Small'; -fx-font-size: 25; -fx-background-color: dimgrey; -fx-background-radius: 5; -fx-border-radius: 5;");
            
            wordLabel.setOnMouseEntered(e -> {
                wordLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Sitka Small'; -fx-font-size: 25; -fx-background-color: #003049; -fx-background-radius: 10; -fx-border-radius: 10;");
            });
            
            wordLabel.setOnMouseExited(e -> {
                wordLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Sitka Small'; -fx-font-size: 25; -fx-background-color: dimgrey; -fx-background-radius: 5; -fx-border-radius: 5;");
            });
        
            wordLabel.setOnMouseClicked(event -> {
                onLabelClick(words);
            });
        
            indexContainer.getChildren().add(wordLabel);
        }
        
    }

    public void setAlphabet(char alphabet) {
        alphabetContainer = alphabet;
        filterWordListByLetter(alphabetContainer);   
    }

    public void onLabelClick(String[] wordData) {
        String wordContainer = wordData[0];
        try {
            if (contentContainer == null) {
                FXMLLoader contentContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/ContentContainer.fxml"));
                contentContainer = contentContainerLoader.load();
                ContentContainerController contentContainerController = contentContainerLoader.getController();
                
                animateContentContainer(contentContainer);
                contentContainerController.getWordSearched(wordContainer);
                
                contentContainer.getProperties().put("fxController", contentContainerController); // we store the controller to the content container for future purposes
            } 
            else {
                ContentContainerController contentContainerController = (ContentContainerController) contentContainer.getProperties().get("fxController"); // this is what the future purposes means like, we get the controller. Since it is initialized 
                animateContentContainerFade(contentContainer);

                PauseTransition pause = new PauseTransition(Duration.seconds(0.7));
                pause.setOnFinished(event -> {
                    animateContentContainer(contentContainer);
                    contentContainerController.getWordSearched(wordContainer);
                });
                pause.play();
            }

            if (!subContainer.getChildren().contains(contentContainer)) {
                subContainer.getChildren().add(contentContainer);
            }

            HBox.setMargin(contentContainer, new Insets(0, 5, 0, 5));
            
        } 
        catch (IOException e) {
            System.out.println("On Label Click Error: " + e.getMessage());
        }
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
    
}
