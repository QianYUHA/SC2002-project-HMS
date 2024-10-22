import java.util.List;

/**
 * Represents a Pharmacist who handles medicine dispensing and inventory management.
 */
public class Pharmacist extends User implements InventoryManager {

    private Inventory inventory;

    public Pharmacist(int userID, String password, Inventory inventory) {
        super(userID, password);
        this.inventory = inventory;
    }

    // View the inventory of medicines
    public void viewInventory() {
        inventory.displayInventory();
    }

    // Dispense medicines based on the latest appointment of the patient
    public void dispenseMedicines(Appointment appointment) {
        if (appointment != null && appointment.getPrescribedMedicines() != null) {
            System.out.println("Dispensing medicines for patient: " + appointment.getPatient().getName());

            List<Medicine> prescribedMedicines = appointment.getPrescribedMedicines();
            for (Medicine medicine : prescribedMedicines) {
                if (inventory.getStockLevel(medicine) > 0) {
                    inventory.reduceStock(medicine, 1);  // Assume 1 unit per medicine
                    System.out.println("Dispensed: " + medicine.getName());
                } else {
                    System.out.println("Out of stock: " + medicine.getName());
                }
            }
            // After successfully dispensing medicines, update the prescription status to "Dispatched"
            appointment.setPrescriptionStatus("Dispatched");
            System.out.println("Medicines have been dispatched for appointment ID: " + appointment.getAppointmentID());
        } else {
            System.out.println("No valid medicines prescribed for this appointment.");
        }
    }

    // Request replenishment of low stock items
    public void requestReplenishment(Medicine medicine) {
        if (inventory.isLowStock(medicine)) {
            System.out.println("Replenishment requested for: " + medicine.getName());
            // Logic to notify the administrator can be added here
        } else {
            System.out.println("Stock is sufficient for " + medicine.getName());
        }
    }

    // Generate bill based on the prescribed medicines for the patient
    public int generatePharmacistBill(Appointment appointment) {
        int total = 0;
        List<Medicine> prescribedMedicines = appointment.getPrescribedMedicines();
        if (prescribedMedicines != null) {
            for (Medicine medicine : prescribedMedicines) {
                total += medicine.getPrice();
            }
        }
        System.out.println("Total Bill for Medicines: $" + total);
        return total;
    }
}
