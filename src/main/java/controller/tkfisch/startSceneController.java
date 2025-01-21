package controller.tkfisch;

import javafx.event.ActionEvent;

public class startSceneController implements SceneController {
   private Controller appController;

   @Override
   public void setAppController(Controller appController) {
      this.appController = appController;
   }

   public void switchToSelect(ActionEvent event) {
      appController.switchToScene("select");
   }
}
