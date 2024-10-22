import java.time.LocalDate;
import java.util.List;

/**
 * Represents a Patient with a medical record and appointment management functionality.
 */
public class Patient extends User implements AppointmentManager, UserInterface {

    private MedicalRecord medicalRecord;
    private String patientID;
    private String name;    // Patient name
    private String gender;  // Patient gender
    private int age;        // Patient age

    // Constructor for a new patient
    public Patient(int userID, String password, String patientID, String name, String gender, int age) {
        super(userID, password);
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        // Create a new medical record for the patient
        this.medicalRecord = new MedicalRecord(this);
    }

    // Constructor when loading an existing patient (e.g., from file)
    public Patient(int userID, String password, String patientID) {
        super(userID, password);
        this.patientID = patientID;
        // Load patient data from Patient.xlsx (logic can be added)
        this.medicalRecord = new MedicalRecord(this);
    }

    public Patient(String patientID) {
        this.patientID = patientID;
    }

    // Getter and setter for the patient's name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for the patient's age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter and setter for the patient's gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter for the patient ID
    public String getPatientID() {
        return patientID;
    }

    // Getter for the patient's medical record
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    // View the patient's medical record (includes all appointments, both past and future)
    public void viewMedicalRecord() {
        System.out.println("Medical Record for Patient: " + this.patientID);
        medicalRecord.viewRecord();
    }

    // Add an appointment to the patient's medical record
    public void addAppointment(Appointment appointment) {
        medicalRecord.addAppointment(appointment);
    }

    // Schedule an appointment for the patient
    @Override
    public void scheduleAppointment(Appointment appointment) {
        addAppointment(appointment);  // Add to the medical record
        System.out.println("Appointment scheduled for patient " + this.name + " with Dr. " + appointment.getDoctor().getName() + " on " + appointment.getAppointmentDate());
    }

    // Reschedule an appointment for the patient
    @Override
    public void rescheduleAppointment(Appointment appointment) {
        System.out.println("Appointment rescheduled for patient " + this.name + " with Dr. " + appointment.getDoctor().getName() + " on " + appointment.getAppointmentDate());
    }

    // Cancel an appointment for the patient
    @Override
    public void cancelAppointment(Appointment appointment) {
        System.out.println("Appointment canceled for patient " + this.name + " with Dr. " + appointment.getDoctor().getName());
        medicalRecord.getAppointments().remove(appointment);  // Remove from medical record
    }

    // Function to view only upcoming appointments
    public void viewUpcomingAppointments() {
        System.out.println("Upcoming appointments for patient " + this.name + ":");
        List<Appointment> appointments = medicalRecord.getAppointments();
        boolean foundUpcoming = false;
        LocalDate today = LocalDate.now();

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentDate().isAfter(today)) {
                appointment.viewAppointmentDetails();
                foundUpcoming = true;
            }
        }

        if (!foundUpcoming) {
            System.out.println("No upcoming appointments found.");
        }
    }
}
