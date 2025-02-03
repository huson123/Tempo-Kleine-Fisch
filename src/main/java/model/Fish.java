package model;

import java.util.Arrays;

public class Fish extends Entity {
    //written by Song Thien Phuc Nguyen
    private boolean escaped = false;
    private boolean caught = false;

    public Fish(int pos, String name, String color) {
        super(Type.FISH, pos, name, Arrays.asList(color));
    }//written by Song Thien Phuc Nguyen

    public void setEscaped(boolean state) {
        escaped = state;
    }//written by Song Thien Phuc Nguyen

    public void setCaught(boolean state) {
        caught = state;
    }//written by Song Thien Phuc Nguyen

    public boolean isEscaped() {
        return escaped;
    }//written by Song Thien Phuc Nguyen

    public boolean isCaught() {
        return caught;
    }//written by Song Thien Phuc Nguyen
}
