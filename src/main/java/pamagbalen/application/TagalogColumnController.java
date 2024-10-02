package pamagbalen.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TagalogColumnController {

    private double contentHeight;
    private final double minHeight = 200.0;

    @FXML
    private VBox vBoxContainer;
    
    @FXML
    private TextArea textArea;

    @FXML
    public void initialize() {
            textArea.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                switch (event.getCode()) {
                    case ENTER:
                        expandVBox();
                    break;
                    case BACK_SPACE:
                        checkIfLineDeleted();
                    break;
                default:
                    break;
            }
        });
    }
    
    public void expandVBox() {

        contentHeight = textArea.getScrollTop() + textArea.getPrefHeight();    
        System.out.println("SUCCESSFULLY USED ENTER");
        vBoxContainer.setPrefHeight(vBoxContainer.getHeight() + contentHeight);
    }

    public void checkIfLineDeleted() {
        String currentText = textArea.getText();
        int caretPosition = textArea.getCaretPosition();

        if (caretPosition > 0 && currentText.charAt(caretPosition - 1) == '\n') {
            shrinkVBox();
        }
    }

    public void shrinkVBox() {
        double newHeight = vBoxContainer.getHeight() - contentHeight;

        if (newHeight > minHeight) {
            System.out.println("LINE DELETED - VBox SHRINK YOU BOII");
            vBoxContainer.setPrefHeight(newHeight);
        } else {
            System.out.println("MINIMUM HEIGHT REACHED - BAWAL NA MAG SHRINK");
            vBoxContainer.setPrefHeight(minHeight);
        }
    }
}
