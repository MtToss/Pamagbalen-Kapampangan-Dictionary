package pamagbalen.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @FXML
    private Label labelContainer1;

    private List<String[]> wordList = new ArrayList<>();
    
    VBox mainWindow = null;
    
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
            wordContainer = searched.trim(); // to remove available spaces and stuff
            verifySearch();
            System.out.println("SUCCESFULLY PASSED THE WORD YOU SEARCHED");
            System.out.println("IS IT " + wordContainer + "?");
        }
        catch (IOError e) {
            e.printStackTrace();
        }
        return wordContainer;
    }

    private String[] searchWord(String word) {
        String[] bestMatch = null;
        int minDistance = Integer.MAX_VALUE;
    
        for (String[] entry : wordList) {
            
            if (entry[0].equalsIgnoreCase(word) || entry[1].equalsIgnoreCase(word) || entry[2].equalsIgnoreCase(word)) {
                labelContainer1.setText(null);
                return entry;
            }
            
            for (int i = 0; i < entry.length; i++) {
                int distance = wagnerFischer(word, entry[i]);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestMatch = entry;
                    
                }
            }
        }
        String tempStoreWord = bestMatch[0];
        labelContainer1.setText("Did you mean: " + tempStoreWord);
        return bestMatch; 
    }
    
    
    private int wagnerFischer(String str1, String str2) {
        int len1 = str1.length(); //dukho which is the mispelled
        int len2 = str2.length(); //dukha
        int[][] dp = new int[len1 + 1][len2 + 1]; 

        
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i; // Cost of deleting characters from str1
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j; // Cost of adding characters to str1
        }
    
        // Compute the distances
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1; // 0 if the characters are the same, 1 if different
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, // Deletion
                                              dp[i][j - 1] + 1), // Insertion
                                              dp[i - 1][j - 1] + cost); // Substitution
            }
        }
        //System.out.println("returning: " + dp[len1][len2]);
        return dp[len1][len2]; // Return the edit distance
    }

    public void verifySearch() {
        String[] foundWord = searchWord(wordContainer);
        if (foundWord != null) {    
            kapampanganWord.setText(capitalize(foundWord[0]));  
            tagalogWord.setText(capitalize(foundWord[1]));      
            englishWord.setText(capitalize(foundWord[2]));      
            definitionContainer.setText(capitalize(foundWord[3])); 
        } 
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}