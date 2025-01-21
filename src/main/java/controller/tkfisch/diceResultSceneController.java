package controller.tkfisch;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class diceResultSceneController implements SceneController {
    private Controller appController;

    @FXML
    private Label result;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void switchToGame() {
        try {
            appController.switchToScene("game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayResult(String colour) {
        result.setText(colour);
    }

    public String getRoot(String state) {
        if (state.equals("fish")) {
            String colour = result.getText();
            if (colour.equals("yellow") || colour.equals("orange") || colour.equals("pink") || colour.equals("blue")) {
                return "fishSelect";
            }
        }
        return "game";
    }
}
