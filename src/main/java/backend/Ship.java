package backend;

import java.util.List;

class Ship extends Entity {
    public Ship(String name, List<String> colors)
    {
        super(Type.SHIP, -6, 80, 198, name, colors);
    }

    public void addColor(String color)
    {
        this.colors.add(color);
    }
}
