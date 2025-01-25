package controller.tkfisch;

import backend.Gameplay;
import javafx.fxml.FXML;

public class diceSceneController implements SceneController {
    private Controller appController;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    @FXML
    public void rollDice() {
        Gameplay gameplay = appController.getGameplay();

        // Perform dice roll and movement
        String diceResult = gameplay.rollAndMove();

        // Update the diceResultSceneController with the current state
        diceResultSceneController resultController = (diceResultSceneController) appController.getSceneController("diceResult");
        resultController.displayResult(diceResult);

        appController.switchToScene("diceResult");
    }
}