import java.util.*; 

public class Game {
    public static void main(String[] args)
    {
        Gameplay game = new Gameplay(); 

        while (!game.isGameOver())
        {
            game.rollAndMove(); 
            game.update(); 
        }

        game.printResult(); 
    }
}