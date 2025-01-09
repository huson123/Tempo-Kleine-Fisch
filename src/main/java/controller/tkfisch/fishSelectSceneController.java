package controller.tkfisch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class fishSelectSceneController {
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private ImageView blueFishImage;
    @FXML
    private ImageView pinkFishImage;
    @FXML
    private ImageView yellowFishImage;
    @FXML
    private ImageView orangeFishImage;

    private final Image blueFishPressed = new Image(getClass().getResourceAsStream("/image/blueFishPressed.jpg"));
    private final Image blueFishReleased = new Image(getClass().getResourceAsStream("/image/blueFishReleased.png"));

    private final Image pinkFishPressed = new Image(getClass().getResourceAsStream("/image/pinkFishPressed.jpg"));
    private final Image pinkFishReleased = new Image(getClass().getResourceAsStream("/image/pinkFishReleased.jpg"));

    private final Image yellowFishPressed = new Image(getClass().getResourceAsStream("/image/yellowFishPressed.jpg"));
    private final Image yellowFishReleased = new Image(getClass().getResourceAsStream("/image/yellowFishReleased.jpg"));

    private final Image orangeFishPressed = new Image(getClass().getResourceAsStream("/image/orangeFishPressed.jpg"));
    private final Image orangeFishReleased = new Image(getClass().getResourceAsStream("/image/orangeFishReleased.jpg"));

    public void switchToGame(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/scene/gameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //simulating image as button (blue fish)
    public void blueFishPressed(MouseEvent event) throws IOException {
        blueFishImage.setImage(blueFishPressed);
        System.out.println("blue pressed");
        switchToGame(event);
    }
    public void blueFishEntered(){
        blueFishImage.setImage(blueFishPressed);
        System.out.println("blue hovered");
    }
    public void blueFishExited(){
        blueFishImage.setImage(blueFishReleased);
        System.out.println("blue exited");
    }

    //simulating image as button (pink fish)
    public void pinkFishPressed(MouseEvent event) throws IOException {
        pinkFishImage.setImage(pinkFishPressed);
        System.out.println("pink pressed");
        switchToGame(event);
    }
    public void pinkFishEntered(){
        pinkFishImage.setImage(pinkFishPressed);
        System.out.println("pink hovered");
    }
    public void pinkFishExited(){
        pinkFishImage.setImage(pinkFishReleased);
        System.out.println("pink exited");
    }

    //simulating image as button (orange fish)
    public void orangeFishPressed(MouseEvent event) throws IOException {
        orangeFishImage.setImage(orangeFishPressed);
        System.out.println("orange pressed");
        switchToGame(event);
    }
    public void orangeFishEntered(){
        orangeFishImage.setImage(orangeFishPressed);
        System.out.println("orange hovered");
    }
    public void orangeFishExited(){
        orangeFishImage.setImage(orangeFishReleased);
        System.out.println("orange exited");
    }

    //simulating image as button (yellow fish)
    public void yellowFishPressed(MouseEvent event) throws IOException {
        yellowFishImage.setImage(yellowFishPressed);
        System.out.println("yellow pressed");
        switchToGame(event);
    }
    public void yellowFishEntered(){
        yellowFishImage.setImage(yellowFishPressed);
        System.out.println("yellow hovered");
    }
    public void yellowFishExited(){
        yellowFishImage.setImage(yellowFishReleased);
        System.out.println("yellow exited");
    }
}
