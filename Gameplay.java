import java.util.*; 

public class Gameplay {
    private boolean gameOver = false; // flag 
    
    private static final int SEA = 7; 
    private final Random random; 

    private List<Entity> entities;
    
    private int boatScore; 
    private int fishScore; 

    // Constructor 
    public Gameplay()
    {
        this.entities = new ArrayList<>(); 
        this.random = new Random().setSeed(0); 
        this.boatScore = 0;
        this.fishScore = 0; 

        init(); 
    }

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
        int randomIndex = random.nextInt(entities.size());
        Entity entity = entities.get(randomIndex); 
        entity.move(); 
    }

    
    
}
