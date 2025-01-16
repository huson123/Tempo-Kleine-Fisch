package backend;

import java.util.*;

public abstract class Entity {
    public enum Type {
        FISH, SHIP
    }
    private Type type; 

    private int position; 
    private String name; 
    protected List<String> colors; 

    // Constructor 
    public Entity(Type type, int position, String name, List<String> colors)
    {
        this.type = type; 
        this.position = position; 
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
    
    // Method
    public void move()
    {
        this.position++; 
    }
}

class Fish extends Entity {
    public Fish(String name, String color)
    {
        super(Type.FISH, 0, name, Arrays.asList(color)); 
    }
}

class Ship extends Entity {
    public Ship(String name, List<String> colors)
    {
        super(Type.SHIP, -5, name, colors); 
    }

    public void addColor(String color)
    {
        this.colors.add(color);
    }
}