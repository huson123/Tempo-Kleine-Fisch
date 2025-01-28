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
        Gameplay gameplay = appController.getGameplay();
        gameplay.setPlayerType("Fish"); // Update backend logic
        appController.switchToScene("game");
        //System.out.println("gaming");

    }

    public void shipPressed(MouseEvent event) {
        Gameplay gameplay = appController.getGameplay();
        gameplay.setPlayerType("Ship");
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

    // Event handlers for mouse hover and exit (FISH)
    public void fishEntered(MouseEvent event) {
        System.out.println("Mouse entered the FISH image.");
        // Add hover effect logic here, e.g., changing opacity
    }

    public void fishExited(MouseEvent event) {
        System.out.println("Mouse exited the FISH image.");
        // Add hover effect logic here, e.g., resetting opacity
    }
}
