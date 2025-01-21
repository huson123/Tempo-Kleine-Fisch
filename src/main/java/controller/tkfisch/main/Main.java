package controller.tkfisch.main;

import controller.tkfisch.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Initialize the Controller with the primary stage
        Controller appController = new Controller(primaryStage);

        // Initialize scenes and load the first one
        appController.initApp();
    }

    public static void main(String[] args) {
        launch(); // Launch the JavaFX application
    }
}
