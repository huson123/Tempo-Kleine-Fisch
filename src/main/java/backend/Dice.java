package backend;

import java.util.List;
import java.util.Random;

public class Dice {
    private List<String> colors;
    private Random random;

    public Dice(List<String> colors) {
        this.colors = colors;
        this.random = new Random();
    }

    public String roll() {
        int randomIndex = random.nextInt(colors.size());
        return colors.get(randomIndex);
    }
}
