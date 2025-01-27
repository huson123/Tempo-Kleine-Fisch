package backend;

import java.util.*;

public class Gameplay {

    private boolean gameOver = false; // flag 
    
    private static final int SEA = 7; 
    private final Random random; 

    private List<Entity> entities;
    private List<String> diceColor = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Pink", "Orange"));
    private List<Entity> entitiesToRemove = new ArrayList<>();
    private List<String> caughtFish = new ArrayList<>();
    private List<String> escapedFish = new ArrayList<>();

    private int shipScore;
    private int fishScore;

    private String playerType;

    // Constructor 

    public Gameplay()
    {
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

    public String getPlayerType(){return this.playerType;}

    public void addCaughtFish(String color){
        caughtFish.add(color);
    }
    public List<String> getCaughtFish(){
        return caughtFish;
    }
    public void addEscapedFish(String color){
        escapedFish.add(color);
    }
    public List <String> getEscapedFish(){
        return escapedFish;
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
        entities.add(new Ship("ship", new ArrayList<>(Arrays.asList("Red", "Green"))));
        entities.add(new Fish(6,250,216,"blueFish", "Blue"));
        entities.add(new Fish(-4,250,230,"yellowFish", "Yellow"));
        entities.add(new Fish(6,250,244,"pinkFish", "Pink"));
        entities.add(new Fish(0,250,258,"orangeFish", "Orange"));
    }

    public String roll()
    {
        List<String> colors = getDiceColor();
        
        // roll 
        int randomIndex = random.nextInt(colors.size());
        return colors.get(randomIndex);
    }


    public List<Entity> collisionUpdate(Entity entity) {
        //check when ship hit FISH
        //entity is already ship
        //return a list of fish to be deleted from pane
        List<Entity> deletedFish = new ArrayList<>();

                // Check for collisions with FISH
                for (Entity fish : entities) {
                    if (fish.getType() == Entity.Type.FISH && fish.getPosition() == entity.getPosition()) {
                        System.out.println(fish.getPosition());
                        System.out.println(entity.getPosition());
                        addShipScore();
//                        ((Ship) entity).addColor(FISH.getColors().get(0));
                        entitiesToRemove.add(fish); // Mark the FISH for removal
                        deletedFish.add(fish);
                    }
                }
        // Remove all marked entities after the iteration
        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
        return deletedFish;
    }
    public boolean fishEndUpdate(Entity entity){
        boolean flag = false;
        //check for when a FISH reach the sea
        //entity is already fish
            // If the entity is a FISH
            if (entity.getType() == Entity.Type.FISH) {
                if (entity.getPosition() >= SEA) {
                    flag = true;
                    addFishScore();
                    entitiesToRemove.add(entity); // Mark the FISH for removal
                }
            }
            entities.removeAll(entitiesToRemove);
            entitiesToRemove.clear();
            return flag;
    }
    public void endGameUpdate(){
        // End the game if no FISH are left
        if (entities.stream().noneMatch(e -> e.getType() == Entity.Type.FISH)) {
            setGameOver();
        }
    }
    public void printResult()
    {
        if (getShipScore() > getFishScore())
        {
            System.out.println("Boat Wins!");
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
