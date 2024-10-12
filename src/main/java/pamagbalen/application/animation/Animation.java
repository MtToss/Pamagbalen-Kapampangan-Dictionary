package pamagbalen.application.animation;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public abstract class Animation {

    public abstract void animate(AnchorPane anchorPane);

    protected void performPushTransition(AnchorPane anchorPane) {
        TranslateTransition pushIn = new TranslateTransition(Duration.seconds(5), anchorPane);
        pushIn.setFromX(-300); // Start from left outside the scene
        pushIn.setToX(0); // Move to original position

        TranslateTransition pushOut = new TranslateTransition(Duration.seconds(5), anchorPane);
        pushOut.setFromX(0); // Start from original position
        pushOut.setToX(-300); // Move out to left outside the scene

        SequentialTransition sequentialTransition = new SequentialTransition(pushIn, pushOut);
        
        sequentialTransition.play();
    }
}
