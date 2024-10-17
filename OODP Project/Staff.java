import java.util.*;

public class Staff implements UserInterface {

	Collection<Staff> nurses;
	Staff manager;
	private String name;
	private String staffID;
	private String contactInfo;
	private Doctor[] doctors;
	private Pharmacist[] pharmacists;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getStaffID() {
		return this.staffID;
	}

	/**
	 * 
	 * @param staffID
	 */
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getContactInfo() {
		return this.contactInfo;
	}

	/**
	 * 
	 * @param contactInfo
	 */
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * 
	 * @param userID
	 * @param password
	 */
	public void login(string userID, String password) {
		// TODO - implement Staff.login
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement Staff.logout
		throw new UnsupportedOperationException();
	}

	public void manageDoctors() {
		// TODO - implement Staff.manageDoctors
		throw new UnsupportedOperationException();
	}

	public void managePharmacists() {
		// TODO - implement Staff.managePharmacists
		throw new UnsupportedOperationException();
	}

	public void displayStaff() {
		// TODO - implement Staff.displayStaff
		throw new UnsupportedOperationException();
	}

}