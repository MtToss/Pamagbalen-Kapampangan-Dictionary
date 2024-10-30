package pamagbalen.application;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pamagbalen.application.tool.mainAbstract;

public class MainWindowController extends mainAbstract {

    @FXML
    private HBox subContainer;

    @FXML
    private TextField searchTextField;

    @FXML
    private AnchorPane bottomPaneContainer;

    @FXML
    private Pane browsePane;

    @FXML
    private Pane homePane;

    @FXML
    private Label browseLabel, homeLabel;

    @FXML
    private AnchorPane browseAnchorPane;

    @FXML
    private AnchorPane homeAnchorPane;

    char alphabetContainer = 'b';
    VBox wordofTheDayContainer = null;
    VBox contentContainer = null;
    AnchorPane selectorContainer = null;
    AnchorPane listContentContainer = null;

    



    @FXML
    public void initialize() {
        setYAnimation(browsePane);
        setYAnimation(homePane);
        loadArea();
        wordofTheDayContainer.setTranslateY(75);
        wordofTheDayContainer.setTranslateX(100); 
        
    }

    private void loadArea() {
        try {
            FXMLLoader wordofTheDayLoader = new FXMLLoader(getClass().getResource("/pamagbalen/WordofTheDay.fxml"));
            wordofTheDayContainer = wordofTheDayLoader.load();
            WordofTheDayController wordofTheDayController = wordofTheDayLoader.getController();
            wordofTheDayController.setWordOfTheDay();

            subContainer.getChildren().add(wordofTheDayContainer);
            HBox.setMargin(wordofTheDayContainer, new Insets(50, 50, 0, 50));

        } 
        catch (IOException e) {
            System.out.println("Load Area Error: " + e.getMessage());
            
        }
    }

    @FXML
    public void onBrowsePaneShow() {
        browseAnchorPane.setStyle("-fx-background-color: #003049;");
        browseLabel.setTextFill(Color.WHITE);
    }

    @FXML
    public void onBrowsePaneHide() {
        browseAnchorPane.setStyle("-fx-background-color: STEELBLUE;");
        browseLabel.setTextFill(Color.BLACK);
    }

    @FXML
    public void onHomePaneShow() {
        homeAnchorPane.setStyle("-fx-background-color:  #003049;");
        homeLabel.setTextFill(Color.WHITE);
    }

    @FXML
    public void onHomePaneHide() {
        homeAnchorPane.setStyle("-fx-background-color: STEELBLUE;");
        homeLabel.setTextFill(Color.BLACK);
    }


    @FXML
    @SuppressWarnings("unused")
    private void buttonClicked() {
        String wordContainer = searchTextField.getText();

        try {
            if (contentContainer == null) {
                FXMLLoader contentContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/ContentContainer.fxml"));
                contentContainer = contentContainerLoader.load();
                ContentContainerController contentContainerController = contentContainerLoader.getController();

                contentContainerController.getWordSearched(wordContainer);
                
                if(subContainer.getChildren().contains(wordofTheDayContainer)) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));

                    pause.setOnFinished(event -> {
                    
                    contentContainer.setTranslateX(-subContainer.getWidth());
                    animateVBox(contentContainer, true);
                    subContainer.getChildren().add(contentContainer);
                    });
                    pause.play();

                }
                
