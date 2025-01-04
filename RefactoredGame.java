import java.util.*;

public class RefactoredGame {
    private static final int FISH_GOAL = 6;
    private static final int BOAT_START = -6;

    public static String rolling(List<String> List) {
        Random rand = new Random();
        int random_Index = rand.nextInt(List.size());
        String result = List.get(random_Index);
        return result;
    }

    public static void update_Fish_Position(Map<String, Integer> f_Position, String rolled_Fish) {
        f_Position.put(rolled_Fish, f_Position.get(rolled_Fish) + 1);
    }

    public static void game_Process(Map<String, Integer> f_Position, List<String> f_List, List<String> b_List, List<String> e_List, int b_Position){
        Iterator<Map.Entry<String, Integer>> iterator = f_Position.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String fish = entry.getKey();
            int position = entry.getValue();

            // Fish reaches the harbor
            if (position == FISH_GOAL) { 
                iterator.remove(); 
                f_List.remove(fish);
                e_List.add(fish);
            } 

            // Fish caught by the boat
            else if (position == b_Position) { 
                iterator.remove(); 
                f_List.remove(fish); 
                b_List.add(fish); 
            }
        }
    }

    public static void determine_Winner(List<String> escape, List<String>boat, int pick) {
        int caught_Fish = boat.size() - 2;
        if (pick == 1) {
            if (escape.size() > caught_Fish)         System.out.println("You Win!");
            else if (escape.size() == caught_Fish)   System.out.println("It's a Tie!");
            else                                     System.out.println("You Lose!");
        }
        else {
            if (escape.size() > caught_Fish)         System.out.println("You Lose!");
            else if (escape.size() == caught_Fish)   System.out.println("It's a Tie!");
            else                                     System.out.println("You Win!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boat_Position = BOAT_START;

        System.out.println("Pick your side (1 for fish and 2 for boat): ");
        int pick = scanner.nextInt();
        scanner.nextLine();

        List<String> full_List = new ArrayList<>(Arrays.asList("green", "red", "yellow", "blue", "pink", "orange"));
        List<String> fish_List = new ArrayList<>(Arrays.asList("yellow", "blue", "pink", "orange"));
        List<String> boat_List = new ArrayList<>(Arrays.asList("green", "red")); 
        List<String> escape_List = new ArrayList<>(Arrays.asList());

        Map<String, Integer> fish_Position = new HashMap<>();
        for (String fish : fish_List) {
            fish_Position.put(fish, 0);
        }

        // Game loop
        while (!fish_List.isEmpty()) 
        {
            String random_Element = rolling(full_List);
            System.out.println("Rolled color: " + random_Element);

            if (boat_List.contains(random_Element))         boat_Position++;
            else if (fish_List.contains(random_Element))    update_Fish_Position(fish_Position, random_Element);
            else {
                if (pick == 2) {
                    String new_Random_Element = rolling(fish_List);
                    update_Fish_Position(fish_Position, new_Random_Element);
                }
                else {
                    System.out.println("Pick a fish: ");
                    String choose = scanner.nextLine();
                    while (!fish_List.contains(choose)){
                        System.out.println("Invalid pick, please pick again: ");
                        choose = scanner.nextLine();
                    }
                    update_Fish_Position(fish_Position, choose);
                }
            }

            game_Process(fish_Position, fish_List, boat_List, escape_List, boat_Position);

            // Print game state
            System.out.println("Fish Positions: " + fish_Position);
            System.out.println("Boat Position: " + boat_Position);
            System.out.println(); 
        }
        determine_Winner(escape_List, boat_List, pick);
        scanner.close();
        System.exit(0);
    }
}
