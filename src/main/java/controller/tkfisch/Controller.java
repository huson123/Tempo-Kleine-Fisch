package controller.tkfisch;

import backend.Gameplay;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final Stage primaryStage; // Reference to the primary stage
    private final Map<String, Scene> scenes = new HashMap<>(); // Map for scenes
    private final Map<String, SceneController> sceneControllers = new HashMap<>(); // Map for scene controllers
    private final Gameplay gameplay = new Gameplay();


    public Controller(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Initialize scenes and load the first one
    public void initApp() {
        try {
            // Load scenes and their controllers
            addScene("start", "/scene/startScene.fxml");
            addScene("select", "/scene/selectScene.fxml");
            addScene("game", "/scene/gameScene.fxml");
            addScene("dice", "/scene/diceScene.fxml");
            addScene("fishSelect", "/scene/fishSelectScene.fxml");

            // Switch to the initial scene
            switchToScene("start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a scene and its controller
    private void addScene(String name, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        System.out.println(scene);

        // Retrieve the controller from the FXML and store it in sceneControllers
        SceneController controller = loader.getController();
        sceneControllers.put(name, controller);
        System.out.println(sceneControllers);

        // Inject AppController into the scene controller
        controller.setAppController(this);

        scenes.put(name, scene);
        System.out.println(name + " added");
    }

    // Switch to a scene by name
    public void switchToScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.out.println("Scene not found: " + name);
        }
        System.out.println("Available scenes: " + scenes.keySet());

        System.out.println("Switching to scene: " + name);
    }
    public Timeline playAnimation(String imgURL, int frames, double duration, ImageView animatingObj)
    throws  IOException{
        //return a timeline to play a single cycle of animation on a given obj
        Timeline timeline = new Timeline();
        for (int i =0; i<= frames; i++){
            Image img;
            if (i < 10){
                img = new Image(getClass().getResourceAsStream(imgURL + "frame000" + i + ".png"));
            }
            else {
                img = new Image(getClass().getResourceAsStream(imgURL + "frame00" + i + ".png"));
            }
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(i * duration),
                    e -> animatingObj.setImage(img));
            timeline.getKeyFrames().add(keyFrame);
            }
        timeline.setCycleCount(1);
        return timeline;
    }
    public Timeline playIdleAnimation(String imgURL, int frames, double duration, ImageView animatingObj)
            throws  IOException{
        //return a timeline to play inf loop of idle animation
        Timeline timeline = new Timeline();
        for (int i =0; i<= frames; i++){
            Image img;
            if (i < 10){
                img = new Image(getClass().getResourceAsStream(imgURL + "frame000" + i + ".png"));
            }
            else {
                img = new Image(getClass().getResourceAsStream(imgURL + "frame00" + i + ".png"));
            }
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(i * duration),
                    e -> animatingObj.setImage(img));
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;

    }
    public Timeline setBackgroundAnimation(String imgURL, int frames, double duration, AnchorPane pane) throws IOException {
        //return a timeline to set background to repeat pics as animation
        Timeline timeline = new Timeline();
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
        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;
    };

    public SequentialTransition setSequenceAnimation(Timeline ani1, Timeline ani2){
        return new SequentialTransition(ani1,ani2);
    }

    // Get a specific scene controller by name
    public SceneController getSceneController(String name) {
        return sceneControllers.get(name);
    }
    public Scene getScene (String name) {return scenes.get(name);}
    public Stage getPrimaryStage(){return this.primaryStage;}

    public Gameplay getGameplay() {
        return gameplay;
    }
}
