package pamagbalen.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ContentContainerController {

    private String wordContainer = null;

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

    private Map<String, String[]> wordMap = new HashMap<>();

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

                wordMap.put(words[0].toLowerCase(), words); 
                wordMap.put(words[1].toLowerCase(), words); 
                wordMap.put(words[2].toLowerCase(), words); 
            }
            System.out.println("PASSED TEST 4 - Content Container Controller");
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("FAILED TEST 4 - Content Container Controller");
        }
    }

    public String getWordSearched(String searched) {
        try {
            wordContainer = searched.trim().toLowerCase(); 
            verifySearch();
            System.out.println("SUCCESSFULLY PASSED THE WORD YOU SEARCHED");
            System.out.println("IS IT " + wordContainer + "?");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return wordContainer;
    } 

    private String[] searchWord(String word) {
       
        if (wordMap.containsKey(word)) {
            labelContainer1.setText(null);
            return wordMap.get(word); 
        } 
        else {
            return findClosestMatch(word);
        }
    }

    private String[] findClosestMatch(String word) {
        String[] bestMatch = null;
        int minDistance = Integer.MAX_VALUE;

        for (Map.Entry<String, String[]> entry : wordMap.entrySet()) {
            String key = entry.getKey();
            int distance = wagnerFischer(word, key);

            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = entry.getValue();
            }
        }

        if (bestMatch != null) {
            labelContainer1.setText("Did you mean: " + bestMatch[0]);
        }
        return bestMatch;
    }

    private int wagnerFischer(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
        return dp[len1][len2];
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
