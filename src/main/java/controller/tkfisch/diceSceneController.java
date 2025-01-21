package controller.tkfisch;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class diceSceneController implements SceneController {
    private Controller appController;

    private final List<String> colours = List.of("red", "blue", "yellow", "green", "orange", "pink");

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    @FXML
    public void switchToGame() {
        try {
            // Roll the dice and pass the result to the next scene
            String result = rollDice();
            diceResultSceneController resultController =
                    (diceResultSceneController) appController.getSceneController("diceResult");
            resultController.displayResult(result);

            appController.switchToScene("diceResult");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String rollDice() {
        Random rand = new Random();
        return colours.get(rand.nextInt(colours.size()));
    }
}
