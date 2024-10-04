package pamagbalen.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ContentContainerController {

    String wordContainer = null;

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
        loadCSV("src\\\\main\\\\python\\\\csv\\\\kapampangan_translations.csv");
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
        } 
        catch (IOException e) {
            e.printStackTrace(); 
            System.out.println("FAILED TEST 4 - Content Container Controller");
        }
    }

    public String getWordSearched(String searched) {
        try{
            wordContainer = searched;
            System.out.println("SUCCESFULLY PASSED THE WORD YOU SEARCHED");
            System.out.printf("IS IT ", wordContainer, "?");
        }
        catch (IOError e) {
            e.printStackTrace();
        }
        return wordContainer;
    }
}