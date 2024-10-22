import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Appointment with all the details of a patient's visit.
 */
public class Appointment {

    private static int idCounter = 1;  // Static counter for generating unique appointment IDs
    private int appointmentID;         // Unique appointment ID
    private Patient patient;
    private Doctor doctor;
    private TimeSlot timeSlot;
    private LocalDate appointmentDate; // Date of appointment/visit
    private String diagnosis;          // Diagnosis during visit
    private String serviceType;        // Consultation, X-ray, MRI, etc.
    private String consultationNotes;  // Notes added by doctor
    private List<Medicine> prescribedMedicines; // List of medicines prescribed
    private String prescriptionStatus; // Prescription status: "Prescribed", "Dispatched", etc.
    private int consultationFee;       // Fee for consultation based on the department (set initially)
    private int serviceTypeFee;        // Fee for the service type (initially consultation fee)

    // Constructor for scheduling an appointment (initially set consultation fee based on doctor's department)
    public Appointment(Patient patient, Doctor doctor, TimeSlot timeSlot, LocalDate appointmentDate) {
        this.appointmentID = idCounter++;
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
        this.appointmentDate = appointmentDate;
        this.serviceType = "General Consultation";
        this.consultationFee = doctor.getConsultationFee();
        this.serviceTypeFee = consultationFee;
        this.prescriptionStatus = "Prescribed";
        this.prescribedMedicines = new ArrayList<>();
        this.status = "Scheduled";  // Default to scheduled
    }

    // Method to change the service type and set the associated fee
    public void changeServiceType(String serviceType, int serviceFee) {
        this.serviceType = serviceType;
        this.serviceTypeFee = serviceFee;
        System.out.println("Service type changed to " + serviceType + " with fee: $" + serviceFee);
    }

    // Method to record visit details after an appointment
    public void recordVisitDetails(String diagnosis, String consultationNotes, List<Medicine> prescribedMedicines) {
        this.diagnosis = diagnosis;
        this.consultationNotes = consultationNotes;
        this.prescribedMedicines = prescribedMedicines;
        System.out.println("Visit details recorded for appointment ID: " + appointmentID);
    }

    // Getter methods
    public int getAppointmentID() {
        return appointmentID;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getConsultationFee() {
        return consultationFee;
    }

    public int getServiceTypeFee() {
        return serviceTypeFee;
    }

    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    // Setter for prescription status (used by the pharmacist to update to "Dispatched")
    public void setPrescriptionStatus(String status) {
        this.prescriptionStatus = status;
    }

    // View appointment details including prescribed medicines
    public void viewAppointmentDetails() {
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Date: " + appointmentDate);
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName() + " (Specialisation: " + doctor.getSpecialisation() + ")");
        System.out.println("Time Slot: " + timeSlot);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Service Type Fee: $" + serviceTypeFee);
        if (diagnosis != null) {
            System.out.println("Diagnosis: " + diagnosis);
            System.out.println("Consultation Notes: " + consultationNotes);
            System.out.println("Prescribed Medicines: ");
            for (Medicine med : prescribedMedicines) {
                System.out.println("  - " + med.getName());
            }
        }
        System.out.println("Prescription Status: " + prescriptionStatus);
    }

    // Get the list of prescribed medicines (used by the pharmacist)
    public List<Medicine> getPrescribedMedicines() {
        return prescribedMedicines;
    }

    private String status;  // Add a status field

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
        timeSlot.setAvailability(true);  // Make time slot available again
        System.out.println("Appointment ID " + this.appointmentID + " has been cancelled.");
    }

    public void rescheduleAppointment(TimeSlot newSlot) {
        this.timeSlot.setAvailability(true);  // Release old slot
        this.timeSlot = newSlot;  // Assign new slot
        this.status = "Rescheduled";
        System.out.println("Appointment ID " + this.appointmentID + " has been rescheduled.");
    }


}
