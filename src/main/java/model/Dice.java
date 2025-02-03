package model;

import java.util.List;
import java.util.Random;

public class Dice {
    //written by Song Thien Phuc Nguyen
    private List<String> colors;
    private Random random;

    public Dice(List<String> colors) {
        this.colors = colors;
        this.random = new Random();
    }

    public String roll() {
        //written by Song Thien Phuc Nguyen
        int randomIndex = random.nextInt(colors.size());
        return colors.get(randomIndex);
    }
}
