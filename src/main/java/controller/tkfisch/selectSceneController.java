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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class selectSceneController {
    Parent root;
    Stage stage;
    Scene scene;
    static private String state;
    @FXML
    private ImageView shipImage;
    @FXML
    private ImageView fishImage;

    private final Image shipReleased = new Image(getClass().getResourceAsStream("/image/shipReleased.jpg"));
    private final Image shipPressed = new Image(getClass().getResourceAsStream("/image/shipPressed.jpg"));

    private final Image fishReleased = new Image(getClass().getResourceAsStream("/image/fishReleased.jpg"));
    private final Image fishPressed = new Image(getClass().getResourceAsStream("/image/fishPressed.jpg"));

    public void switchToGame(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/scene/gameScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public String getState(){
        return state;
    }

    //simulating image as button (ship)
    public void shipPressed(MouseEvent event) throws IOException {
        state = "ship";
        shipImage.setImage(shipPressed);
        System.out.println("ship pressed");
        switchToGame(event);
    }
    public void shipEntered(){
        shipImage.setImage(shipPressed);
        System.out.println("ship hovered");
    }
    public void shipExited(){
        shipImage.setImage(shipReleased);
        System.out.println("ship exited");
    }

    //simulating image as button (fish)
    public void fishPressed(MouseEvent event) throws IOException {
        state = "fish";
        fishImage.setImage(fishPressed);
        System.out.println("fish pressed");
        switchToGame(event);
    }
    public void fishEntered(){
        fishImage.setImage(fishPressed);
        System.out.println("fish hovered");
    }
    public void fishExited(){
        fishImage.setImage(fishReleased);
        System.out.println("fish exited");
    }
}
