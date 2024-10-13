package pamagbalen.application.tool;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

public abstract class ListAbstract {
    protected void animatelistContainer(ScrollPane pane){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pane);
        transition.setFromX(-500);
        transition.setToX(0);
        transition.play();
    }

    protected void exitAnimation(ScrollPane pane){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pane);
        transition.setFromX(-0);
        transition.setToX(-500);
        transition.play();
    }

    protected void animateLabel(Label l){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), l);
        transition.setFromX(-500);
        transition.setToX(0);
        transition.play();
    }

    protected void animateContentContainer(VBox container) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), container);
        transition.setFromX(2000);
        transition.setToX(0);
        transition.play();
    }

    protected void animateContentContainerFade(VBox container) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), container);
        transition.setFromX(0);
        transition.setToX(2000);
        transition.play();
    }
}
