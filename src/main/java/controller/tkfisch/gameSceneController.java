package controller.tkfisch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class gameSceneController {
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    ImageView diceImage;


    public void switchToDice(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/scene/diceScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void dicePressed (MouseEvent event) throws IOException {
        System.out.println("dice pressed");
        switchToDice(event);
    }
}
