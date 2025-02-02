package controller.tkfisch;

import java.io.IOException;

import backend.Gameplay;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class diceSceneController implements SceneController {
    private Controller appController;
    private Gameplay gameplay;
    private String diceResult = "/animation/dice/result/diceResult";
    private String resultColor;
    @FXML
    private ImageView dice;
    @FXML
    private Button button;

    private boolean buttonFlag = false;


    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }


    public void init () throws IOException {
        //TODO ADD BACKGROUND ANIMATION
        gameplay = appController.getGameplay();
    }
    public void switchToGame() {
        try {
            appController.switchToScene("game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String rollDice (){
        return gameplay.roll();
    }
    public void displayResult() throws IOException {
        stopMusic();
        if (buttonFlag){
            buttonFlag = false;
            button.setText("Roll");
            //System.out.println("switching to game");
            switchToGame();
            return;
        }
        //play animation according to result colour
        resultColor = rollDice();
        String tempResult = diceResult + resultColor + "/";
        System.out.println(resultColor);
        System.out.println(tempResult);
        Timeline tl = appController.playAnimation(tempResult,22,0.05,dice);
        tl.play();
        buttonFlag = true;
        button.setText("Continue");
        music();

    }
    public String getResultColor(){
        return resultColor;
    }
    public void setResultColor(String color){
        this.resultColor = color;
    }

    MediaPlayer mediaPlayer;
    public void music(){
        String s = getClass().getResource("/music/roll.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
    }
    public void stopMusic() {
        if (mediaPlayer != null) {
        mediaPlayer.stop();
        }
    }
}
