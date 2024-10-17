import java.util.List;

public class Pharmacist extends User implements InventoryManager {

	private Inventory inventory;

	public Pharmacist(Inventory inventory) {
        this.inventory = inventory;
    }

	public void viewInventory() {
        inventory.displayInventory();
    }

	public void requestReplenishment(Medicine medicine) {
        if (inventory.isLowStock(medicine)) {
            System.out.println("Replenishment requested for: " + medicine.getName());
            // Notify Administrator for replenishment
        } else {
            System.out.println("Stock is sufficient for " + medicine.getName());
        }
    }

	public void updatePrescriptionStatus(Appointment appointment, List<Medicine> dispensedMedicines) {
        System.out.println("Prescription dispensed for appointment ID: " + appointment.getAppointmentID());
        for (Medicine med : dispensedMedicines) {
            inventory.reduceStock(med, 1); // Assume 1 unit per medicine
        }
        generateBill(dispensedMedicines);
    }

	public void generateBill(List<Medicine> medicines) {
        int total = 0;
        for (Medicine medicine : medicines) {
            total += medicine.getPrice();
        }
        System.out.println("Total Bill: $" + total);
    }

	public Medicine getMedicine(String name) {
		// TODO - implement Pharmacist.getMedicine
		throw new UnsupportedOperationException();

	}

	public void generateBill() {
		// TODO - implement Pharmacist.generateBill
		throw new UnsupportedOperationException();
	}

}