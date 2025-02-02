module controller.tkfisch {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    requires javafx.media;
    requires java.sql;

    opens controller.tkfisch to javafx.fxml;
    opens scene to javafx.fxml;
    exports controller.tkfisch;
    exports main;
    opens main to javafx.fxml;


}
