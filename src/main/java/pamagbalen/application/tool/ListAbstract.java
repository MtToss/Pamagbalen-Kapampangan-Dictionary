package pamagbalen.application.tool;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public abstract class ListAbstract {
    

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
