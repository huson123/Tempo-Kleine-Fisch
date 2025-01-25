package controller.tkfisch;

import backend.Gameplay;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final Stage primaryStage; // Reference to the primary stage
    private final Map<String, Scene> scenes = new HashMap<>(); // Map for scenes
    private final Map<String, SceneController> sceneControllers = new HashMap<>(); // Map for scene controllers
    private final Gameplay gameplay = new Gameplay();
    private Timeline timeline;

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
            addScene("diceResult", "/scene/diceResultScene.fxml");

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

        // Retrieve the controller from the FXML and store it in sceneControllers
        SceneController controller = loader.getController();
        sceneControllers.put(name, controller);

        // Inject AppController into the scene controller
        controller.setAppController(this);

        scenes.put(name, scene);
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
    }
    public void playAnimation(String imgURL, int frames, int duration){
        //TODO ADD A METHOD TO PLAY ANIMATION FOR IMAGE VIEW OBJ


    }

    // Get a specific scene controller by name
    public SceneController getSceneController(String name) {
        return sceneControllers.get(name);
    }

    public Gameplay getGameplay() {
        return gameplay;
    }
}
