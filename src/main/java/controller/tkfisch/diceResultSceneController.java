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

public class diceResultSceneController {
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    Label result;

    public void switchToGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/selectScene.fxml"));
        fxmlLoader.load();

        selectSceneController selectSceneController = fxmlLoader.getController();
        //get state (ship or fish) to decide course of actions
        String state = selectSceneController.getState();
        root = getRoot(state);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayResult(String colour) {
        result.setText(colour);
    }
    // move to correct scene depend on conditions
    public Parent getRoot(String state) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/scene/gameScene.fxml"));
        if (state.equals("fish")) {
            String colour = result.getText();
            if (colour.equals("yellow")
                    || colour.equals("orange")
                    || colour.equals("pink")
                    || colour.equals("blue")) {
                root = FXMLLoader.load(getClass().getResource("/scene/fishSelectScene.fxml"));
            }
        }
        return root;
    }

}
