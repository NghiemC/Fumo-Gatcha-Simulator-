//Fumo Gacha Simulator
//Members :Charlie Nghiem

import java.util.ArrayList; // Import ArrayList class
import java.util.Random; // Import Random class
import javax.swing.JOptionPane; // Import JOptionPane class for GUI input/output

class Gacha {
    private final String name; // Declare private member variable name of type String
    private final String rarity; // Declare private member variable rarity of type String

    public Gacha(String name, String rarity) { // Constructor with parameters name and rarity
        this.name = name; // Assign value to name variable
        this.rarity = rarity; // Assign value to rarity variable
    }

    public String ItemName() { // Method to get item name
        return name; // Return item name
    }

    public String getRarity() { // Method to get rarity
        return rarity; // Return rarity
    }
}

class GachaSystem {
    private final ArrayList<Gacha> gachaPool; // Declare private member variable gachaPool of type ArrayList storing Gacha objects
    private int totalPullCount; // Declare private member variable totalPullCount of type int
    private int money; // Declare private member variable money of type int
    private final Random random; // Declare private member variable random of type Random for random number generation
    private int pity4; // Declare private member variable pity4 of type int
    private int pity5; // Declare private member variable pity5 of type int
    private final int GUARANTEED_SR_PULLS = 10; // Declare constant GUARANTEED_SR_PULLS with value 10
    private final int GUARANTEED_SRR_PULLS = 60; // Declare constant GUARANTEED_SRR_PULLS with value 60
    private final int MAX_ADD_AMOUNT = 10; // Declare constant MAX_ADD_AMOUNT with value 10 (maximum amount that can be added)

    public GachaSystem() { // Constructor for GachaSystem class
        gachaPool = new ArrayList<>(); // Initialize gachaPool as a new ArrayList
        gachaPool.add(new Gacha("Fumbo", "N")); // Add Gacha object with name "Fumbo" and rarity "N" to gachaPool
        gachaPool.add(new Gacha("Mass produced Bootleg Fumo", "R")); // Add Gacha object with name "Mass produced Bootleg Fumo" and rarity "R" to gachaPool
        gachaPool.add(new Gacha("Decent Custom Fumo", "SR")); // Add Gacha object with name "Decent Custom Fumo" and rarity "SR" to gachaPool
        gachaPool.add(new Gacha("A Real Genuine Fumo", "SSR")); // Add Gacha object with name "A Real Genuine Fumo" and rarity "SSR" to gachaPool

        totalPullCount = 0; // Initialize totalPullCount to 0
        money = 0; // Initialize money to 0
        random = new Random(); // Initialize random using Random class
        pity4 = 0; // Initialize pity4 to 0
        pity5 = 0; // Initialize pity5 to 0
    }

    public void addMoney(int amount) { // Method to add money to balance
        if (amount > MAX_ADD_AMOUNT) { // Check if amount is greater than MAX_ADD_AMOUNT
            JOptionPane.showMessageDialog(null, "Maximum amount that can be added is $10."); // Display message if amount is too large
            return; // Exit method
        }
        money += amount; // Add amount to money
        totalPullCount += amount; // Add amount to totalPullCount (each $1 adds one pull)
        totalPullCount = Math.min(totalPullCount, 10); // Limit totalPullCount to a maximum of 10 pulls
    }

    public String pullItem() { // Method to pull an item from the gacha pool
        if (totalPullCount > 0) { // Check if there are pulls left
            totalPullCount--; // Decrease totalPullCount
            pity4++; // Increase pity4
            pity5++; // Increase pity5

            if (totalPullCount % 10 == 0) { // Check if 10th pull
                pity4 = 0; // Reset SR counter
                return "You pulled: " + getGacha("SR").ItemName() + " (Rarity: Super Rare!)"; // Return pulled SR item
            } else if (totalPullCount % 60 == 0) { // Check if 60th pull
                pity5 = 0; // Reset SSR counter
                return "You pulled: " + getGacha("SSR").ItemName() + " (Rarity: Super Special Rare!)"; // Return pulled SSR item
            }

            double roll = random.nextDouble() * 100; // Generate random number between 0 and 100 for rarity selection
            if (roll < 0.6) {
                pity5 = 0; // Reset SSR counter
                return "You pulled: " + getGacha("SSR").ItemName() + " (Rarity: Super Special Rare!)"; // Return pulled SSR item
            } else if (roll < 5.1) {
                pity4 = 0; // Reset SR counter
                return "You pulled: " + getGacha("SR").ItemName() + " (Rarity: Super Rare!)"; // Return pulled SR item
            } else if (roll < 50) {
                return "You pulled: " + getGacha("R").ItemName() + " (Rarity: Rare!)"; // Return pulled R item
            } else {
                return "You pulled: " + getGacha("N").ItemName() + " (Rarity: Normal!)"; // Return pulled N item
            }
        } else {
            return "No pulls left. Add more money to pull."; // Return message if no pulls left
        }
    }

