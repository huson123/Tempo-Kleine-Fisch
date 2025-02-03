package view;

import java.io.IOException;

import model.Gameplay;
import controller.Controller;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class selectSceneController implements SceneController {
    //written by Phuc Tan Hien Tran
    private Controller appController;
    private gameSceneController gameSC;
    private String bg = "/animation/background/start/";

    @FXML
    private AnchorPane pane;

    @FXML
    ImageView ship;

    @FXML
    ImageView fish;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }//written by Phuc Tan Hien Tran

    public void init() throws IOException {
        //written by Phuc Tan Hien Tran
        Timeline tl = new Timeline();
        tl = appController.setBackgroundAnimation(bg, 12, 0.2, pane);
        tl.play();
        pane.setOpacity(0.8);

    };

    public void fishPressed(MouseEvent event) throws IOException{
        //written by Phuc Tan Hien Tran
        music();
        gameSC = (gameSceneController) appController.getSceneController("game");
        Gameplay gameplay = appController.getGameplay();
        gameplay.setPlayerType("Fish"); // Update backend logic
        gameSC.init();
        appController.switchToScene("game");
        //System.out.println("gaming");

    }

    public void shipPressed(MouseEvent event) throws IOException{
        //written by Phuc Tan Hien Tran
        music();
        gameSC = (gameSceneController) appController.getSceneController("game");
        Gameplay gameplay = appController.getGameplay();
        gameplay.setPlayerType("Ship");
        gameSC.init();
        appController.switchToScene("game");

    }

    // Event handlers for mouse hover and exit (ship)
    public void shipEntered(MouseEvent event) {
        //written by Phuc Tan Hien Tran
        //System.out.println("Mouse entered the ship image.");
        ship.setOpacity(1);
    }

    public void shipExited(MouseEvent event) {
        //written by Phuc Tan Hien Tran
        //System.out.println("Mouse exited the ship image.");
        ship.setOpacity(0.5);
    }

    public void fishEntered(MouseEvent event) {
        //written by Phuc Tan Hien Tran
        //System.out.println("Mouse entered the FISH image.");
        fish.setOpacity(1);
    }

    public void fishExited(MouseEvent event) {
        //written by Phuc Tan Hien Tran
        //System.out.println("Mouse exited the FISH image.");
        fish.setOpacity(0.5);
    }

    MediaPlayer mediaPlayer;
    public void music(){
        //written by Huynh Anh Duc Truong
        String s = getClass().getResource("/music/tap.mp3").toExternalForm();
        Media h = new Media(s);
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }
}
