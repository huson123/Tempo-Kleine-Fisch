import java.util.*;
import Entity.java;

import javax.swing.text.html.parser.Entity;
public class Gameplay {

    private boolean gameOver = false; // flag 
    
    private static final int SEA = 7; 
    private final Random random; 

    private List<Entity> entities;
    private List<String> diceColor = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Pink", "Orange")); 
    
    private int boatScore; 
    private int fishScore; 

    // Constructor 

    public Gameplay()
    {
        this.entities = new ArrayList<>(); 
        this.random = new Random(); 
        this.boatScore = 0;
        this.fishScore = 0; 

        init(); 
    }

    // Getter 
    public boolean isGameOver()
    {
        return this.gameOver; 
    }

    public int getBoatScore()
    {
        return this.boatScore; 
    } 

    public int getFishScore()
    {
        return this.fishScore; 
    } 

    public List<String> getDiceColor()
    {
        return this.diceColor; 
    }

    // Setter 

    public void setGameOver()
    {
        this.gameOver = true;
    }

    public void addBoatScore()
    {
        this.boatScore++; 
    }

    public void addFishScore()
    {
        this.fishScore++; 
    }

    // Method 
    public void init()
    {
        entities.add(new Ship("Ship", Arrays.asList("Red", "Green"))); 
        entities.add(new Fish("Fish1", "Blue"));
        entities.add(new Fish("Fish2", "Yellow"));
        entities.add(new Fish("Fish3", "Pink"));
        entities.add(new Fish("Fish4", "Orange"));
    }

    public void rollAndMove()
    {
        List<String> colors = getDiceColor();
        
        // roll 
        int randomIndex = random.nextInt(colors.size());
        String color = colors.get(randomIndex); 

        // move
        for (Entity entity : entities)
        {
            if (entity.getColors().contains(color))
            {
                entity.move(); 
            }
        }
    }

    public void update()
    {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext())
        {
            Entity entity = iterator.next();
            
            if (entity.getType() == Entity.Type.FISH)
            {
                if (entity.getPosition() >= SEA)
                {
                    addFishScore(); 
                    iterator.remove(); 
                }
            }
            else if (entity.getType() == Entity.Type.SHIP)
            {
                for (Entity fish: entities)
                {
                    if  ((fish.getType() == Entity.Type.FISH) && (fish.getPosition() == entity.getPosition()))
                    {
                        addBoatScore(); 
                        ((Ship) entity).addColor(fish.getColors().get(0));
                        iterator.remove();  
                    }
                }
            }
            

            if (entities.size() == 1)
            {
                setGameOver(); 
            }
        } 
    }

    public void printResult()
    {
        if (getBoatScore() > getFishScore())
        {
            System.out.println("Boat Wins!");
        }
        else if (getBoatScore() < getFishScore())
        {
            System.out.println("Fish Wins!");
        }
        else
        {
            System.out.println("It's a Tie!");
        }
    } 

    public static void main(String[] args)
    {
        Gameplay game = new Gameplay(); 

        while (!game.isGameOver())
        {
            game.rollAndMove(); 
            game.update(); 
        }

        game.printResult(); 
    }
}
