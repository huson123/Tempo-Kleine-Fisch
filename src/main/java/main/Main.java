package main;

import java.io.IOException;

import model.Gameplay;
import controller.Controller;
import view.SceneController;
import view.diceSceneController;
import view.fishSelectSceneController;
import view.gameSceneController;
import view.instructionSceneController;
import view.resultLSceneController;
import view.resultTSceneController;
import view.resultWSceneController;
import view.selectSceneController;
import view.startSceneController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application implements SceneController {
    //import controllers
    private diceSceneController diceSC;
    private gameSceneController gameSC;
    private fishSelectSceneController fishSSC;
    private selectSceneController selectSC;
    private startSceneController startSC;
    private instructionSceneController instructionSC;
    private resultWSceneController resultWSC;
    private resultLSceneController resultLSC;
    private resultTSceneController resultTSC;

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

        // set non resizable
        Stage stage = appController.getPrimaryStage();
        stage.setResizable(false);

        //get instances of controllers
        diceSC = (diceSceneController) appController.getSceneController("dice");
        gameSC = (gameSceneController) appController.getSceneController("game");
        fishSSC = (fishSelectSceneController) appController.getSceneController("fishSelect");
        startSC = (startSceneController) appController.getSceneController("start");
        instructionSC = (instructionSceneController) appController.getSceneController("instruction");


        //scenes init
        diceSC.init();
        fishSSC.init();
        startSC.init();
        music();
        instructionSC.init();
        
        

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
                    try {
                        result();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        gameloop.start();
    }

    public void result() throws IOException {
        if (gameplay.printResult().equals("You Win!")) {
            resultWSC = (resultWSceneController) appController.getSceneController("resultW");
            resultWSC.init();
            stopMusic();
            musicW();
            appController.switchToScene("resultW");
        }
        if (gameplay.printResult().equals("You Lose!")) {
            resultLSC = (resultLSceneController) appController.getSceneController("resultL");
            resultLSC.init();
            stopMusic();
            musicnext();
            appController.switchToScene("resultL");
        }
        if (gameplay.printResult().equals("Tie!")) {
            resultTSC = (resultTSceneController) appController.getSceneController("resultT");
            resultTSC.init();
            stopMusic();
            musicnext();
            appController.switchToScene("resultT");
        }
    }

    MediaPlayer mediaPlayer;
    public void music(){
        String s = getClass().getResource("/music/gamemusic.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    public void musicW(){
        String s = getClass().getResource("/music/winmusic.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.play();
    }
    public void musicnext(){
        String s = getClass().getResource("/music/loseortiemusic.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.play();
    }
    public void stopMusic() {
        if (mediaPlayer != null) {
        mediaPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
