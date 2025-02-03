package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The Gameplay class manages the main logic of the game, including the state of the game, 
 * the entities involved, and the scores.
 */
public class Gameplay {

    private boolean gameOver = false; // flag 
    private static final int SEA = 6; 
    private final Random random; 
    private List<Entity> entities;
    private List<String> diceColor = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Pink", "Orange"));
    private List<Entity> entitiesToRemove = new ArrayList<>();
    private List<String> caughtFish = new ArrayList<>();
    private List<String> escapedFish = new ArrayList<>();
    private int shipScore;
    private int fishScore;
    private String playerType;

    /**
     * Constructor for the Gameplay class.
     * Initializes the entities, random generator, and scores.
     */
    public Gameplay() {
        this.entities = new ArrayList<>(); 
        this.random = new Random(); 
        this.shipScore = 0;
        this.fishScore = 0; 
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return this.gameOver; 
    }

    /**
     * Gets the current score of the ship.
     * @return the ship's score.
     */
    public int getShipScore() {
        return this.shipScore;
    } 

    /**
     * Gets the current score of the fish.
     * @return the fish's score.
     */
    public int getFishScore() {
        return this.fishScore; 
    } 

    /**
     * Gets the list of dice colors.
     * @return the list of dice colors.
     */
    public List<String> getDiceColor() {
        return this.diceColor;
    }

    /**
     * Gets the list of entities in the game.
     * @return the list of entities.
     */
    public List<Entity> getEntities() {
        return this.entities;
    }

    /**
     * Gets the player type.
     * @return the player type.
     */
    public String getPlayerType() {
        return this.playerType;
    }

    /**
     * Adds a caught fish color to the list.
     * @param color the color of the caught fish.
     */
    public void addCaughtFish(String color) {
        caughtFish.add(color);
    }

    /**
     * Gets the list of caught fish colors.
     * @return the list of caught fish colors.
     */
    public List<String> getCaughtFish() {
        return caughtFish;
    }

    /**
     * Adds an escaped fish color to the list.
     * @param color the color of the escaped fish.
     */
    public void addEscapedFish(String color) {
        escapedFish.add(color);
    }

    /**
     * Gets the list of escaped fish colors.
     * @return the list of escaped fish colors.
     */
    public List<String> getEscapedFish() {
        return escapedFish;
    }

    /**
     * Sets the game over flag to true.
     */
    public void setGameOver() {
        this.gameOver = true;
    }

    /**
     * Increments the ship's score by one.
     */
    public void addShipScore() {
        this.shipScore++;
    }

    /**
     * Increments the fish's score by one.
     */
    public void addFishScore() {
        this.fishScore++; 
    }

    /**
     * Initializes the game by adding initial entities.
     */
    public void init() {
        entities.add(new Ship(-6, "ship", new ArrayList<>(Arrays.asList("Red", "Green"))));
        entities.add(new Fish(0,"blueFish", "Blue"));
        entities.add(new Fish(0,"yellowFish", "Yellow"));
        entities.add(new Fish(0,"pinkFish", "Pink"));
        entities.add(new Fish(0,"orangeFish", "Orange"));
    }

    /**
     * Rolls the dice to get a random color.
     * @return the color obtained from the dice roll.
     */
    public String roll() {
        List<String> colors = getDiceColor();
        int randomIndex = random.nextInt(colors.size());
        return colors.get(randomIndex);
    }

    /**
     * Updates the game state based on collisions between the ship and fish.
     * @param entity the ship entity.
     * @return a list of fish entities that were caught by the ship.
     */
    public List<Entity> collisionUpdate(Entity entity) {
        List<Entity> deletedFish = new ArrayList<>();
        for (Entity fish : entities) {
            if (fish.getType() == Entity.Type.FISH && fish.getPosition() == entity.getPosition()) {
                addShipScore();
                entitiesToRemove.add(fish); // Mark the fish for removal
                deletedFish.add(fish);
            }
        }
        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
        return deletedFish;
    }

    /**
     * Updates the game state when a fish reaches the sea.
     * @param entity the fish entity.
     * @return true if the fish reached the sea, false otherwise.
     */
    public boolean fishEndUpdate(Entity entity) {
        boolean flag = false;
        if (entity.getType() == Entity.Type.FISH) {
            if (entity.getPosition() >= SEA) {
                flag = true;
                addFishScore();
                entitiesToRemove.add(entity); // Mark the fish for removal
            }
        }
        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
        return flag;
    }

    /**
     * Ends the game if no fish are left.
     */
    public void endGameUpdate() {
        if (entities.stream().noneMatch(e -> e.getType() == Entity.Type.FISH)) {
            setGameOver();
        }
    }

    /**
     * Prints the result of the game based on the scores.
     * @return the result of the game as a string.
     */
    public String printResult() {
        if (getPlayerType().equals("Ship")) {
            if (getShipScore() > getFishScore()) {
                return "You Win!";
            } else if (getShipScore() < getFishScore()) {
                return "You Lose!";
            } else {
                return "Tie!";
            }
        } else {
            if (getShipScore() < getFishScore()) {
                return "You Win!";
            } else if (getShipScore() > getFishScore()) {
                return "You Lose!";
            } else {
                return "Tie!";
            }
        }
    }

    /**
     * Sets the player type.
     * @param playerType the type of the player (e.g., "Ship" or "Fish").
     */
    public void setPlayerType(String playerType) {
        this.playerType = playerType;
        System.out.println("Player type set to: " + playerType);
    }
}
