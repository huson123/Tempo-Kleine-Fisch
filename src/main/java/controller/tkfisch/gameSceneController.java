package controller.tkfisch;

import backend.Entity;
import backend.Gameplay;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class gameSceneController implements SceneController {
    private Stage stage = new Stage();
    private Controller appController = new Controller(stage);
    private final String diceIdleUrl = "/animation/dice/diceIdle/";
    private Gameplay gameplay = new Gameplay();
    private List<Entity> entities = gameplay.getEntities();
    @FXML
    private ImageView ship;
    @FXML
    private ImageView blueFish;
    private final String blueFishMoveURL = "/animation/fish/move/blueFishMove/";
    private final String blueFishStillURL = "/animation/fish/idle/blueFishStill/";

    @FXML
    private ImageView orangeFish;
    @FXML
    private ImageView pinkFish;
    @FXML
    private ImageView yellowFish;
    @FXML
    private ImageView dice;

    diceSceneController diceSC = new diceSceneController();
    private String resultColor = null;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void initialize() throws IOException, InterruptedException {
        //TODO add background animation
        gameLoop();
    }
    public void gameLoop() throws IOException, InterruptedException {
        while (!gameplay.isGameOver()){
            resultColor = diceSC.getResultColor();
            if (resultColor != null){
                move(resultColor);
                resultColor = null;
            }
        }
    }

    public void move (String color) throws IOException, InterruptedException {
        // move
        for (Entity entity : entities)
        {
            if (entity.getColors().contains(color))
            {
                switch (color){
                    case "Blue":
                        System.out.println("blue is moved");
                        fishMove(blueFishMoveURL,blueFish);
                        TimeUnit.SECONDS.sleep((long)21 * (long)0.2);
                        fishIdle(blueFishStillURL,blueFish);
                        entity.move();
                }

            }
        }
    }

    //FISH SECTION
    public void fishMove (String moveURL,ImageView fish) throws IOException {
        appController.playAnimation(moveURL,21,0.2,fish);
    }
    public void fishIdle (String idleURL, ImageView fish) throws IOException {
        appController.playIdleAnimation(idleURL,12,0.2,fish);
    }

    //SHIP SECTION
    public void shipMove (String moveURL,ImageView ship) throws IOException {
        appController.playAnimation(moveURL,7,0.2,ship);
    }
    public void shipCatch (String catchURL, ImageView ship) throws IOException {
        appController.playAnimation(catchURL,12,0.2,ship);
    }

    //DICE SECTION
    public void dicePressed(MouseEvent event) throws IOException {

        appController.switchToScene("dice");
    }
    public void diceEntered(MouseEvent event) throws IOException {
        System.out.println("dice hovered");
        appController.playAnimation(diceIdleUrl,12, 0.2, dice);
    }
    public void diceExited(MouseEvent event){
        System.out.println("dice exited");
        dice.setImage(new Image(getClass().getResourceAsStream("/image/dice.png")));
    }
}
