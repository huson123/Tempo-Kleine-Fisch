package controller.tkfisch;

import javafx.scene.input.MouseEvent;
import backend.Gameplay;

public class gameSceneController implements SceneController {
    private Controller appController;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void dicePressed(MouseEvent event) {
        Gameplay gameplay = appController.getGameplay();

        // Roll the dice and move entities
        gameplay.rollAndMove();
        gameplay.update();

        // Switch to diceResult scene to display the result
        appController.switchToScene("diceResult");
    }
}
