package pamagbalen.application.tool;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public abstract class mainAbstract {
    
    protected void animatePane(Pane pane, boolean show) {
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

    protected void animateVBox(VBox box, boolean show) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), box);
        if (show) {
            transition.setFromX(-800);
            transition.setToX(50);
            transition.setToY(75);
            
            
        } else {
            transition.setFromX(-400);
            transition.setToX(0);
        }
        transition.play();
    }

    protected void animateExit(VBox box) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), box);
        transition.setFromX(0);
        transition.setToX(-800);
        transition.play();
    }

    protected void animateExitContentContainer(VBox box) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), box);
        transition.setFromX(50);
        transition.setToX(-800);
        transition.play();
    }

    protected void animateExitWordofTheDayContainer(VBox box) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), box);
        transition.setFromX(50);
        transition.setToX(-800);
        transition.play();
    }

    protected void addDelay(Duration duration, Runnable action) {
        PauseTransition pause = new PauseTransition(duration);
        pause.setOnFinished(event -> action.run());
        pause.play();
    }

    protected void setYAnimation(Pane pane) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pane);
        transition.setFromY(-300);   
        transition.play();
    }

    protected void animateEntrance(VBox box) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), box);
 
        transition.setFromX(-700);
        transition.setToX(0); 

        transition.play();
    }

    protected void animateBottomPaneContainer(AnchorPane pane) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pane);

        transition.setFromY(0);
        transition.setToY(-400);
    }

    protected void addAndAnimateContentContainer(VBox container, HBox subContainer) {
        if(!subContainer.getChildren().contains(container)) {
            subContainer.getChildren().add(container);
            animateVBox(container, true);
        }
    } 

    protected void animateSelectorContainer(AnchorPane pane) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pane);
        
        transition.setFromY(500);
        transition.setToY(0);
        transition.play();

    }

    protected void animateLCC(AnchorPane pane){
        FadeTransition fade = new FadeTransition(Duration.seconds(1), pane);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

    }
}
