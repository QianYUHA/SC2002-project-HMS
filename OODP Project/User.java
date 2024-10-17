import java.util.*;

public class User implements UserInterface {

	private String password;
	private int userID;
	private boolean login;

	private static List<String> userIDs = new ArrayList<>();


	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		// TODO - implement User.setPassword
		//throw new UnsupportedOperationException();
		this.password = password;
	}

	/**
	 * 
	 * @param userID
	 * @param password
	 */
	public boolean login(int userID, String password) {
    // Use Objects.equals to compare password safely
    	if (this.userID == userID && Objects.equals(this.password, password)) {
        	login = true;
        	System.out.println("Successful Login!");
        	return true;
    	}
    	System.out.println("Invalid Credentials.");
    	return false;
	}


	/**
	 * 
	 * @param newPassword
	 */
	public void changePassword(String newPassword) {
		// TODO - implement User.changePassword
		//throw new UnsupportedOperationException();

		// what if the person forgot password then can't login 

		Scanner sc = new Scanner(System.in);
		String u = sc.nextLine();
		String p = sc.next();
		login = login(u, p);
		if(login){
			this.password = newPassword;
			System.out.println("Successfully Changed Password!");
		}
	}

	public String getUserID() {
		// TODO - implement User.getUserID
		//throw new UnsupportedOperationException();
		return this.userID;
	}

	/**
	 * 
	 * @param userID
	 */
	public void setUserID(String userID) {
		// TODO - implement User.setUserID
		//throw new UnsupportedOperationException();
		this.userID = userID;
		userIDs.add(userID);
	}

	public void logout() {
		// TODO - implement User.logout
		//throw new UnsupportedOperationException();
		login = false; 
		System.out.println("Successfully Logged out!");

	}

}