module pamagbalen {
    requires javafx.controls;
    requires javafx.fxml;

    opens pamagbalen to javafx.fxml;
    exports pamagbalen;
}
