package backend;

public class Game {
    public static void main(String[] args)
    {
        Gameplay game = new Gameplay();

        while (!game.isGameOver())
        {
            game.roll();
        }

        game.printResult(); 
    }
}