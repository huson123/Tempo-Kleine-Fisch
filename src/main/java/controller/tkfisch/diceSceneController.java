package controller.tkfisch;

import backend.Gameplay;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class diceSceneController implements SceneController {
    private Controller appController;
    private Gameplay gameplay = new Gameplay();
    private String diceResult = "/animation/dice/result/diceResult";
    private String resultColor;
    @FXML
    private ImageView dice;
    @FXML
    private Button button;

    private boolean buttonFlag = false;


    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }


    public void initialize () throws IOException {
        //TODO ADD BACKGROUND ANIMATION
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
    public void displayResult() throws IOException {
        if (buttonFlag){
            buttonFlag = false;
            button.setText("Roll");
            switchToGame();
            return;
        }
        //play animation according to result colour
        resultColor = rollDice();
        String tempResult = diceResult + resultColor + "/";
        System.out.println(resultColor);
        System.out.println(tempResult);
        Timeline tl = appController.playAnimation(tempResult,22,0.1,dice);
        tl.play();
        buttonFlag = true;
        button.setText("Continue");

    }
    public String getResultColor(){
        return resultColor;
    }
    public void setResultColor(String color){
        this.resultColor = color;
    }

}