                contentContainer.getProperties().put("fxController", contentContainerController); 
            } 
            else if(subContainer.getChildren().contains(contentContainer) && !subContainer.getChildren().contains(wordofTheDayContainer)) {
                
                ContentContainerController contentContainerController = (ContentContainerController) contentContainer.getProperties().get("fxController"); 


                animateExitContentContainer(contentContainer);
                
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event -> {
                    contentContainer.setTranslateX(-subContainer.getWidth());
                    contentContainerController.getWordSearched(wordContainer);
                    animateVBox(contentContainer, true);
                });
                pause.play();
                
            }

            if (subContainer.getChildren().contains(wordofTheDayContainer)) {

                animateExitWordofTheDayContainer(wordofTheDayContainer);
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                
                pause.setOnFinished(event -> { 
                    subContainer.getChildren().remove(wordofTheDayContainer); 
                    contentContainer.setTranslateX(-subContainer.getWidth());
                    animateVBox(contentContainer, true);         
                });
                pause.play();
                
            }
            
            PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
            pause1.setOnFinished(event -> {
                contentContainer.setTranslateX(-subContainer.getWidth());
                addAndAnimateContentContainer(contentContainer, subContainer);
            });
            pause1.play();

            
            if(subContainer.getChildren().contains(listContentContainer)) {
                contentContainer.setTranslateX(-subContainer.getWidth());
                exitanimateLCC(listContentContainer);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.75));
    
                pause.setOnFinished(event -> {
                    subContainer.getChildren().remove(listContentContainer);
                    
                });
                pause.play();
            }

            if(bottomPaneContainer.getChildren().contains(selectorContainer)) {
                bottomPaneContainer.getChildren().removeAll(selectorContainer);
            }

            HBox.setMargin(contentContainer, new Insets(0, 5, 0, 5));
            

        } catch (IOException e) {
            System.out.println("Button Clicked Error: " + e.getMessage());
        }
    }

    @FXML
    public void labelHomeClicked() {


        if(subContainer.getChildren().contains(contentContainer)) 
        {
            
            wordofTheDayContainer.setTranslateX(-8000);
            animateExit(contentContainer);

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                subContainer.getChildren().remove(contentContainer);
                animateEntrance(wordofTheDayContainer);
            });
            pause.play();
        }

        
        if(!subContainer.getChildren().contains(wordofTheDayContainer)) {  
            if(subContainer.getChildren().contains(listContentContainer)) {
                wordofTheDayContainer.setTranslateX(-8000);
                exitanimateLCC(listContentContainer);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.75));
    
                pause.setOnFinished(event -> {
                    subContainer.getChildren().remove(listContentContainer);
                    animateEntrance(wordofTheDayContainer);
                });
                pause.play();
            }
            wordofTheDayContainer.setVisible(true);
            
            subContainer.getChildren().add(wordofTheDayContainer);
        }

        if(bottomPaneContainer.getChildren().contains(selectorContainer)) {
            bottomPaneContainer.getChildren().remove(selectorContainer);
        }
    }

    @FXML
    public void labelBrowseClicked() {
        if(selectorContainer == null) {
            FXMLLoader selectorContainerLoader = new FXMLLoader(getClass().getResource("/pamagbalen/Selector.fxml"));
            try {
                selectorContainer = selectorContainerLoader.load();
            } 
            catch (IOException e) {
                System.out.println("Label Browse Clicked Error: " + e.getMessage());
            }
            ListController browseController = selectorContainerLoader.getController();
            browseController.setMainWindowController(this); 

        }

        if(subContainer.getChildren().contains(contentContainer)) {
            subContainer.getChildren().remove(contentContainer);
        }
        
        
        
        if(!bottomPaneContainer.getChildren().contains(selectorContainer)) {

            PauseTransition pause = new PauseTransition(Duration.seconds(1));

            pause.setOnFinished(event -> {
                animateSelectorContainer(selectorContainer);
                bottomPaneContainer.getChildren().add(selectorContainer);
            });
            pause.play();

            
            AnchorPane.setTopAnchor(selectorContainer, 0.0);
            AnchorPane.setBottomAnchor(selectorContainer, 0.0);
            AnchorPane.setLeftAnchor(selectorContainer, null);
            AnchorPane.setRightAnchor(selectorContainer, 0.0);


        }

        if(subContainer.getChildren().contains(wordofTheDayContainer)) {
            animateExitWordofTheDayContainer(wordofTheDayContainer);
            PauseTransition pause = new PauseTransition(Duration.seconds(0.8));
            pause.setOnFinished(event -> {
                wordofTheDayContainer.setVisible(false);
                subContainer.getChildren().remove(wordofTheDayContainer);
            });
            pause.play();
        }

        if (listContentContainer != null && !subContainer.getChildren().contains(listContentContainer)) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                animateLCC(listContentContainer);
                subContainer.getChildren().add(listContentContainer);
            });

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
            System.out.println("Load List Content Container Error: " + e.getMessage());
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
