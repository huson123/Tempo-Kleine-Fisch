package controller.tkfisch;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class diceResultSceneController implements SceneController {
    private Controller appController;
    private String diceResult = "/aninmation/dice/diceResult";
    private String resultColor = null;
    @FXML
    private ImageView dice;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void initialize (){
        //TODO ADD BACKGROUND ANIMATION
    }
    public void switchToGame() {
        try {
            appController.switchToScene("game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayResult() {
        //play animation according to result colour
        diceSceneController diceSC = new diceSceneController();
        resultColor = diceSC.rollDice();
        diceResult += resultColor;
        appController.playAnimation(diceResult,22,0.5,dice);

    }
    public String getResultColor(){
        return resultColor;
    }


}
