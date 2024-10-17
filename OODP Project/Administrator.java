import java.util.List;

public class Administrator extends User{ //implements InventoryManager {

	private Inventory inventory;

    public Administrator(Inventory inventory) {
        this.inventory = inventory;
    }

    public void approveReplenishment(Medicine medicine, int quantity) {
        System.out.println("Replenishment approved for: " + medicine.getName());
        inventory.addMedicine(medicine, quantity);
    }

    public void viewInventory() {
        inventory.displayInventory();
    }

    public void manageStaff(List<Staff> staffList) {
        System.out.println("Managing staff members:");
        for (Staff staff : staffList) {
            System.out.println("Staff: " + staff.getName() + ", Role: " + staff.getClass().getSimpleName());
        }
    }

}