package controller.tkfisch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class gameSceneController {
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    ImageView diceImage;

    @FXML
    AnchorPane pane;

    Timeline timeline = new Timeline();
    String gameBackgroundURL = "/animation/gameBackground/";

    public void initialize() throws IOException {
        addBackgroundToTimeline(gameBackgroundURL,12,0.2);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void addBackgroundToTimeline(String imgURL, int frames, double duration) throws IOException {
        for (int i =0; i<= frames; i++){
            Image img;
            if (i < 10){
                img = new Image(getClass().getResourceAsStream(imgURL + "frame000" + i + ".png"));
            }
            else {
                img = new Image(getClass().getResourceAsStream(imgURL + "frame00" + i + ".png"));
            }
            Background bg = new Background(new BackgroundImage(img, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * duration), // Display each image for x seconds
                    e -> pane.setBackground(bg));
            timeline.getKeyFrames().add(keyFrame);
        }
    };



    public void switchToDice(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/scene/diceScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void dicePressed (MouseEvent event) throws IOException {
        System.out.println("dice pressed");
        switchToDice(event);
    }
}
