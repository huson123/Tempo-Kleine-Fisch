package backend;

import java.util.List;

public abstract class Entity {
    public enum Type {
        FISH, SHIP
    }
    private Type type;
    private int position; 
    private String name; 
    protected List<String> colors;
    private int xPos;
    private int yPos;

    // Constructor 
    public Entity(Type type, int position, int xPos, int yPos, String name, List<String> colors)
    {
        this.type = type; 
        this.position = position;
        this.xPos = xPos;
        this.yPos = yPos;
        this.name = name; 
        this.colors = colors;
    }

    // Getters 
    public Type getType()
    {
        return this.type; 
    } 

    public int getPosition()
    {
        return this.position; 
    }

    public String getName()
    {
        return this.name;  
    }
    
    public List<String> getColors()
    {
        return this.colors; 
    }

    public int getxPos(){return xPos;}

    // Method
    public void move()
    {
        this.position++;
        this.xPos += 32;
    }
}

