// ValidationUtils.java
public class ValidationUtils {
    // Simple regex for email validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidContactNumber(String contactNumber) {
        return contactNumber.matches("\\d{1,8}");
    }
}
