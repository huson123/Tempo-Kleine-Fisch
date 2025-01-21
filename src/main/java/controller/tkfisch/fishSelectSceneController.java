package controller.tkfisch;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class fishSelectSceneController implements SceneController {
    private Controller appController;

    @FXML
    private ImageView blueFishImage, pinkFishImage, yellowFishImage, orangeFishImage;

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

    public void switchToGame(MouseEvent event) {
        try {
            appController.switchToScene("game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void blueFishPressed(MouseEvent event) {
        blueFishImage.setImage(blueFishPressed);
        switchToGame(event);
    }

    public void blueFishEntered() {
        blueFishImage.setImage(blueFishPressed);
    }

    public void blueFishExited() {
        blueFishImage.setImage(blueFishReleased);
    }

    public void pinkFishPressed(MouseEvent event) {
        pinkFishImage.setImage(pinkFishPressed);
        switchToGame(event);
    }

    public void pinkFishEntered() {
        pinkFishImage.setImage(pinkFishPressed);
    }

    public void pinkFishExited() {
        pinkFishImage.setImage(pinkFishReleased);
    }

    public void orangeFishPressed(MouseEvent event) {
        orangeFishImage.setImage(orangeFishPressed);
        switchToGame(event);
    }

    public void orangeFishEntered() {
        orangeFishImage.setImage(orangeFishPressed);
    }

    public void orangeFishExited() {
        orangeFishImage.setImage(orangeFishReleased);
    }

    public void yellowFishPressed(MouseEvent event) {
        yellowFishImage.setImage(yellowFishPressed);
        switchToGame(event);
    }

    public void yellowFishEntered() {
        yellowFishImage.setImage(yellowFishPressed);
    }

    public void yellowFishExited() {
        yellowFishImage.setImage(yellowFishReleased);
    }
}
