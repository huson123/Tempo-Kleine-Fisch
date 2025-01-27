package backend;

import java.util.Arrays;

public class Fish extends Entity {
    private boolean escaped = false;
    private boolean caught = false;
    public Fish(int pos, int xPos, int yPos, String name, String color)
    {
        super(Type.FISH, pos, xPos, yPos,  name, Arrays.asList(color));
    }
    public void setEscaped(boolean state){
        escaped = state;
    }
    public void setCaught(boolean state){
        caught = state;
    }
    public boolean isEscaped(){
        return escaped;
    }
    public boolean isCaught(){
        return caught;
    }
}
