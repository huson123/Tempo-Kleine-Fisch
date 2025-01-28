package controller.tkfisch.main;

import controller.tkfisch.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import backend.Gameplay;

import java.io.IOException;

public class Main extends Application implements SceneController {
    //import controllers
    private diceSceneController diceSC;
    private gameSceneController gameSC;
    private fishSelectSceneController fishSSC;
    private selectSceneController sceneSC;
    private startSceneController startSC;

    private Gameplay gameplay = new Gameplay() ;
    private Controller appController;
    private Stage stage = new Stage();
    private AnimationTimer gameloop;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize the Controller with the primary stage
        appController = new Controller(stage);
        // Initialize scenes and load the first one
        appController.initApp();
        gameplay.init();

        //get instances of controllers
        diceSC = (diceSceneController) appController.getSceneController("dice");
        gameSC = (gameSceneController) appController.getSceneController("game");
        fishSSC = (fishSelectSceneController) appController.getSceneController("fishSelect");

        //scenes init
        gameSC.init();
        fishSSC.init();
        startGameLoop();
    }
    public void startGameLoop () throws IOException {
        //game loop and updating logic
        gameloop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!gameplay.isGameOver()){
                    if(stage.getScene() == appController.getScene("game")){
                        String resultColor = diceSC.getResultColor();
                        if (resultColor != null){
                            try {
                                gameSC.move(resultColor);
                                diceSC.setResultColor(null);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    gameplay.endGameUpdate();
                }
            }
        };
        gameloop.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
