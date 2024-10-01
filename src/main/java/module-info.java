module pamagbalen {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens pamagbalen.application to javafx.fxml;
    exports pamagbalen.application;

}
