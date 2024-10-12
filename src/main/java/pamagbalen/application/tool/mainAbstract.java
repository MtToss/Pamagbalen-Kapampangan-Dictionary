package pamagbalen.application.tool;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
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
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), box);
        if (show) {
            transition.setFromX(400);
            transition.setToX(-800);
            transition.setToY(75);
        } else {
            transition.setFromX(0);
            transition.setToX(-300);
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
        transition.setFromX(-800);
        transition.setToX(-1800);
        transition.play();
    }

    protected void addDelay(Duration duration, Runnable action) {
        PauseTransition pause = new PauseTransition(duration);
        pause.setOnFinished(event -> action.run());
        pause.play();
    }
}
