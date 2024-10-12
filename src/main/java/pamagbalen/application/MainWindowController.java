package pamagbalen.application;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private AnchorPane mainContainer; 

    @FXML
    private HBox subContainer;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane bottomPaneContainer;

    @FXML
    private VBox vBoxContainer;

    @FXML
    private Pane browsePane;

    @FXML
    private Pane homePane;

    @FXML
    private Label browseLabel, homeLabel;

    char alphabetContainer = 'b';
    VBox wordofTheDayContainer = null;
    VBox contentContainer = null;
    AnchorPane browseContainer = null;
    AnchorPane listContentContainer = null;
    @FXML
    public void initialize() {
        setYAnimation(browsePane);
        setYAnimation(homePane);
        loadArea();
        
    }

    private void loadArea() {
        try {
            FXMLLoader wordofTheDayLoader = new FXMLLoader(getClass().getResource("/pamagbalen/WordofTheDay.fxml"));
            wordofTheDayContainer = wordofTheDayLoader.load();
            WordofTheDayController wordofTheDayController = wordofTheDayLoader.getController();
            wordofTheDayController.setWordOfTheDay();

            subContainer.getChildren().add(wordofTheDayContainer);
            HBox.setMargin(wordofTheDayContainer, new Insets(50, 50, 0, 50));

            System.out.println("PASSED TEST 1 - MainWindowController");
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("FAILED TEST 1 - MainWindowController");
        }
    }

    public void setYAnimation(Pane pane) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pane);
        transition.setFromY(-300);   
        transition.play();
    }

    @FXML
    public void onBrowsePaneShow() {
        animatePane(browsePane, true); 
        browseLabel.setTextFill(Color.web("#ff5c5c"));
    }

    @FXML
    public void onBrowsePaneHide() {
        animatePane(browsePane, false); 
        browseLabel.setTextFill(Color.BLACK);
    }

    @FXML
    public void onHomePaneShow() {
        animatePane(homePane, true);
        homeLabel.setTextFill(Color.web("#ff5c5c"));
    }

    @FXML
    public void onHomePaneHide() {
        animatePane(homePane, false);
        homeLabel.setTextFill(Color.BLACK);
    }

    private void animatePane(Pane pane, boolean show) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pane);
        
        if (show) {
            transition.setFromY(-300); 
            transition.setToY(0); 
        } else {
            transition.setFromY(0); 
            transition.setToY(-300); 
        }
        
        transition.play();
    }

    @FXML
    private void buttonClicked() {
        String wordContainer = searchTextField.getText();

        if (subContainer.getChildren().contains(wordofTheDayContainer)){
            subContainer.getChildren().remove(wordofTheDayContainer);
        }

        try {
            if (contentContainer == null) {
                FXMLLoader contentContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/ContentContainer.fxml"));
                contentContainer = contentContainerLoader.load();
                ContentContainerController contentContainerController = contentContainerLoader.getController();

                contentContainerController.getWordSearched(wordContainer);

                contentContainer.getProperties().put("fxController", contentContainerController); // we store the controller to the content container for future purposes
            } 
            else {
                ContentContainerController contentContainerController = (ContentContainerController) contentContainer.getProperties().get("fxController"); // this is what the future purposes means like, we get the controller. Since it is initialized 
                                                                                                                                                            // and we need to update it for another search, we can retrieve to controller and update the word.
                contentContainerController.getWordSearched(wordContainer);
                
            }

            if (!subContainer.getChildren().contains(contentContainer)) {
                subContainer.getChildren().add(contentContainer);
            }

            if(subContainer.getChildren().contains(listContentContainer)) {
                subContainer.getChildren().removeAll(listContentContainer);
            }

            if(bottomPaneContainer.getChildren().removeAll(browseContainer)) {
                bottomPaneContainer.getChildren().removeAll(browseContainer);
            }

            HBox.setMargin(contentContainer, new Insets(0, 5, 0, 5));
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void labelHomeClicked() {
        if(browseContainer != null) {
            if(bottomPaneContainer.getChildren().contains(browseContainer)) {
                bottomPaneContainer.getChildren().remove(browseContainer);
            }

            if(subContainer.getChildren().contains(listContentContainer)) {
                subContainer.getChildren().remove(listContentContainer);
            }
        }

        if(subContainer.getChildren().contains(contentContainer)) {
            subContainer.getChildren().remove(contentContainer);
        }

        if(!subContainer.getChildren().contains(wordofTheDayContainer)) {
            subContainer.getChildren().add(wordofTheDayContainer);
        }
    }

    @FXML
    public void labelBrowseClicked() {
        try {
            if(browseContainer == null) {
                FXMLLoader browseContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/Selector.fxml"));
                browseContainer = browseContainerLoader.load();

                ListController browseController = browseContainerLoader.getController();
                browseController.setMainWindowController(this); 
                
            }

            if(!bottomPaneContainer.getChildren().contains(browseContainer)) {
                bottomPaneContainer.getChildren().add(browseContainer);
                
                AnchorPane.setTopAnchor(browseContainer, 0.0);
                AnchorPane.setBottomAnchor(browseContainer, 0.0);
                AnchorPane.setLeftAnchor(browseContainer, null);
                AnchorPane.setRightAnchor(browseContainer, 0.0);

                if(subContainer.getChildren().contains(wordofTheDayContainer)) {
                    subContainer.getChildren().remove(wordofTheDayContainer);
                }

                if(subContainer.getChildren().contains(contentContainer)) {
                    subContainer.getChildren().remove(contentContainer);
                }
            }
            
            if(!subContainer.getChildren().contains(listContentContainer)){
                subContainer.getChildren().add(listContentContainer);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadListContentContainer(char letter) {
        try {

            if(subContainer.getChildren().contains(listContentContainer)) {
                subContainer.getChildren().remove(listContentContainer);
            }
            FXMLLoader listContentContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/ListContentContainer.fxml"));
            listContentContainer = listContentContainerLoader.load();
            ListContentContainerController listContentContainerController = listContentContainerLoader.getController();

            listContentContainerController.setAlphabet(letter);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        

        if (!subContainer.getChildren().contains(listContentContainer)) {
            subContainer.getChildren().add(listContentContainer);
        }
    }

    public char getAlphabet(char alphabet) {
        alphabetContainer = alphabet;
        return alphabetContainer;
    }
}
