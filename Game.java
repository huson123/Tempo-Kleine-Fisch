import java.util.*;

public class Game {
    public static void main(String[] args) {
        int boatPosition = -5;

        Map<String, Integer> fishPosition = new HashMap<>();
        fishPosition.put("yellowFish", 0);
        fishPosition.put("blueFish", 0);
        fishPosition.put("pinkFish", 0);
        fishPosition.put("orangeFish", 0);

        Random rand = new Random();
        List<String> fullList = new ArrayList<>(Arrays.asList("greenMan", "redMan", "yellowFish", "blueFish", "pinkFish", "orangeFish"));
        List<String> fishList = new ArrayList<>(Arrays.asList("yellowFish", "blueFish", "pinkFish", "orangeFish"));
        List<String> boatList = new ArrayList<>(Arrays.asList("greenMan", "redMan")); 
        List<String> escapeList = new ArrayList<>(Arrays.asList());

        // Game loop
        while (!fishList.isEmpty()) 
        {
            int randomIndex = rand.nextInt(fullList.size());
            String randomElement = fullList.get(randomIndex);

            if (boatList.contains(randomElement)) 
            {
                boatPosition++;
            }
            else if (fishList.contains(randomElement)) 
            {
                fishPosition.put(randomElement, fishPosition.get(randomElement) + 1);
            } 
            else 
            {
                int newRandomIndex = rand.nextInt(fishList.size());
                String newRandomElement = fishList.get(newRandomIndex);

                fishPosition.put(newRandomElement, fishPosition.get(newRandomElement) + 1);
            }

            // Use Iterator to safely modify the map
            Iterator<Map.Entry<String, Integer>> iterator = fishPosition.entrySet().iterator();
            while (iterator.hasNext()) 
            {
                Map.Entry<String, Integer> entry = iterator.next();
                String fish = entry.getKey();
                int position = entry.getValue();

                if (position == 7) 
                { // Fish reaches the harbor
                    iterator.remove(); // Safely remove using Iterator
                    fishList.remove(fish);
                    escapeList.add(fish);
                } 
                
                else if (position == boatPosition) 
                { // Fish caught by the boat
                    iterator.remove(); // Safely remove using Iterator
                    fishList.remove(fish); 
                    boatList.add(fish); 
                }
            }

            // Print game state
            System.out.println("Fish Positions: " + fishPosition);
            System.out.println("Boat Position: " + boatPosition);
            System.out.println("Random Element: " + randomElement);
            System.out.println("Fish List: " + fishList); 
            System.out.println("Boat List: " + boatList); 
            System.out.println("Escape List: " + escapeList);
            System.out.println(); 
        }

        // Determine the winner
        if (escapeList.size() > (boatList.size() - 2)) 
        {
            System.out.println("Fish Wins!");
        } 
        else if (escapeList.size() == (boatList.size() - 2)) 
        {
            System.out.println("It's a Tie!");
        } 
        else 
        {
            System.out.println("Boat Wins!");
        }
    }
}
