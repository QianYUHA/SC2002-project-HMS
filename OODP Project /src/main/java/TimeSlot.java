/**
 * Represents a time slot for a doctor's appointment.
 */
public class TimeSlot {
    private final int startTime;
    private final int endTime;
    private boolean isAvailable;

    public TimeSlot(int startTime, int endTime, boolean isAvailable) {
        if (startTime >= endTime) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    // Getters
    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Set availability for the time slot
    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return startTime + ":00 - " + endTime + ":00 (Available: " + isAvailable + ")";
    }
}
