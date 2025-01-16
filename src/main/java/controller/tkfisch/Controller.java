package controller.tkfisch;

import backend.Entity;
import backend.Gameplay;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Gameplay gameplay;

    @FXML
    private Canvas gameBoard;

    @FXML
    private Button rollDiceButton;

    @FXML
    private Button restartButton;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label statusLabel;

    // Initialize the controller and backend logic
    public void initialize() {
        gameplay = new Gameplay(); // Create a new Gameplay instance
        drawGameBoard(); // Initial rendering of the game board
    }

    @FXML
    private void rollDice() {
        gameplay.rollAndMove(); // Execute dice rolling and movement in the backend
        gameplay.update(); // Update the game state
        drawGameBoard(); // Refresh the game board

        // Check if the game is over
        if (gameplay.isGameOver()) {
            displayGameOver();
        }
    }

    @FXML
    private void restartGame() throws IOException {
        // Restart the game logic
        gameplay = new Gameplay();
        drawGameBoard();

        // Reset UI labels
        scoreLabel.setText("Fish: 0 | Boat: 0");
        statusLabel.setText("");
    }

    private void drawGameBoard() {
        GraphicsContext gc = gameBoard.getGraphicsContext2D();
        gc.clearRect(0, 0, gameBoard.getWidth(), gameBoard.getHeight()); // Clear the canvas

        for (Entity entity : gameplay.getEntities()) {
            if (entity.getType() == Entity.Type.FISH) {
                gc.setFill(javafx.scene.paint.Color.BLUE);
                gc.fillOval(entity.getPosition() * 50, 300, 20, 20); // Adjust coordinates as needed
            } else if (entity.getType() == Entity.Type.SHIP) {
                gc.setFill(javafx.scene.paint.Color.RED);
                gc.fillRect(entity.getPosition() * 50, 250, 30, 30); // Adjust coordinates as needed
            }
        }

        // Update the score label
        scoreLabel.setText("Fish: " + gameplay.getFishPoints() + " | Boat: " + gameplay.getBoatPoints());
    }

    private void displayGameOver() {
        // Display the game over message based on the winner
        String winner = gameplay.getFishPoints() > gameplay.getBoatPoints() ? "Fish Win!" : "Boats Win!";
        if (gameplay.getFishPoints() == gameplay.getBoatPoints()) {
            winner = "It's a Tie!";
        }
        statusLabel.setText("Game Over! " + winner);

        // Disable the roll dice button after the game ends
        rollDiceButton.setDisable(true);
    }
}
