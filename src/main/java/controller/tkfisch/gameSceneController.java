package controller.tkfisch;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class gameSceneController implements SceneController {
    private Stage stage = new Stage();
    private Controller appController = new Controller(stage);
    private String diceIdleUrl = "/animation/dice/diceIdle/";
    @FXML
    private ImageView ship;
    @FXML
    private ImageView blueFish;
    @FXML
    private ImageView orangeFish;
    @FXML
    private ImageView pinkFish;
    @FXML
    private ImageView yellowFish;
    @FXML
    private ImageView dice;

    diceSceneController diceSC = new diceSceneController();
    private String resultColor = diceSC.getResultColor();

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void initialize(){
        //TODO add background animation
    }

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
