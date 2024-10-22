import java.util.ArrayList;
import java.util.List;

public class Administrator extends User{ //implements InventoryManager {

	private final Inventory inventory;
    private List<Staff> staffList = new ArrayList<>();
    private String role;

    public Administrator(Inventory inventory) {
        super();
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

    public void addStaff(Staff newStaff) {
        staffList.add(newStaff);
        System.out.println("Staff member added: " + newStaff.getName());
    }

    public void removeStaff(Staff staff) {
        staffList.remove(staff);
        System.out.println("Staff member removed: " + staff.getName());
    }

    public void updateStaff(Staff staff, String name, String role) {
        staff.setName(name);
        // Assuming you implement a method to set the role in the Staff class
        staff.setRole(role);
        System.out.println("Staff member updated: " + name);
    }
    public void setRole(String role) {
        this.role = role;
    }

//    public void approveReplenishment(Medicine medicine, int quantity) {
//        System.out.println("Replenishment approved for: " + medicine.getName());
//        inventory.addMedicine(medicine, quantity);
//        inventory.displayInventory();  // Display updated inventory after adding
//    }



}