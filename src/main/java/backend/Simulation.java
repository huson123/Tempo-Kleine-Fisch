package backend;

import java.util.*;

public class Simulation {
    private boolean gameOver = false;
    private static final int SEA = 6;
    private final Random random;

    private List<Entity> entities;
    private final List<String> diceColor = new ArrayList<>(Arrays.asList("Red", "Green", "Blue",
            "Yellow", "Pink", "Orange"));

    private int turn = 0;

    private int shipScore = 0;
    private int fishScore = 0;

    private int shipWin = 0;
    private int fishWin = 0;
    private int tie = 0;

    // Constructor
    public Simulation() {
        this.random = new Random();
        random.setSeed(25);
    }

    // Getter Methods
    public boolean isGameOver() {
        return this.gameOver;
    }

    public int getShipWin() {
        return this.shipWin;
    }

    public int getFishWin() {
        return this.fishWin;
    }

    public int getTie() {
        return this.tie;
    }

    public int getTurn()
    {
        return this.turn;
    }

    // Setter Methods
    public void setGameOver() {
        this.gameOver = true;
    }

    public void resetGameOver() {
        this.gameOver = false;
    }

    public void addShipScore() {
        this.shipScore++;
    }

    public void addFishScore() {
        this.fishScore++;
    }

    public void resetScores() {
        this.shipScore = 0;
        this.fishScore = 0;
    }

    // Initialize game state
    public void init() {
        entities = new ArrayList<>(); // Reset entity list
        entities.add(new Ship("Ship", new ArrayList<>(Arrays.asList("Red", "Green"))));
        entities.add(new Fish(0, "Fish1", "Blue"));
        entities.add(new Fish(0, "Fish2", "Yellow"));
        entities.add(new Fish(0, "Fish3", "Pink"));
        entities.add(new Fish(0, "Fish4", "Orange"));
    }

    // Simulate a die roll and move the respective entity
    public String rollAndMove() {
        String color = diceColor.get(random.nextInt(diceColor.size()));

        this.turn++;

        for (Entity entity : entities) {
            if (entity.getColors().contains(color)) {
                entity.move();
            }
        }

        return color;
    }

    // Update game state
    public void update() {
        List<Entity> entitiesToRemove = new ArrayList<>();
        Ship ship = null;

        // Find the ship
        for (Entity entity : entities) {
            if (entity.getType() == Entity.Type.SHIP) {
                ship = (Ship) entity;
                break;
            }
        }

        // Process fish movement
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity.getType() == Entity.Type.FISH) {
                if (entity.getPosition() >= SEA) {
                    addFishScore();
                    entitiesToRemove.add(entity);
                } else if (ship != null && entity.getPosition() == ship.getPosition()) {
                    addShipScore();
                    ship.addColor(entity.getColors().get(0));
                    entitiesToRemove.add(entity);
                }
            }
        }

        // Remove fish after iteration
        entities.removeAll(entitiesToRemove);

        // Check for game over
        if (entities.stream().noneMatch(e -> e.getType() == Entity.Type.FISH)) {
            setGameOver();
        }
    }

    // Determine game result
    public void addResult() {
        if (shipScore > fishScore) {
            shipWin++;
        } else if (shipScore < fishScore) {
            fishWin++;
        } else {
            tie++;
        }
    }

    // Main simulation
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        int numGames = 1000000; // Run 1,000,000 games

        for (int i = 0; i < numGames; i++) {
            simulation.init(); // Reset game state for each simulation

            while (!simulation.isGameOver()) {
                simulation.rollAndMove();
                simulation.update();
            }

            simulation.addResult();
            simulation.resetGameOver();
            simulation.resetScores();
        }

        // Print results
        System.out.println("Ship Wins: " + simulation.getShipWin());
        System.out.println("Fish Wins: " + simulation.getFishWin());
        System.out.println("Ties: " + simulation.getTie());

        System.out.println("Average Turn: " + simulation.getTurn() / numGames);
    }
}