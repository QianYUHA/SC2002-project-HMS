import java.util.HashMap;
import java.util.Map;

public class Inventory {

	private Map<Medicine, Integer> stock = new HashMap<>();
    private int lowStockAlert;

	public void addMedicine(Medicine medicine, int quantity) {
        stock.put(medicine, stock.getOrDefault(medicine, 0) + quantity);
    }

	public int getStockLevel(Medicine medicine) {
        return stock.getOrDefault(medicine, 0);
    }

    public void reduceStock(Medicine medicine, int quantity) {
        if (stock.containsKey(medicine)) {
            stock.put(medicine, stock.get(medicine) - quantity);
        }
    }

	public Inventory(int lowStockAlert) {
        this.lowStockAlert = lowStockAlert;
    }

	public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<Medicine, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey().toString() + " - Stock: " + entry.getValue());
        }
    }

	public boolean isLowStock(Medicine medicine) {
        return stock.getOrDefault(medicine, 0) <= lowStockAlert;
    }

    public int getLowStockAlert() {
        return this.lowStockAlert;
    }

    public void setLowStockAlert(int lowStockAlert) {
        this.lowStockAlert = lowStockAlert;
    }

}