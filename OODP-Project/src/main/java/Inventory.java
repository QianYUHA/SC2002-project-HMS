import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<Medicine, Integer> stock = new HashMap<>();  // Medicine stock by quantity
    private int lowStockAlertThreshold;  // Threshold for triggering a low stock alert

    // Constructor
    public Inventory(int lowStockAlertThreshold) {
        this.lowStockAlertThreshold = lowStockAlertThreshold;
    }

    // Add medicine to the inventory
    public void addMedicine(Medicine medicine, int quantity) {
        stock.put(medicine, stock.getOrDefault(medicine, 0) + quantity);
        System.out.println("Added " + quantity + " units of " + medicine.getName() + " to inventory.");
    }

    // Reduce stock after dispensing medicine
    public void reduceStock(Medicine medicine, int quantity) {
        if (stock.containsKey(medicine)) {
            int currentStock = stock.get(medicine);
            if (currentStock >= quantity) {
                stock.put(medicine, currentStock - quantity);
                System.out.println(quantity + " units of " + medicine.getName() + " dispensed.");
            } else {
                System.out.println("Insufficient stock for " + medicine.getName());
            }
        } else {
            System.out.println("Medicine not found in inventory.");
        }
    }

    // Display the current inventory of medicines
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<Medicine, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey().getName() + " - Stock: " + entry.getValue());
        }
    }

    // Check if the stock level of a specific medicine is low
    public boolean isLowStock(Medicine medicine) {
        return stock.getOrDefault(medicine, 0) <= lowStockAlertThreshold;
    }

    // Get stock level for a specific medicine
    public int getStockLevel(Medicine medicine) {
        return stock.getOrDefault(medicine, 0);
    }



}
