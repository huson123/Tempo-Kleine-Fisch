package view;

import java.io.IOException;

import controller.Controller;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class resultTSceneController implements SceneController {
    private Controller appController;

    @FXML
    AnchorPane pane;
    private String bg = "/animation/background/start/";

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    @FXML
    public void init() throws IOException {
        Timeline tl = new Timeline();
        tl = appController.setBackgroundAnimation(bg, 12, 0.2, pane);
        tl.play();
    }

    public void Exit(ActionEvent event) {
        System.exit(0);
    }

}