package model;

import java.util.List;

class Ship extends Entity {
    public Ship(int pos, String name, List<String> colors)
    {
        super(Type.SHIP, pos, name, colors);
    }

    public void addColor(String color)
    {
        this.colors.add(color);
    }
}
