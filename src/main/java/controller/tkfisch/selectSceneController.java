package controller.tkfisch;

import java.io.IOException;

import backend.Gameplay;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class selectSceneController implements SceneController {
    private Controller appController;
    private gameSceneController gameSC;

    @FXML
    public void init(){};

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void fishPressed(MouseEvent event) throws IOException{
        music();
        gameSC = (gameSceneController) appController.getSceneController("game");
        Gameplay gameplay = appController.getGameplay();
        gameplay.setPlayerType("Fish"); // Update backend logic
        gameSC.init();
        appController.switchToScene("game");
        //System.out.println("gaming");

    }

    public void shipPressed(MouseEvent event) throws IOException{
        music();
        gameSC = (gameSceneController) appController.getSceneController("game");
        Gameplay gameplay = appController.getGameplay();
        gameplay.setPlayerType("Ship");
        gameSC.init();
        appController.switchToScene("game");

    }

    // Event handlers for mouse hover and exit (ship)
    public void shipEntered(MouseEvent event) {
        System.out.println("Mouse entered the ship image.");
        // Add hover effect logic here, e.g., changing opacity
    }

    public void shipExited(MouseEvent event) {
        System.out.println("Mouse exited the ship image.");
        // Add hover effect logic here, e.g., resetting opacity
    }

    // Event handlers for mouse hover and exit (FISH)
    public void fishEntered(MouseEvent event) {
        System.out.println("Mouse entered the FISH image.");
        // Add hover effect logic here, e.g., changing opacity
    }

    public void fishExited(MouseEvent event) {
        System.out.println("Mouse exited the FISH image.");
        // Add hover effect logic here, e.g., resetting opacity
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
