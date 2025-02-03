package model;

import java.util.List;

public abstract class Entity {
    public enum Type {
        FISH, SHIP
    }

    private Type type;
    private int position;
    private String name;
    protected List<String> colors;

    public Entity(Type type, int position, String name, List<String> colors) {
        this.type = type;
        this.position = position;
        this.name = name;
        this.colors = colors;
    }

    public Type getType() {
        return this.type;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getColors() {
        return this.colors;
    }

    public void move() {
        this.position++;
    }
}
