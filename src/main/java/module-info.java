module controller.tkfisch {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    requires javafx.media;
    requires java.sql;

    opens view to javafx.fxml;
    opens scene to javafx.fxml;
    exports view;
    exports main;
    opens main to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;


}