    public boolean isGuaranteedSR() { // Method to check if SR is guaranteed
        return pity4 >= GUARANTEED_SR_PULLS && (totalPullCount > 0 || pity5 < GUARANTEED_SRR_PULLS); // Check if SR is guaranteed
    }

    public boolean isGuaranteedSRR() { // Method to check if SSR is guaranteed
        return pity5 >= GUARANTEED_SRR_PULLS; // Check if SSR is guaranteed
    }

    public String pullUntilRarity(String targetRarity, int remainingPulls) { // Method to pull until a specific rarity is obtained
        if (remainingPulls <= 0) { // Check if no remaining pulls
            return "You have zero pulls left."; // Return message
        }

        String result = pullItem(); // Pull an item
        if (!result.contains(targetRarity)) { // Check if pulled item is not the target rarity
            result += "\n" + pullUntilRarity(targetRarity, remainingPulls - 1); // Pull again recursively until target rarity is obtained
        }
        return result; // Return result
    }

    private Gacha getGacha(String rarity) { // Method to get Gacha object by rarity
        for (Gacha item : gachaPool) { // Loop through gachaPool
            if (item.getRarity().equals(rarity)) { // Check if item rarity matches input rarity
                return item; // Return matching item
            }
        }
        return null; // Return null if no matching item found
    }

    public int getMoney() { // Method to get current balance
        return money; // Return money
    }

    public int getTotalPullCount() { // Method to get total pulls
        return totalPullCount; // Return totalPullCount
    }
}

public class Main { // Main class
    public static void main(String[] args) { // Main method
        GachaSystem gacha = new GachaSystem(); // Create new GachaSystem object
        int totalPulls = 0; // Initialize totalPulls to 0

        while (true) { // Infinite loop
            try {
                int dollars = Integer.parseInt(JOptionPane.showInputDialog("Enter $1 to $10 to add to your balance (or 0 to stop):")); // Get input from user
                if (dollars < 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a positive integer."); // Display message for negative input
                    continue; // Restart loop
                }
                if (dollars == 0) {
                    break; // Exit loop if 0 is entered
                }
                gacha.addMoney(dollars); // Add money to balance
                totalPulls += dollars; // Update total pulls
                
                // Display total pulls during this loop iteration
                JOptionPane.showMessageDialog(null, "Total pulls during this session: " + totalPulls);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer amount."); // Display message for invalid input
            }

            JOptionPane.showMessageDialog(null, "Your money has turned into the amount of pulls you can pull for. You have " + gacha.getTotalPullCount() + " pulls left."); // Display message with remaining pulls
            int choice = JOptionPane.showConfirmDialog(null, "Would you like to pull from the gacha pool?"); // Ask user to pull from gacha pool
            if (choice == JOptionPane.YES_OPTION) {
                int pulls = gacha.getTotalPullCount(); // Get total pulls
                String pulledItem = gacha.pullUntilRarity("Decent Custom Fumo", pulls); // Pull until a specific rarity is obtained
                JOptionPane.showMessageDialog(null, pulledItem); // Display pulled item

                if (gacha.isGuaranteedSR()) // Check if SR is guaranteed
                {
                    JOptionPane.showMessageDialog(null, "Congratulations! You are guaranteed to get a SR in the next pull."); // Display message for guaranteed SR
                }

                if (gacha.isGuaranteedSRR()) // Check if SSR is guaranteed
                {
                    JOptionPane.showMessageDialog(null, "Congratulations! You are guaranteed to get a SRR in the next pull."); // Display message for guaranteed SSR
                }
            } else {
                JOptionPane.showMessageDialog(null, "No items pulled."); // Display message if no items pulled
            }
        }

        // Show total pulls after exiting the loop
        JOptionPane.showMessageDialog(null, "Total pulls during this session: " + totalPulls);
    }
}
