package view;

import java.io.IOException;

import backend.Gameplay;
import controller.Controller;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class diceSceneController implements SceneController {
    private Controller appController;
    private Gameplay gameplay;
    private String diceResult = "/animation/dice/result/diceResult";
    private String resultColor;
    private Timeline tl = new Timeline();
    @FXML
    private ImageView dice;
    @FXML
    private ImageView rollButtonImageView;
    @FXML
    private Button button;

    private boolean buttonFlag = false;
    @FXML
    AnchorPane pane;
    private String bgurl = "/image/1.png";


    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }


    public void init () throws IOException {
        //TODO ADD BACKGROUND ANIMATION
        gameplay = appController.getGameplay();
        Image img = new Image(getClass().getResourceAsStream(bgurl));
        BackgroundImage bgImage = new BackgroundImage(img, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgImage);
        pane.setBackground(bg);
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
        
        if (buttonFlag){
            buttonFlag = false;
            addDelay(0.1, () -> {
                rollButtonImageView.setVisible(true);
                switchToGame();
            });
            return;
        }
        //play animation according to result colour
        if (!(tl.getStatus() == Animation.Status.RUNNING)){
            stopMusic();
            resultColor = rollDice();
            String tempResult = diceResult + resultColor + "/";
            //System.out.println(resultColor);
            //System.out.println(tempResult);
            tl = appController.playAnimation(tempResult,22,0.05,dice);
            tl.play();
            music();
            tl.setOnFinished(e -> {
                buttonFlag = true;
                rollButtonImageView.setVisible(false);
            });
        }

    }

    private void addDelay(double seconds, Runnable onComplete) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> onComplete.run());
        pause.play();
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
