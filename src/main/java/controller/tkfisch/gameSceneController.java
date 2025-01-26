package controller.tkfisch;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class gameSceneController implements SceneController {
    private Controller appController;
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

    diceSceneController diceSC = new diceSceneController();
    private String resultColor = diceSC.getResultColor(); ;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void initialize(){
        //TODO add background animation
    }

    public void dicePressed(MouseEvent event) {
        appController.switchToScene("dice");
    }
}
