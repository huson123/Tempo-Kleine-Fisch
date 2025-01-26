package controller.tkfisch;

import backend.Gameplay;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class diceSceneController implements SceneController {
    private Controller appController;
    private Gameplay gameplay;
    private String diceResult = "/animation/dice/diceResult";
    private String resultColor = null;
    @FXML
    private ImageView dice = new ImageView();


    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }


    public void initialize (){
        //TODO ADD BACKGROUND ANIMATION
        dice.setImage (new Image(getClass().getResourceAsStream("/image/dice.png")));
        dice.setFitWidth(40);
        dice.setX(100);
        dice.setY(100);
        dice.setPreserveRatio(true);
        dice.setSmooth(true);
        dice.setCache(true);
    }
    public void switchToGame() {
        try {
            appController.switchToScene("game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String rollDice (){
        return gameplay.roll();
    }
    public void displayResult() {
        //play animation according to result colour
        resultColor = rollDice();
        diceResult += resultColor;
        System.out.println(resultColor);
        appController.playAnimation(diceResult,22,0.5,dice);

    }
    public String getResultColor(){
        return resultColor;
    }

}
