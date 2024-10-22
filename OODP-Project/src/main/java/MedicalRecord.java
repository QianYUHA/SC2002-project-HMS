import java.util.ArrayList;
import java.util.List;

/**
 * Represents a medical record for a patient, containing all appointments.
 */
public class MedicalRecord {

    private Patient patient;  // The patient this medical record belongs to
    private List<Appointment> appointments;  // List of all appointments (past and future)

    public MedicalRecord(Patient patient) {
        this.patient = patient;
        this.appointments = new ArrayList<>();
    }

    // Add an appointment (either scheduled or after completion)
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // View the complete medical record (both scheduled and past visits)
    public void viewRecord() {
        System.out.println("Medical Record for " + patient.getName() + ":");
        if (appointments.isEmpty()) {
            System.out.println("No appointments or visits recorded.");
        } else {
            for (Appointment appointment : appointments) {
                appointment.viewAppointmentDetails();
            }
        }
    }

    // Get the list of all appointments (scheduled or completed)
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void updateAppointment(Appointment appointment) {
        System.out.println("Updated appointment details for appointment ID: " + appointment.getAppointmentID());
    }

}
