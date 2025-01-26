package controller.tkfisch.main;

import controller.tkfisch.Controller;
import controller.tkfisch.diceSceneController;
import controller.tkfisch.gameSceneController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import backend.Gameplay;

import java.io.IOException;

public class Main extends Application {
    private diceSceneController diceSC;
    private gameSceneController gameSC;
    private  Gameplay gameplay = new Gameplay();

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        // Initialize the Controller with the primary stage
        Controller appController = new Controller(primaryStage);
        // Initialize scenes and load the first one
        appController.initApp();
        Gameplay gameplay = new Gameplay();
        gameplay.init();
        startGameLoop();

    }
    public void startGameLoop (){
        //game loop and updating logic
        AnimationTimer gameloop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!gameplay.isGameOver()){
                    String resultColor = diceSC.getResultColor();
                    System.out.println("main "+ resultColor);
                    if (resultColor != null){
                        try {
                            gameSC.move(resultColor);
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        gameloop.start();
    }

    public static void main(String[] args) {
        launch(); // Launch the JavaFX application
    }
}
