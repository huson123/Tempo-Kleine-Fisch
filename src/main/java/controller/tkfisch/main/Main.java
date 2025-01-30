package controller.tkfisch.main;

import java.io.IOException;

import backend.Gameplay;
import controller.tkfisch.Controller;
import controller.tkfisch.SceneController;
import controller.tkfisch.diceSceneController;
import controller.tkfisch.fishSelectSceneController;
import controller.tkfisch.gameSceneController;
import controller.tkfisch.instructionSceneController;
import controller.tkfisch.resultLSceneController;
import controller.tkfisch.resultWSceneController;
import controller.tkfisch.selectSceneController;
import controller.tkfisch.startSceneController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements SceneController {
    //import controllers
    private diceSceneController diceSC;
    private gameSceneController gameSC;
    private fishSelectSceneController fishSSC;
    private selectSceneController sceneSC;
    private startSceneController startSC;
    private instructionSceneController instructionSC;
    private resultWSceneController resultWSC;
    private resultLSceneController resultLSC;

    private Gameplay gameplay;
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
        gameplay = appController.getGameplay();
        gameplay.init();

        //get instances of controllers
        diceSC = (diceSceneController) appController.getSceneController("dice");
        gameSC = (gameSceneController) appController.getSceneController("game");
        fishSSC = (fishSelectSceneController) appController.getSceneController("fishSelect");
        startSC = (startSceneController) appController.getSceneController("start");
        instructionSC = (instructionSceneController) appController.getSceneController("instruction");
        resultWSC = (resultWSceneController) appController.getSceneController("resultW");
        resultLSC = (resultLSceneController) appController.getSceneController("resultL");

        //scenes init
        gameSC.init();
        diceSC.init();
        fishSSC.init();
        startSC.init();
        instructionSC.init();
        resultWSC.init();
        resultLSC.init();

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
                                System.out.println("move called with resultColor: " + resultColor);
                                gameSC.move(resultColor);
                                diceSC.setResultColor(null);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    gameplay.endGameUpdate();
                    //System.out.println(gameplay.isGameOver());
                    //System.out.println(gameplay.getEntities());
                }
                else {
                    gameloop.stop();
                    if (gameplay.printResult().equals("You Win!"))   appController.switchToScene("resultW");
                    if (gameplay.printResult().equals("You Lose!"))   appController.switchToScene("resultL");
                }
            }
        };
        gameloop.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
