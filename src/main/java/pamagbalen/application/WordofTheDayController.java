package pamagbalen.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordofTheDayController {

    @FXML
    private VBox vBoxContainer;

    @FXML
    private TextArea definitionContainer;

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
                
                String[] words = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); 
                
                for (int i = 0; i < words.length; i++) {
                    words[i] = words[i].replace("\"", "").trim(); 
                }
    
                wordList.add(words);
            }
            System.out.println("PASSED TEST 3 - Word of The Day Controller");
        } catch (IOException e) {
            e.printStackTrace(); 
            System.out.println("FAILED TEST 3 - Word of The Day Controller");
        }
    }

    public void setWordOfTheDay() {
        LocalDate today = LocalDate.now();
        File recordFile = new File("src\\main\\python\\csv\\word_of_the_day.csv");

        if (recordFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(recordFile))) {
                String line;
                if ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String storedDate = data[0];
                    int storedIndex = Integer.parseInt(data[1]);

                    if (storedDate.equals(today.toString())) {
                        setRandomKapampanganWord(storedIndex);
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Random random = new Random();
        int randomIndex = random.nextInt(wordList.size());
        setRandomKapampanganWord(randomIndex);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(recordFile))) {
            bw.write(today.toString() + "," + randomIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setRandomKapampanganWord(int index) {
        if (!wordList.isEmpty()){
            String[] selectedWord = wordList.get(index);

            kapampanganWord.setText(capitalize(selectedWord[0])); 
            tagalogWord.setText(capitalize(selectedWord[1])); 
            englishWord.setText(capitalize(selectedWord[2]));
            //definitionContainer.setText("is a type of flowering plant belonging to the family Poaceae, characterized by narrow leaves and hollow stems, commonly found in various habitats, and plays an essential role in ecosystems as a ground cover, forage for animals, and soil stabilization");
            definitionContainer.setText(capitalize(selectedWord[3]));
            
        }
        
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
