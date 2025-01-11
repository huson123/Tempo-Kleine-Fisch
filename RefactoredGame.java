import java.util.*;

public class RefactoredGame {
    private static final int FISH_GOAL = 6;
    private static final int BOAT_START = -6;

    public static String rolling(List<String> list) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(list.size());
        String result = list.get(randomIndex);
        return result;
    }

    public static void updateFishPosition(Map<String, Integer> fPosition, String rolledFish) {
        fPosition.put(rolledFish, fPosition.get(rolledFish) + 1);
    }

    public static void gameProcess(Map<String, Integer> fPosition, List<String> fList, List<String> bList, List<String> eList, int bPosition){
        Iterator<Map.Entry<String, Integer>> iterator = fPosition.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String fish = entry.getKey();
            int position = entry.getValue();

            // Fish reaches the harbor
            if (position == FISH_GOAL) { 
                iterator.remove(); 
                fList.remove(fish);
                eList.add(fish);
            } 

            // Fish caught by the boat
            else if (position == bPosition) { 
                iterator.remove(); 
                fList.remove(fish); 
                bList.add(fish); 
            }
        }
    }

    public static void determineWinner(List<String> escape, List<String>boat, int pick) {
        int caughtFish = boat.size() - 2;
        if (pick == 1) {
            if (escape.size() > caughtFish)         System.out.println("You Win!");
            else if (escape.size() == caughtFish)   System.out.println("It's a Tie!");
            else                                     System.out.println("You Lose!");
        }
        else {
            if (escape.size() > caughtFish)         System.out.println("You Lose!");
            else if (escape.size() == caughtFish)   System.out.println("It's a Tie!");
            else                                     System.out.println("You Win!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boatPosition = BOAT_START;

        System.out.println("Pick your side (1 for fish and 2 for boat): ");
        int pick = scanner.nextInt();
        scanner.nextLine();

        List<String> fullList = new ArrayList<>(Arrays.asList("green", "red", "yellow", "blue", "pink", "orange"));
        List<String> fishList = new ArrayList<>(Arrays.asList("yellow", "blue", "pink", "orange"));
        List<String> boatList = new ArrayList<>(Arrays.asList("green", "red")); 
        List<String> escapeList = new ArrayList<>(Arrays.asList());

        Map<String, Integer> fishPosition = new HashMap<>();
        for (String fish : fishList) {
            fishPosition.put(fish, 0);
        }

        // Game loop
        while (!fishList.isEmpty()) 
        {
            String randomElement = rolling(full_List);
            System.out.println("Rolled color: " + randomElement);

            if (boat_List.contains(randomElement))         boatPosition++;
            else if (fish_List.contains(randomElement))    updateFishPosition(fishPosition, randomElement);
            else {
                if (pick == 2) {
                    String newRandomElement = rolling(fishList);
                    update_Fish_Position(fishPosition, newRandomElement);
                }
                else {
                    System.out.println("Pick a fish: ");
                    String choose = scanner.nextLine();
                    while (!fishList.contains(choose)){
                        System.out.println("Invalid pick, please pick again: ");
                        choose = scanner.nextLine();
                    }
                    update_Fish_Position(fishPosition, choose);
                }
            }

            game_Process(fishPosition, fishList, boatList, escapeList, boatPosition);

            // Print game state
            System.out.println("Fish Positions: " + fishPosition);
            System.out.println("Boat Position: " + boatPosition);
            System.out.println(); 
        }
        determine_Winner(escapeList, boatList, pick);
        scanner.close();
        System.exit(0);
    }
}
