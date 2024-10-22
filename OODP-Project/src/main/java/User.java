import java.util.*;

/**
 * Represents a general user in the system. Doctors, Pharmacists, and Patients extend from this class.
 */
public class User {
    private String password;
    private final int userID;
    private boolean loggedIn;

    // Static list of used userIDs to ensure uniqueness
    private static final List<Integer> usedUserIDs = new ArrayList<>();

    public User(int userID, String password) {
        this.userID = userID;
        this.password = password;
        usedUserIDs.add(userID);
    }

    public User() {

        this.userID = -1;
        this.password = "";
    }


    // Getters
    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    // Setters
    public void setPassword(String password) {
        this.password = password;
    }

    // Login method
    public boolean login(int userID, String password) {
        if (this.userID == userID && Objects.equals(this.password, password)) {
            loggedIn = true;
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Invalid Credentials.");
        return false;
    }

    // Method to change password (only when logged in)
    public void changePassword(String newPassword) {
        if (loggedIn) {
            this.password = newPassword;
            System.out.println("Password successfully changed!");
        } else {
            System.out.println("You must be logged in to change your password.");
        }
    }

    // Logout method
    public void logout() {
        if (loggedIn) {
            loggedIn = false;
            System.out.println("Successfully logged out!");
        } else {
            System.out.println("You are not logged in.");
        }
    }

    // Check if user is logged in
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
