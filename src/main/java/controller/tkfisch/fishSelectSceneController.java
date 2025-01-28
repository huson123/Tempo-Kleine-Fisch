package controller.tkfisch;

import backend.Gameplay;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class fishSelectSceneController implements SceneController {
    private Controller appController;
    private gameSceneController gameSC;
    private Gameplay gameplay;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView blueFish, pinkFish, yellowFish, orangeFish;

    private final Image blueFishPressed = new Image(getClass().getResourceAsStream("/image/blueFishPressed.jpg"));
    private final Image blueFishReleased = new Image(getClass().getResourceAsStream("/image/blueFishReleased.png"));

    private final Image pinkFishPressed = new Image(getClass().getResourceAsStream("/image/pinkFishPressed.jpg"));
    private final Image pinkFishReleased = new Image(getClass().getResourceAsStream("/image/pinkFishReleased.jpg"));

    private final Image yellowFishPressed = new Image(getClass().getResourceAsStream("/image/yellowFishPressed.jpg"));
    private final Image yellowFishReleased = new Image(getClass().getResourceAsStream("/image/yellowFishReleased.jpg"));

    private final Image orangeFishPressed = new Image(getClass().getResourceAsStream("/image/orangeFishPressed.jpg"));
    private final Image orangeFishReleased = new Image(getClass().getResourceAsStream("/image/orangeFishReleased.jpg"));

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
    public void init(){
       gameSC = (gameSceneController) appController.getSceneController("game");
       gameplay = appController.getGameplay();
       //System.out.println(gameplay);
       //System.out.println(gameSC);
       //System.out.println(blueFish.getScene() == appController.getScene("fishSelect"));
       nonColor();
    }

    public void nonColor(){
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
        //System.out.println("Removed ImageView: " + obj);
        pane.getChildren().remove(obj);
    }
    public ImageView convertStrToImageView(String color){
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
        blueFish.setImage(blueFishPressed);
        //gameSC.setFishSelectedColor("Blue");
        gameSC.move("Blue");
        switchToGame();
    }

    public void blueFishEntered() {
        blueFish.setImage(blueFishPressed);
    }

    public void blueFishExited() {
        blueFish.setImage(blueFishReleased);
    }

    public void pinkFishPressed() throws IOException {
        pinkFish.setImage(pinkFishPressed);
        //gameSC.setFishSelectedColor("Pink");
        gameSC.move("Pink");
        switchToGame();

    }

    public void pinkFishEntered() {
        pinkFish.setImage(pinkFishPressed);
    }

    public void pinkFishExited() {
        pinkFish.setImage(pinkFishReleased);
    }

    public void orangeFishPressed() throws IOException {
        orangeFish.setImage(orangeFishPressed);
        //gameSC.setFishSelectedColor("Orange");
        gameSC.move("Orange");
        switchToGame();

    }

    public void orangeFishEntered() {
        orangeFish.setImage(orangeFishPressed);
    }

    public void orangeFishExited() {
        orangeFish.setImage(orangeFishReleased);
    }

    public void yellowFishPressed() throws IOException {
        yellowFish.setImage(yellowFishPressed);
        //gameSC.setFishSelectedColor("Yellow");
        gameSC.move("Yellow");
        switchToGame();

    }

    public void yellowFishEntered() {
        yellowFish.setImage(yellowFishPressed);
    }

    public void yellowFishExited() {
        yellowFish.setImage(yellowFishReleased);
    }
}
