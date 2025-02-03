package model;

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Simulation {
    private boolean gameOver = false;
    private int SEA = 6;
    private final Random random;

    private List<Entity> entities;
    private final List<String> diceColor = new ArrayList<>(Arrays.asList("Red", "Green", "Blue",
            "Yellow", "Pink", "Orange"));

    private int turn = 0;

    private int shipScore = 0;
    private int fishScore = 0;
    private int scoreGap = 0;

    private int shipWin = 0;
    private int fishWin = 0;
    private int tie = 0;

    // Constructor
    public Simulation() {
        this.random = new Random();
        random.setSeed(1000);
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

    public int getScoreGap()
    {
        return this.scoreGap;
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
    public void init(int shipPos, int fishPos, int seaPos) {
        entities = new ArrayList<>(); // Reset entity list
        entities.add(new Ship(shipPos, "Ship", new ArrayList<>(Arrays.asList("Red", "Green"))));
        entities.add(new Fish(fishPos, "Fish1", "Blue"));
        entities.add(new Fish(fishPos, "Fish2", "Yellow"));
        entities.add(new Fish(fishPos, "Fish3", "Pink"));
        entities.add(new Fish(fishPos, "Fish4", "Orange"));
        this.SEA = seaPos;
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
        scoreGap += shipScore - fishScore;
    }

    // Main simulation
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        int numGames = 1000000; // Run 1,000,000 games

        String fileName1 = "C:/Users/Admin/Desktop/JAVA/Tempo-Kleine-Fisch/src/main/java/model/result/seed_simulation.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName1))) 
        {
            // Write CSV header
            writer.write("Seed,Ship Wins,Fish Wins,Ties,Average Turns per Game,Average Score Gap");
            writer.newLine();

            for (int seed = 0; seed < 1000; seed=seed+50) { // Loop over different seeds
                simulation = new Simulation();
                simulation.random.setSeed(seed);


                for (int i = 0; i < numGames; i++) {
                    simulation.init(-6, 0, 6); // Reset game state for each simulation

                    while (!simulation.isGameOver()) {
                        simulation.rollAndMove();
                        simulation.update();
                    }

                    simulation.addResult();
                    simulation.resetGameOver();
                    simulation.resetScores();
                }

                writer.write(seed + "," + simulation.getShipWin() + "," + simulation.getFishWin() + "," + simulation.getTie() + "," + (double) simulation.getTurn() / numGames + "," + (double) simulation.getScoreGap() / numGames);
                writer.newLine();
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] shipPositions = {-7, -6, -5};
        int[] fishPositions = {-1, 0, 1};
        int[] seaPositions = {6, 7, 8};

        String fileName2 = "C:/Users/Admin/Desktop/JAVA/Tempo-Kleine-Fisch/src/main/java/model/result/position_simulation.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2))) {
            // Write CSV header
            writer.write("Ship Position, Fish Position, Sea Position, Ship Wins, Fish Wins, Ties, Average Turns, Average Score Gap");
            writer.newLine();

            for (int shipPos : shipPositions) {
                for (int fishPos : fishPositions) {
                    for (int seaPos : seaPositions) {
                        simulation = new Simulation();

                        for (int i = 0; i < numGames; i++) {
                            simulation.init(shipPos, fishPos, seaPos); // Reset game state for each simulation

                            while (!simulation.isGameOver()) {
                                simulation.rollAndMove();
                                simulation.update();
                            }
        
                            simulation.addResult();
                            simulation.resetGameOver();
                            simulation.resetScores();
                            
                        }

                        // Write results to CSV
                        writer.write(shipPos + "," + fishPos + "," + seaPos + "," + simulation.getShipWin() + "," + simulation.getFishWin() + "," + simulation.getTie() + "," + (double) simulation.getTurn() / numGames + "," + (double) simulation.getScoreGap() / numGames);
                        writer.newLine();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }   
}