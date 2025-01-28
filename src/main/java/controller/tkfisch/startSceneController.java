package controller.tkfisch;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class startSceneController implements SceneController {
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
      pane.setOpacity(0.5);
   }
   public void switchToSelect(ActionEvent event) {
      appController.switchToScene("select");
   }
}
