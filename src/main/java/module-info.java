module pamagbalen {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens pamagbalen.application to javafx.fxml;
    exports pamagbalen.application;

}
