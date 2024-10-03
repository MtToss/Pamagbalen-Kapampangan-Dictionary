package pamagbalen.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordofTheDayController {

    @FXML
    private VBox vBoxContainer;

    @FXML
    private Label definitionContainer;

    @FXML
    private Label kapampanganWord;

    @FXML
    private Label tagalogWord;

    @FXML
    private Label englishWord;

    private List<String[]> wordList = new ArrayList<>();

    public void initialize() {
        loadCSV("src\\main\\python\\csv\\kapampangan_translations.csv");
    }

    private void loadCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Kapampangan")) {
                    continue;
                }
                String[] words = line.split(",");
            
                wordList.add(words);
            }
            System.out.println("PASSED TEST 3 - Word of The Day Controller");
        } catch (IOException e) {
            e.printStackTrace(); System.out.println("FAILED TEST 3 - Word of The Day Controller");
        }
    }

    public void setRandomKapampanganWord() {
        if (!wordList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(wordList.size());
            String[] selectedWord = wordList.get(130);

            kapampanganWord.setText(selectedWord[0]); 
            tagalogWord.setText(selectedWord[1]); 
            englishWord.setText(selectedWord[2]);
            //definitionContainer.setText("is a type of flowering plant belonging to the family Poaceae, characterized by narrow leaves and hollow stems, commonly found in various habitats, and plays an essential role in ecosystems as a ground cover, forage for animals, and soil stabilization");
            
             if (selectedWord.length > 3) {
                definitionContainer.setText(selectedWord[3]);
            } 
            else {
                definitionContainer.setText("No definition available");
            } 
            
        }
    }
}
