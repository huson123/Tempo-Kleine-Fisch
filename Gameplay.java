package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gameplay {

    private boolean gameOver = false; // flag 
    
    private static final int SEA = 7; 
    private final Random random; 

    private List<Entity> entities;
    private List<String> diceColor = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Pink", "Orange")); 
    ArrayList<String> remainFishColor = new ArrayList<>();
    ArrayList<String> escapedFish = new ArrayList<>();
    
    private int shipScore;
    private int fishScore;

    private String playerType;

    // Constructor 

    public Gameplay()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose your side (Fish/Ship): ");
        this.playerType = input.nextLine();
        this.entities = new ArrayList<>(); 
        this.random = new Random(); 
        this.shipScore = 0;
        this.fishScore = 0; 

        init(); 
    }

    // Getter 
    public boolean isGameOver()
    {
        return this.gameOver; 
    }

    public int getShipScore()
    {
        return this.shipScore;
    } 

    public int getFishScore()
    {
        return this.fishScore; 
    } 

    public List<String> getDiceColor()
    {
        return this.diceColor;
    }

    public List<Entity> getEntities()
    {
        return this.entities;
    }

    // Setter 

    public void setGameOver()
    {
        this.gameOver = true;
    }

    public void addShipScore()
    {
        this.shipScore++;
    }

    public void addFishScore()
    {
        this.fishScore++; 
    }

    // Method 
    public void init()
    {
        entities.add(new Ship("Ship", new ArrayList<>(Arrays.asList("Red", "Green"))));
        entities.add(new Fish("Fish1", "Blue"));
        entities.add(new Fish("Fish2", "Yellow"));
        entities.add(new Fish("Fish3", "Pink"));
        entities.add(new Fish("Fish4", "Orange"));
    }

    public String rollAndMove()
    {
        Scanner input = new Scanner(System.in);
        List<String> colors = getDiceColor();
        
        // roll 
        int randomIndex = random.nextInt(colors.size());
        String color = colors.get(randomIndex); 
        System.out.println(color);

        for (Entity entity : entities) {
            if (entity.getType() == Entity.Type.SHIP && entity.getColors().contains(color)) {
                entity.move();
                break;
            }
            else if (entity.getType() == Entity.Type.FISH && entity.getColors().contains(color)) {
                entity.move();
                break;
            }
            else if (escapedFish.contains(color)) {
                handleEscapedRolled();
                break;
            }
        }
        return color;
    }

    public void handleEscapedRolled() {
        Scanner input = new Scanner(System.in);
        for (Entity entity : entities) {
            if (entity.getType() == Entity.Type.FISH) {
                remainFishColor.add(entity.getColor());
            }
        }
        if (playerType.equals("Ship")){
            int randomIndex = random.nextInt(remainFishColor.size());
            String color = remainFishColor.get(randomIndex); 

            for (Entity entity : entities)
            {
                if (entity.getColors().contains(color))
                {
                    entity.move(); 
                }
            }
        }

        if (playerType.equals("Fish")){
            System.out.println("Choose a Fish to move: " + remainFishColor);
            String color = input.nextLine();
            
            for (Entity entity : entities)
            {
                if (entity.getType() == Entity.Type.FISH && entity.getColors().contains(color)) {
                    entity.move();
                }
            }
            remainFishColor.clear();
        }
    }

    public void update() {
        List<Entity> entitiesToRemove = new ArrayList<>(); // To collect entities marked for removal
    
        // Primary iteration using an Iterator
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
    
            // If the entity is a fish
            if (entity.getType() == Entity.Type.FISH) {
                if (entity.getPosition() >= SEA) {
                    addFishScore();
                    escapedFish.add(entity.getColor());
                    entitiesToRemove.add(entity); // Mark the fish for removal
                }
            }
            // If the entity is a ship
            else if (entity.getType() == Entity.Type.SHIP) {
                // Check for collisions with fish
                for (Entity fish : entities) {
                    if (fish.getType() == Entity.Type.FISH && fish.getPosition() == entity.getPosition()) {
                        addShipScore();
                        ((Ship) entity).addColor(fish.getColors().get(0));
                        entitiesToRemove.add(fish); // Mark the fish for removal
                    }
                }
            }
        }

        for (Entity entity : entities)
        {
            if (entity.getType() == Entity.Type.SHIP)   System.out.println(entity.getType() + ": " + entity.getPosition());
            if (entity.getType() == Entity.Type.FISH)   System.out.println(entity.getColor() + ": " + entity.getPosition());
        }
    
        // Remove all marked entities after the iteration
        entities.removeAll(entitiesToRemove);
    
        // End the game if no fish are left
        if (entities.stream().noneMatch(e -> e.getType() == Entity.Type.FISH)) {
            setGameOver();
        }
    }
    
    public void printResult()
    {
        if (getShipScore() > getFishScore())
        {
            System.out.println("Ship Wins!");
        }
        else if (getShipScore() < getFishScore())
        {
            System.out.println("Fish Wins!");
        }
        else
        {
            System.out.println("It's a Tie!");
        }
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
        System.out.println("Player type set to: " + playerType);
    }
}