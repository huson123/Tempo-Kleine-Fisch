package controller.tkfisch;

import backend.Gameplay;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class diceSceneController implements SceneController {
    private Controller appController;
    private Gameplay gameplay;
    @FXML
    private ImageView dice;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void intialize (){
        //TODO ADD BACKGROUND ANIMATION
    }
    public void switchToDiceResult() {
        try {
            // Roll the dice and pass the result to the next scene
            appController.switchToScene("diceResult");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String rollDice (){
        return gameplay.roll();
    }

}
