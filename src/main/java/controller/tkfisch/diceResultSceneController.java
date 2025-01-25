package controller.tkfisch;

import backend.Gameplay;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class diceResultSceneController implements SceneController {
    private Controller appController;

    @FXML
    private Label resultLabel; // Bind this to FXML to show results
    @FXML
    private Label boatScoreLabel, fishScoreLabel; // To show scores

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void displayResult(String text) {
        Gameplay gameplay = appController.getGameplay();

        // Show updated scores
        boatScoreLabel.setText("Boat Score: " + gameplay.getShipScore());
        fishScoreLabel.setText("Fish Score: " + gameplay.getFishScore());

        // Display entities and their positions (optional)
        StringBuilder result = new StringBuilder("Entity Positions:\n");
        gameplay.getEntities().forEach(entity ->
                result.append(entity.getName()).append(": ").append(entity.getPosition()).append("\n")
        );
        resultLabel.setText(result.toString());
        resultLabel.setText("Dice rolled: " + text);
    }

    @FXML
    public void continueToGame() {
        appController.switchToScene("game");
    }
}
