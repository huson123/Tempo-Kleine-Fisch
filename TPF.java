import java.util.*;

class Game {
    private static final int FISH_GOAL = 6;
    private static final int BOAT_START = -6;

    private Boat boat;
    private List<Fish> fishes;
    private List<Fish> escapedFishes;
    private int playerChoice;
    //Initialize
    public Game(int playerChoice) {
        this.boat = new Boat(BOAT_START, new ArrayList<>(Arrays.asList("green", "red")));
        this.fishes = new ArrayList<>(Arrays.asList(
            new Fish("yellow"), new Fish("blue"), new Fish("pink"), new Fish("orange")
        ));
        this.escapedFishes = new ArrayList<>();
        this.playerChoice = playerChoice;
    }
    //Gameplay
    public void play() {
        Scanner scanner = new Scanner(System.in);
        List<String> allColors = Arrays.asList("green", "red", "yellow", "blue", "pink", "orange");

        while (!fishes.isEmpty()) {
            String rolledColor = roll(allColors);
            System.out.println("Rolled color: " + rolledColor);

            if (boat.containsColor(rolledColor))    boat.move();

            else {
                Fish rolledFish = getFishByColor(rolledColor);

                if (rolledFish != null)     rolledFish.move();
                else                        handleSpecialRoll(scanner);
            }

            processGameState();
            printGameState();
            /* 
            Map<String, Object> gameState = getGameState(fishes, boat);
            System.out.println("Current Game State: " + gameState);
            System.out.println();
            */
        }

        determineWinner();
        scanner.close();
    }
    //function to roll a dice
    private String roll(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
    //function to get the list of current fish color
    private List<String> getCurrentFishColor(List<Fish> fishes) {
        List<String> fishColors = new ArrayList<>();
            for (Fish fish : fishes) {
                fishColors.add(fish.getColor());
            }
        return fishColors;
    }
    //function to get a fish with appropriate color
    private Fish getFishByColor(String color) {
        return fishes.stream().filter(fish -> fish.getColor().equals(color)).findFirst().orElse(null);
    }
    //function to determine the move of escaped fish
    private void handleSpecialRoll(Scanner scanner) {
        if (playerChoice == 2) {
            String rolledColor = roll(getCurrentFishColor(fishes));
            Fish randomFish = getFishByColor(rolledColor);
            randomFish.move();
        } else {
            System.out.println("Pick a fish: ");
            String chosenColor = scanner.nextLine();
            Fish chosenFish = getFishByColor(chosenColor);

            while (chosenFish == null) {
                System.out.println("Invalid pick, please pick again: ");
                chosenColor = scanner.nextLine();
                chosenFish = getFishByColor(chosenColor);
            }

            chosenFish.move();
        }
    }
    //Game processing
    private void processGameState() {
        Iterator<Fish> iterator = fishes.iterator();

        while (iterator.hasNext()) {
            Fish fish = iterator.next();

            if (fish.getPosition() == FISH_GOAL) {
                escapedFishes.add(fish);
                iterator.remove();
                fishes.remove(fish);
            } else if (fish.getPosition() == boat.getPosition()) {
                boat.UpdateCaughtFish(fish.getColor());
                iterator.remove();
                fishes.remove(fish);
            }
        }
    }
    //function to get game state
    public Map<String, Object> getGameState(List<Fish> fishes, Boat boat) {
        Map<String, Object> gameState = new HashMap<>();
        Map<String, Integer> fishPositions = new HashMap<>();
        for (Fish fish : fishes) {
            fishPositions.put(fish.getColor(), fish.getPosition());
        }
        gameState.put("fish Positions", fishPositions);
        gameState.put("boat Position", boat.getPosition());
        return gameState;
    }
    //function to print game state
    private void printGameState() {
        System.out.println("Fish Positions: ");
        fishes.forEach(fish -> System.out.println(fish));
        System.out.println("Boat Position: " + boat.getPosition());
        System.out.println();
    }
    //function to detemine win or lose
    private void determineWinner() {
        int caughtFishCount = boat.getColors().size() - 2;

        if (playerChoice == 1) {
            if (escapedFishes.size() > caughtFishCount)             System.out.println("You Win!");
            else if (escapedFishes.size() == caughtFishCount)       System.out.println("It's a Tie!");
            else                                                    System.out.println("You Lose!");
        }
        else {
            if (escapedFishes.size() > caughtFishCount)             System.out.println("You Lose!");
            else if (escapedFishes.size() == caughtFishCount)       System.out.println("It's a Tie!");
            else                                                    System.out.println("You Win!");
        }
    }
}

//Fish class
class Fish {
    private String color;
    private int position;

    public Fish(String color) {
        this.color = color;
        this.position = 0;
    }
    //Function to get fish color
    public String getColor() {
        return color;
    }
    //function to get fish position
    public int getPosition() {
        return position;
    }
    //function to update fish position
    public void move() {
        position++;
    }
    //function to print fish current state
    @Override
    public String toString() {
        return color + " at position " + position;
    }
}
//Boat class
class Boat {
    private int position;
    private List<String> colors = new ArrayList<>();

    public Boat(int startPosition, List<String> colors) {
        this.position = startPosition;
        this.colors = colors;
    }
    //function to get boat position
    public int getPosition() {
        return position;
    }
    //function to update boat position
    public void move() {
        position++;
    }
    //function to get colors list
    public List<String> getColors() {
        return this.colors;
    }
    //function to check if there is a color in colors list
    public boolean containsColor(String color) {
        return colors.contains(color);
    }
    //function to update caught fish into boat colors list
    public void UpdateCaughtFish(String color) {
        this.colors.add(color);
    }
}

public class TPF {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pick your side (1 for fish and 2 for boat): ");
        int Choice = scanner.nextInt();

        Game game = new Game(Choice);
        game.play();
        System.exit(0);
    }
}