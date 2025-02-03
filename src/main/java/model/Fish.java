package model;

import java.util.Arrays;

public class Fish extends Entity {
    private boolean escaped = false;
    private boolean caught = false;

    public Fish(int pos, String name, String color) {
        super(Type.FISH, pos, name, Arrays.asList(color));
    }

    public void setEscaped(boolean state) {
        escaped = state;
    }

    public void setCaught(boolean state) {
        caught = state;
    }

    public boolean isEscaped() {
        return escaped;
    }

    public boolean isCaught() {
        return caught;
    }
}
