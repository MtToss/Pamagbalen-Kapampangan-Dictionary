module pamagbalen {
    requires javafx.controls;
    requires javafx.fxml;

    opens pamagbalen.application to javafx.fxml;
    exports pamagbalen.application;

}
