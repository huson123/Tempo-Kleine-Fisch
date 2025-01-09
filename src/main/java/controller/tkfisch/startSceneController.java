package controller.tkfisch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class startSceneController {
   Parent root;
   Stage stage;
   Scene scene;

   public void switchToSelect(ActionEvent event) throws IOException {
      root = FXMLLoader.load(getClass().getResource("/scene/selectScene.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }
}