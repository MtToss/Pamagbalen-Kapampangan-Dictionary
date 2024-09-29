package pamagbalen.application;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainWindowController {
    
    @FXML
    private ImageView imageID;

    @FXML
    private Image image;

    @FXML
    public void initialize() {
        image = new Image("file:/C:/Users/Cedric%20Casanova/ForGit/DSAPROJECT/translator/src/main/resources/pamagbalen/img/Title.png");
        imageID.setImage(image);
        System.out.println("PASSED TEST 2");
    }

}
