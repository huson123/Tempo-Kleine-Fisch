package view;

import java.io.IOException;

import controller.Controller;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class startSceneController implements SceneController {
   private selectSceneController sceneSC;
   private Controller appController;
   @FXML
   AnchorPane pane;

   private String bg = "/animation/background/start/";

   @Override
   public void setAppController(Controller appController) {
      this.appController = appController;
   }

   public void init() throws IOException {
      Timeline tl = new Timeline();
      tl = appController.setBackgroundAnimation(bg, 12, 0.2, pane);
      tl.play();
      pane.setOpacity(1);
   }
   public void switchToSelect(ActionEvent event) throws IOException {
      music();
      sceneSC = (selectSceneController) appController.getSceneController("select");
      sceneSC.init();
      appController.switchToScene("select");
   }
   public void switchToInstruction(ActionEvent event) {
      music();
      appController.switchToScene("instruction");
   }
   public void Exit(ActionEvent event) {
      System.exit(0);
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
