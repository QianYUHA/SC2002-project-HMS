import java.util.ArrayList;
import java.util.List;

// Assuming the missing classes (User, TimeSlot, Appointment, Medicine, Patient) are in the same package.
// If they are in different packages, adjust the imports accordingly.

/**
 * Represents a Doctor with a specialization, consultation fee, and available time slots.
 */
public class Doctor extends User {
    private final String name;
    private final String specialization;
    private final int consultationFee; // Fee based on specialization
    private final List<TimeSlot> timeSlots; // Available time slots
    private final List<Appointment> upcomingAppointments; // List of upcoming appointments

    // Constructor
    public Doctor(int userID, String password, String name, String specialization, int consultationFee) {
        super(userID, password);
        this.name = name;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.timeSlots = new ArrayList<>();
        this.upcomingAppointments = new ArrayList<>();
        initializeTimeSlots();
    }

    // Initialize daily time slots for the doctor
    private void initializeTimeSlots() {
        timeSlots.add(new TimeSlot(7, 8, true));
        timeSlots.add(new TimeSlot(8, 9, true));
        timeSlots.add(new TimeSlot(9, 10, true));
        timeSlots.add(new TimeSlot(10, 11, true));
        timeSlots.add(new TimeSlot(11, 12, true));
        timeSlots.add(new TimeSlot(12, 13, true));
        timeSlots.add(new TimeSlot(13, 15, false)); // Lunch break
        timeSlots.add(new TimeSlot(15, 16, true));
        timeSlots.add(new TimeSlot(16, 17, true));
        timeSlots.add(new TimeSlot(17, 18, true));
    }

    // Method to change the service type of an appointment and update its fee
    public void changeAppointmentServiceType(Appointment appointment, String newServiceType, int serviceFee) {
        appointment.changeServiceType(newServiceType, serviceFee);
        System.out.println("Service type changed for appointment with patient: " + appointment.getPatient().getName());
    }

    // Getter for consultation fee
    public int getConsultationFee() {
        return consultationFee;
    }

    // Getter for doctor's name
    public String getName() {
        return name;
    }

    // Getter for specialization
    public String getSpecialisation() {
        return specialization;
    }

    // Show available time slots
    public void showAvailableTimeSlots() {
        System.out.println("Available time slots for Dr. " + this.name + ":");
        for (int i = 0; i < timeSlots.size(); i++) {
            TimeSlot slot = timeSlots.get(i);
            if (slot.isAvailable()) {
                System.out.println((i + 1) + ". " + slot);
            }
        }
    }

    // Set availability of a specific time slot
    public void setAvailability(int slotIndex, boolean isAvailable) {
        if (slotIndex >= 0 && slotIndex < timeSlots.size()) {
            timeSlots.get(slotIndex).setAvailability(isAvailable);
            System.out.println("Time slot availability updated: " + timeSlots.get(slotIndex));
        } else {
            System.out.println("Invalid time slot index.");
        }
    }

    // Get a specific time slot by index
    public TimeSlot getTimeSlot(int slotIndex) {
        if (slotIndex >= 0 && slotIndex < timeSlots.size()) {
            return timeSlots.get(slotIndex);
        }
        return null;
    }

    // Book a time slot for an appointment
    public void bookTimeSlot(int slotIndex) {
        if (slotIndex >= 0 && slotIndex < timeSlots.size()) {
            TimeSlot slot = timeSlots.get(slotIndex);
            if (slot.isAvailable()) {
                slot.setAvailability(false);
                System.out.println("Time slot " + slot.getStartTime() + ":00 - " + slot.getEndTime() + ":00 has been booked.");
            } else {
                System.out.println("This time slot is no longer available.");
            }
        } else {
            System.out.println("Invalid time slot.");
        }
    }

    // Manage appointments
    public void addAppointment(Appointment appointment) {
        upcomingAppointments.add(appointment);
        System.out.println("Appointment added for Dr. " + this.name + " on " + appointment.getAppointmentDate());
    }

    public void viewUpcomingAppointments() {
        if (upcomingAppointments.isEmpty()) {
            System.out.println("No upcoming appointments for Dr. " + this.name);
        } else {
            System.out.println("Upcoming appointments for Dr. " + this.name + ":");
            for (Appointment appointment : upcomingAppointments) {
                appointment.viewAppointmentDetails();
            }
        }
    }

    // Record the outcome of an appointment directly in the appointment object
    public void recordAppointmentOutcome(Appointment appointment, String diagnosis, List<Medicine> prescribedMedicines, String consultationNotes) {
        // Update the appointment details
        appointment.recordVisitDetails(diagnosis, consultationNotes, prescribedMedicines);

        // Update prescription status
        appointment.setPrescriptionStatus("Prescribed");

        System.out.println("Appointment outcome recorded for patient: " + appointment.getPatient().getName());
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Consultation notes: " + consultationNotes);
        System.out.println("Prescribed medicines:");
        for (Medicine medicine : prescribedMedicines) {
            System.out.println("  - " + medicine.getName());
        }
    }

    // Cancel an appointment and make the associated time slot available again
    public void cancelAppointment(Appointment appointment) {
        if (upcomingAppointments.contains(appointment)) {
            // Mark the time slot as available
            TimeSlot slot = appointment.getTimeSlot();
            if (slot != null) {
                slot.setAvailability(true);
            }

            // Remove the appointment from the doctor's upcoming appointments
            upcomingAppointments.remove(appointment);
            System.out.println("Appointment for patient " + appointment.getPatient().getName() + " has been canceled.");
        } else {
            System.out.println("No such appointment found.");
        }
    }

    public void viewPatientMedicalRecords(Patient patient) {
        System.out.println("Viewing medical records for patient: " + patient.getName());
        patient.viewMedicalRecord();  // Call the patient's method to view their medical record
    }

    public void acceptAppointment(Appointment appointment) {
        appointment.setStatus("Confirmed");  // Assuming you have a 'status' field in the Appointment class
        System.out.println("Appointment confirmed for patient: " + appointment.getPatient().getName());
    }

}
