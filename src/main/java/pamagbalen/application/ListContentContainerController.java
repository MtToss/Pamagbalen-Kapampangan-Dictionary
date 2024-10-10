package pamagbalen.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ListContentContainerController {
    

    Label wordContainer = null;
    private List<String[]> wordList = new ArrayList<>();
    char alphabetContainer;
    VBox contentContainer = null;

    @FXML
    VBox indexContainer;

    @FXML
    Label labelAlphabet;

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
            System.out.println("PASSED TEST 4 - Content Container Controller");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FAILED TEST 4 - Content Container Controller");
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

        for (String[] words : filteredList) {
            System.out.println(words[0]);
            Label wordLabel = new Label(String.valueOf(words[0])); 
            wordLabel.setOnMouseClicked(event -> {
                onLabelClick(words); 
            });
            indexContainer.getChildren().add(wordLabel);
            
        }
    }

    public void setAlphabet(char alphabet) {
        alphabetContainer = alphabet;
        filterWordListByLetter(alphabetContainer);  
        System.out.println("Alphabet set to: " + alphabetContainer);  
    }

    public void onLabelClick(String[] wordData) {

        FXMLLoader contentContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/ContentContainer.fxml"));
        try {
            contentContainer = contentContainerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentContainerController contentContainerController = contentContainerLoader.getController();

        contentContainerController.getWordData(wordData);
        
    

        Label detailsLabel = new Label("Kapampangan: " + wordData[0] + "\nTagalog: " + wordData[1] + "\nEnglish: " + wordData[2]);
        indexContainer.getChildren().add(detailsLabel); 
    }
    
}
