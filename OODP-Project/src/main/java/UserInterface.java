public interface UserInterface {

	boolean login(int userID, String password);
	void changePassword(String newPassword);

	void logout();

}