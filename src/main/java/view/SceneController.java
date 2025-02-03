package view;

import controller.Controller;

// Interface for scene controllers to interact with the AppController
public interface SceneController {
    // Method to set the centralized controller
    void setAppController(Controller appController);
}
