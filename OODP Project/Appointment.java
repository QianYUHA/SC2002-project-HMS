import java.time.LocalDate;

public class Appointment {

    private static int idCounter = 1; // Static counter for generating unique IDs
    private int appointmentID;  // Unique appointment ID
    private Patient patient;
    private Doctor doctor;
    private TimeSlot timeSlot;
	private LocalDate appointmentDate;
    private String serviceType;  // Type of service (Consultation, X-ray, etc.)


    public Appointment(Patient patient, Doctor doctor, TimeSlot timeSlot, LocalDate appointmentDate, String serviceType) {
        this.appointmentID = idCounter++; 
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;  
        this.appointmentDate = appointmentDate;  // Set the date of the appointment
        this.serviceType = serviceType;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void viewAppointmentDetails() {
        System.out.println("Appointment ID: " + appointmentID);
		System.out.println("Date: " + appointmentDate);
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName() + " (Specialisation: " + doctor.getSpecialisation() + ")");
        System.out.println("Time Slot: " + timeSlot);
    }

	public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
}