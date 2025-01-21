package controller.tkfisch;

import javafx.scene.input.MouseEvent;

public class gameSceneController implements SceneController {
    private Controller appController;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void dicePressed(MouseEvent event) {
        appController.switchToScene("dice");
    }
}
