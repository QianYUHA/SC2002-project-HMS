public interface UserInterface {

	/**
	 * 
	 * @param userID
	 * @param password
	 */
	boolean login(int userID, String password);

	/**
	 * 
	 * @param newPassword
	 */
	void changePassword(String newPassword);

	void logout();

}