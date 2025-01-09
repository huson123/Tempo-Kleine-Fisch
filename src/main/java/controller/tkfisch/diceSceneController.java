package controller.tkfisch;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class diceSceneController {
    Parent root;
    Stage stage;
    Scene scene;

    List<String> colour = new ArrayList<>();

    public void switchToGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/diceResultScene.fxml"));
        root = fxmlLoader.load();

        diceResultSceneController diceResultSceneController = fxmlLoader.getController();
        diceResultSceneController.displayResult(rollDice());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public String rollDice() throws IOException {
        colour.add("red");
        colour.add("blue");
        colour.add("yellow");
        colour.add("green");
        colour.add("orange");
        colour.add("pink");

        Random rand = new Random();
        return colour.get(rand.nextInt(colour.size()));
    }
}
