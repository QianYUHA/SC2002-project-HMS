import java.util.ArrayList;
import java.util.List;

public class Doctor extends User{

    private String specialisation;
	private String name;
    private List<TimeSlot> timeSlots;
    private List<Appointment> upcomingAppointments = new ArrayList<>(); //u


    public Doctor(String name, String specialisation) {
        this.name = name;
        this.specialisation = specialisation;
        this.timeSlots = new ArrayList<>();
        initializeTimeSlots();
    }

    // Initialize daily time slots
    private void initializeTimeSlots() {
        timeSlots.add(new TimeSlot(7, 8, true));
        timeSlots.add(new TimeSlot(8, 9, true));
        timeSlots.add(new TimeSlot(9, 10, true));
        timeSlots.add(new TimeSlot(10, 11, true));
        timeSlots.add(new TimeSlot(11, 12, true));
        timeSlots.add(new TimeSlot(12, 13, true));
        timeSlots.add(new TimeSlot(13, 15, false));  // Lunch break
        timeSlots.add(new TimeSlot(15, 16, true));
        timeSlots.add(new TimeSlot(16, 17, true));
        timeSlots.add(new TimeSlot(17, 18, true));
    }

    public String getName() {
        return name;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    // Display available time slots
    public void showAvailableTimeSlots() {
        System.out.println("Available time slots for Dr. " + this.name + ":");
        for (int i = 0; i < timeSlots.size(); i++) {
            TimeSlot slot = timeSlots.get(i);
            if (slot.isAvailable()) {
                System.out.println((i + 1) + ". " + slot);
            }
        }
    }

    // Mark a time slot as unavailable
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

	public TimeSlot getTimeSlot(int slotIndex) {
        return timeSlots.get(slotIndex);
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    // Doctor views the patient's medical record
    public void viewPatientRecord(Patient patient) {
        System.out.println("Doctor accessing patient's medical record...");
        patient.viewMedicalRecord();  // Doctor views the medical record through the Patient object
    }

    // Doctor updates the patient's medical record (add new diagnosis or treatment)
    public void updatePatientRecord(Patient patient, String diagnosis, String treatment) {
        System.out.println("Updating patient's medical record...");
        // Use the correct methods to add diagnosis and treatment to the lists
        patient.getMedicalRecord().setDiagnoses(diagnosis); // Add diagnosis to the list
        patient.getMedicalRecord().setTreatments(treatment); // Add treatment to the list
        System.out.println("Record updated successfully.");
    }

    public void setAvailability(int slotIndex, boolean isAvailable) {
        // TODO - implement Doctor.setAvailability
        //throw new UnsupportedOperationException();
        if (slotIndex >= 0 && slotIndex < timeSlots.size()) {
            timeSlots.get(slotIndex).setAvailability(isAvailable);
        }
    }
    public void viewUpcomingAppointments() {
        if (upcomingAppointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
        } else {
            System.out.println("Upcoming appointments for Dr. " + this.name + ":");
            for (Appointment appointment : upcomingAppointments) {
                appointment.viewAppointmentDetails();
            }
        }
    }

    // Add a new appointment
    public void addAppointment(Appointment appointment) {
        upcomingAppointments.add(appointment);
    }

    // Remove an appointment (after completion or cancellation)
    public void removeAppointment(Appointment appointment) {
        upcomingAppointments.remove(appointment);
    }

    public void recordAppointmentOutcome(Appointment appointment, String treatment, List<Medicine> prescribedMedications, String consultationNotes) {
        MedicalRecord record = appointment.getPatient().getMedicalRecord();
        record.setDiagnoses(treatment); // Record treatment as diagnosis
        record.setTreatments(consultationNotes); // Record consultation notes as treatment
        record.setPrescribedMedications(prescribedMedications); // Set the list of prescribed medications

        System.out.println("Appointment outcome recorded for patient: " + appointment.getPatient().getName());
    }

    public void cancelAppointment(Appointment appointment) {
        // TODO - implement Doctor.cancelAppointment
        throw new UnsupportedOperationException();
    }

	/*

    @Override
    public void rescheduleAppointment(Appointment appointment) {
        // TODO - implement Doctor.rescheduleAppointment
        throw new UnsupportedOperationException();
    }

    @Override
    public void scheduleAppointment(Appointment appointment) {
        // TODO - implement Doctor.scheduleAppointment
        throw new UnsupportedOperationException();
    }

	*/
}
