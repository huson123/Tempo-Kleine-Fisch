package view;

import model.Gameplay;
import controller.Controller;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class fishSelectSceneController implements SceneController {
    //written by Phuc Tan Hien Tran
    private Controller appController;
    private gameSceneController gameSC;
    private Gameplay gameplay;
    private String bg = "/animation/background/start/";
    Timeline tl = new Timeline();

    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane bgPane;

    @FXML
    private ImageView blueFish, pinkFish, yellowFish, orangeFish;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }//written by Phuc Tan Hien Tran


    public void switchToGame() {
        //written by Phuc Tan Hien Tran
        try {
            appController.switchToScene("game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void init() throws IOException {
        //written by Phuc Tan Hien Tran
       gameSC = (gameSceneController) appController.getSceneController("game");
       gameplay = appController.getGameplay();
        if (!(tl.getStatus() == Animation.Status.RUNNING)){
            tl = appController.setBackgroundAnimation(bg, 12, 0.2, pane);
            tl.play();
            pane.setOpacity(0.8);
        }
        nonColor();
       //System.out.println(gameplay);
       //System.out.println(gameSC);
       //System.out.println(blueFish.getScene() == appController.getScene("fishSelect"));
    }

    public void nonColor(){
        //written by Phuc Tan Hien Tran
        // remove the colors which has been caught or escaped
        List<String> caughtFish = gameplay.getCaughtFish();
        List<String> escapedFish = gameplay.getEscapedFish();
        for(String fish : caughtFish){
            removeImageView(convertStrToImageView(fish));
        }
        for(String fish : escapedFish){
            removeImageView(convertStrToImageView(fish));
        }

    }
    public void removeImageView(ImageView obj){
        //written by Phuc Tan Hien Tran
        //System.out.println("Removed ImageView: " + obj);
        bgPane.getChildren().remove(obj);
    }
    public ImageView convertStrToImageView(String color){
        //written by Phuc Tan Hien Tran
        switch (color){
            case "Blue":
                return blueFish;
            case "Orange":
                return orangeFish;
            case "Pink":
                return pinkFish;
            case "Yellow":
                return yellowFish;
        }
        return null;
    }

    public void blueFishPressed() throws IOException {
        //written by Huynh Anh Duc Truong
        //blueFish.setImage(blueFishPressed);
        //gameSC.setFishSelectedColor("Blue");
        gameSC.move("Blue");
        switchToGame();
    }

    public void blueFishEntered() {
        //written by Huynh Anh Duc Truong
        //blueFish.setImage(blueFishPressed);
        blueFish.setOpacity(1);
    }

    public void blueFishExited() {
        //written by Huynh Anh Duc Truong
        //blueFish.setImage(blueFishReleased);
        blueFish.setOpacity(0.5);
    }

    public void pinkFishPressed() throws IOException {
        //written by Huynh Anh Duc Truong
        //pinkFish.setImage(pinkFishPressed);
        //gameSC.setFishSelectedColor("Pink");
        gameSC.move("Pink");
        switchToGame();

    }

    public void pinkFishEntered() {
        //written by Huynh Anh Duc Truong
        //pinkFish.setImage(pinkFishPressed);
        pinkFish.setOpacity(1);
    }

    public void pinkFishExited() {
        //pinkFish.setImage(pinkFishReleased);
        pinkFish.setOpacity(0.5);
    }

    public void orangeFishPressed() throws IOException {
        //written by Huynh Anh Duc Truong
        //orangeFish.setImage(orangeFishPressed);
        //gameSC.setFishSelectedColor("Orange");
        gameSC.move("Orange");
        switchToGame();

    }

    public void orangeFishEntered() {
        //written by Huynh Anh Duc Truong
        //orangeFish.setImage(orangeFishPressed);
        orangeFish.setOpacity(1);
    }

    public void orangeFishExited() {
        //written by Huynh Anh Duc Truong
        //orangeFish.setImage(orangeFishReleased);
        orangeFish.setOpacity(0.5);
    }

    public void yellowFishPressed() throws IOException {
        //written by Huynh Anh Duc Truong
        //yellowFish.setImage(yellowFishPressed);
        //gameSC.setFishSelectedColor("Yellow");
        gameSC.move("Yellow");
        switchToGame();

    }

    public void yellowFishEntered() {
        //written by Huynh Anh Duc Truong
        //yellowFish.setImage(yellowFishPressed);
        yellowFish.setOpacity(1);
    }

    public void yellowFishExited() {
        //written by Huynh Anh Duc Truong
        //yellowFish.setImage(yellowFishReleased);
        yellowFish.setOpacity(0.5);
    }
}
