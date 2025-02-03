package model;

import java.util.List;

public abstract class Entity {
    //written by Song Thien Phuc Nguyen
    public enum Type {
        FISH, SHIP
    }

    private Type type;
    private int position;
    private String name;
    protected List<String> colors;

    public Entity(Type type, int position, String name, List<String> colors) {
        //written by Song Thien Phuc Nguyen
        this.type = type;
        this.position = position;
        this.name = name;
        this.colors = colors;
    }

    public Type getType() {
        return this.type;
    }//written by Song Thien Phuc Nguyen

    public int getPosition() {
        return this.position;
    }//written by Song Thien Phuc Nguyen

    public String getName() {
        return this.name;
    }//written by Song Thien Phuc Nguyen

    public List<String> getColors() {
        return this.colors;
    }//written by Song Thien Phuc Nguyen

    public void move() {
        this.position++;
    }//written by Song Thien Phuc Nguyen
}
