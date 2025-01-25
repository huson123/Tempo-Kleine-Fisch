package controller.tkfisch;

import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import backend.Gameplay;

public class selectSceneController implements SceneController {
    private Controller appController;

    @Override
    public void setAppController(Controller appController) {
        this.appController = appController;
    }

    public void fishPressed(MouseEvent event) {
        appController.switchToScene("fishSelect");
        Gameplay gameplay = appController.getGameplay();
        gameplay.setPlayerType("Fish"); // Update backend logic
    }

    public void shipPressed(MouseEvent event) {
        appController.switchToScene("game");
    }

    // Event handlers for mouse hover and exit (ship)
    public void shipEntered(MouseEvent event) {
        System.out.println("Mouse entered the ship image.");
        // Add hover effect logic here, e.g., changing opacity
    }

    public void shipExited(MouseEvent event) {
        System.out.println("Mouse exited the ship image.");
        // Add hover effect logic here, e.g., resetting opacity
    }

    // Event handlers for mouse hover and exit (fish)
    public void fishEntered(MouseEvent event) {
        System.out.println("Mouse entered the fish image.");
        // Add hover effect logic here, e.g., changing opacity
    }

    public void fishExited(MouseEvent event) {
        System.out.println("Mouse exited the fish image.");
        // Add hover effect logic here, e.g., resetting opacity
    }
}
