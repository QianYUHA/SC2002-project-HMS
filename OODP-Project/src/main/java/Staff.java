import java.util.*;

public class Staff implements UserInterface {

	Collection<Staff> nurses;
	Staff manager;
	private String name;
	private String staffID;
	private String contactInfo;
	private Doctor[] doctors;
	private Pharmacist[] pharmacists;
	private String role;

	public String getRole(String role) {
		return this.role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	// Getter and Setter for Staff fields
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStaffID() {
		return this.staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getContactInfo() {
		return this.contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	// Login Method
	public void login(String userID, String password) {
		if (this.staffID.equals(userID) && password.equals("defaultPassword")) {
			System.out.println("Login successful for staff: " + this.name);
		} else {
			System.out.println("Login failed: Invalid credentials for staff: " + this.name);
		}
	}

	@Override
	public boolean login(int userID, String password) {
		return false;
	}

	@Override
	public void changePassword(String newPassword) {

	}

	// Logout Method
	public void logout() {
		System.out.println("Logout successful for staff: " + this.name);
	}

	// Manage Doctors
	public void manageDoctors() {
		System.out.println("Managing Doctors:");
		if (doctors != null && doctors.length > 0) {
			for (Doctor doctor : doctors) {
				System.out.println("Doctor: " + doctor.getName() + ", Specialization: " + doctor.getSpecialisation());
			}
		} else {
			System.out.println("No doctors to manage.");
		}
	}

	// Manage Pharmacists
	public void managePharmacists() {
		System.out.println("Managing Pharmacists:");
		if (pharmacists != null && pharmacists.length > 0) {
			for (Pharmacist pharmacist : pharmacists) {
				System.out.println("Pharmacist: " + pharmacist.getUserID());
			}
		} else {
			System.out.println("No pharmacists to manage.");
		}
	}

	// Display all staff (nurses, doctors, pharmacists)
	public void displayStaff() {
		System.out.println("Displaying all staff:");

		// Display Nurses
		System.out.println("Nurses:");
		if (nurses != null && !nurses.isEmpty()) {
			for (Staff nurse : nurses) {
				System.out.println("Nurse: " + nurse.getName() + ", Staff ID: " + nurse.getStaffID());
			}
		} else {
			System.out.println("No nurses to display.");
		}

		// Display Doctors
		System.out.println("Doctors:");
		if (doctors != null && doctors.length > 0) {
			for (Doctor doctor : doctors) {
				System.out.println("Doctor: " + doctor.getName() + ", Specialization: " + doctor.getSpecialisation());
			}
		} else {
			System.out.println("No doctors to display.");
		}

		// Display Pharmacists
		System.out.println("Pharmacists:");
		if (pharmacists != null && pharmacists.length > 0) {
			for (Pharmacist pharmacist : pharmacists) {
				System.out.println("Pharmacist: " + pharmacist.getUserID());
			}
		} else {
			System.out.println("No pharmacists to display.");
		}
	}
}
