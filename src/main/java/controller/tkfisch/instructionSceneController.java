package controller.tkfisch;

import java.io.IOException;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class instructionSceneController implements SceneController {
    private Controller appController;

    @FXML
    AnchorPane pane;
    private String bg = "/animation/background/start/";
    
    @FXML
    private TextArea instructionText;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    @FXML
    public void init() throws IOException {
        Timeline tl = new Timeline();
        tl = appController.setBackgroundAnimation(bg, 12, 0.2, pane);
        tl.play();
        pane.setOpacity(1);
    }

    public void switchToStart(ActionEvent event) {
        music();
        appController.switchToScene("start");
    }

    MediaPlayer mediaPlayer;
    public void music(){
        String s = getClass().getResource("/music/tap.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }
}
