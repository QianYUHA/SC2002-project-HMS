import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main mainTest = new Main();
        mainTest.runAllTests();  // Run all test cases at once
    }

    // This method will call all the individual test methods
    public void runAllTests() {
        System.out.println("Running Patient Test Cases:");
        testPatientCases();

        System.out.println("\nRunning Doctor Test Cases:");
        testDoctorCases();

        System.out.println("\nRunning Pharmacist Test Cases:");
        testPharmacistCases();

        System.out.println("\nRunning Administrator Test Cases:");
        testAdministratorCases();

        System.out.println("\nRunning Login and Password Test Cases:");
        testLoginCases();
    }

    // Helper methods for creating mock data (e.g., create mock patients, doctors, etc.)
    private Patient createMockPatient() {
        return new Patient(1, "password", "P001", "John Doe", "Male", 30);
    }

    private Doctor createMockDoctor() {
        return new Doctor(1, "password", "Dr. Smith", "Cardiology", 100);
    }

    private Pharmacist createMockPharmacist() {
        Inventory inventory = new Inventory(5);
        return new Pharmacist(2, "password", inventory);
    }

    private Administrator createMockAdministrator() {
        Inventory inventory = new Inventory(5);
        return new Administrator(inventory);
    }

    private Medicine createMockMedicine() {
        return new Medicine("Aspirin", 10);
    }

    // Test cases for patients
    public void testPatientCases() {
        Patient patient = createMockPatient();
        Doctor doctor = createMockDoctor();
        Pharmacist pharmacist = createMockPharmacist();

        // Test Case 1: View Medical Record
        System.out.println("Test Case 1: View Medical Record");
        patient.viewMedicalRecord();

        // Test Case 2: Update Personal Information
        System.out.println("Test Case 2: Update Personal Information");
        patient.setName("Jane Doe");
        patient.setAge(29);
        System.out.println("Updated Patient Name: " + patient.getName());
        System.out.println("Updated Patient Age: " + patient.getAge());

        // Test Case 3: View Available Appointment Slots
        System.out.println("Test Case 3: View Available Appointment Slots");
        doctor.showAvailableTimeSlots();

        // Test Case 4: Schedule an Appointment
        System.out.println("Test Case 4: Schedule an Appointment");
        TimeSlot timeSlot = doctor.getTimeSlot(0);  // Assuming first slot is available
        Appointment appointment = new Appointment(patient, doctor, timeSlot, LocalDate.now());
        patient.addAppointment(appointment);
        patient.viewUpcomingAppointments();

        // Test Case 5: Reschedule an Appointment
        System.out.println("Test Case 5: Reschedule an Appointment");
        TimeSlot newTimeSlot = doctor.getTimeSlot(1);  // Assuming the second slot is available
        appointment.rescheduleAppointment(newTimeSlot);
        patient.viewUpcomingAppointments();

        // Test Case 6: Cancel an Appointment
        System.out.println("Test Case 6: Cancel an Appointment");
        appointment.cancelAppointment();
        patient.viewUpcomingAppointments();

        // Test Case 7: View Scheduled Appointments
        System.out.println("Test Case 7: View Scheduled Appointments");
        patient.viewUpcomingAppointments();  // No appointments should show after cancellation

        // Test Case 8: View Past Appointment Outcome Records
        System.out.println("Test Case 8: View Past Appointment Outcome Records");
        patient.viewMedicalRecord();  // Mock appointments will be shown here
    }

    // Test cases for doctors
    public void testDoctorCases() {
        Doctor doctor = createMockDoctor();
        Patient patient = createMockPatient();

        // Test Case 9: View Patient Medical Records
        System.out.println("Test Case 9: View Patient Medical Records");
        doctor.viewPatientMedicalRecords(patient);

        // Test Case 10: Update Patient Medical Records
        System.out.println("Test Case 10: Update Patient Medical Records");
        List<Medicine> prescribedMedicines = new ArrayList<>();
        prescribedMedicines.add(createMockMedicine());
        doctor.recordAppointmentOutcome(new Appointment(patient, doctor, doctor.getTimeSlot(0), LocalDate.now()),
                "Flu", prescribedMedicines, "Rest for 3 days");

        // Test Case 11: View Personal Schedule
        System.out.println("Test Case 11: View Personal Schedule");
        doctor.viewUpcomingAppointments();

        // Test Case 12: Set Availability for Appointments
        System.out.println("Test Case 12: Set Availability for Appointments");
        doctor.setAvailability(0, false);  // Set first slot as unavailable

        // Test Case 13: Accept or Decline Appointment Requests
        System.out.println("Test Case 13: Accept or Decline Appointment Requests");
        Appointment appointment = new Appointment(patient, doctor, doctor.getTimeSlot(1), LocalDate.now());
        doctor.addAppointment(appointment);  // Add appointment to doctor's schedule
        doctor.acceptAppointment(appointment);  // Confirm appointment

        // Test Case 14: View Upcoming Appointments
        System.out.println("Test Case 14: View Upcoming Appointments");
        doctor.viewUpcomingAppointments();

        // Test Case 15: Record Appointment Outcome
        System.out.println("Test Case 15: Record Appointment Outcome");
        doctor.recordAppointmentOutcome(appointment, "Headache", prescribedMedicines, "Take medicine for 1 week");
    }

    // Test cases for pharmacists
    public void testPharmacistCases() {
        Pharmacist pharmacist = createMockPharmacist();
        Appointment appointment = new Appointment(createMockPatient(), createMockDoctor(),
                new TimeSlot(8, 9, true), LocalDate.now());
        appointment.recordVisitDetails("Fever", "Consultation notes", new ArrayList<>());

        // Test Case 16: View Appointment Outcome Record
        System.out.println("Test Case 16: View Appointment Outcome Record");
        appointment.viewAppointmentDetails();

        // Test Case 17: Update Prescription Status
        System.out.println("Test Case 17: Update Prescription Status");
        pharmacist.dispenseMedicines(appointment);

        // Test Case 18: View Medication Inventory
        System.out.println("Test Case 18: View Medication Inventory");
        pharmacist.viewInventory();

        // Test Case 19: Submit Replenishment Request
        System.out.println("Test Case 19: Submit Replenishment Request");
        Medicine medicine = createMockMedicine();
        pharmacist.requestReplenishment(medicine);
    }

    // Test cases for administrators
    public void testAdministratorCases() {
        Administrator admin = createMockAdministrator();
        Staff staff = new Staff();  // Assume this class is implemented
        staff.setName("Nurse Jane");

        // Test Case 20: View and Manage Hospital Staff
        System.out.println("Test Case 20: View and Manage Hospital Staff");
        admin.addStaff(staff);
        admin.manageStaff(new ArrayList<>(List.of(staff)));

        // Test Case 21: View Appointments Details
        System.out.println("Test Case 21: View Appointments Details");
        admin.viewInventory();  // Assuming admin can view appointments via inventory view

        // Test Case 22: View and Manage Medication Inventory
        System.out.println("Test Case 22: View and Manage Medication Inventory");
        admin.viewInventory();

        // Test Case 23: Approve Replenishment Requests
        System.out.println("Test Case 23: Approve Replenishment Requests");
        Medicine medicine = createMockMedicine();
        admin.approveReplenishment(medicine, 10);
    }


    // Test cases for login and password management
    public void testLoginCases() {
        User user = new Patient(1, "password", "P001", "John Doe", "Male", 30);  // Mock user

        // Test Case 25: First-Time Login and Password Change
        System.out.println("Test Case 25: First-Time Login and Password Change");
        user.login(1, "password");
        user.changePassword("newPassword");
        user.logout();

        // Test Case 26: Login with Incorrect Credentials
        System.out.println("Test Case 26: Login with Incorrect Credentials");
        boolean loginResult = user.login(1, "wrongPassword");
        if (!loginResult) {
            System.out.println("Login failed: Invalid credentials.");
        }
    }



}
