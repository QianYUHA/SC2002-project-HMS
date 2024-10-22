import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Handles patient reception tasks such as creating accounts, scheduling appointments, and generating final bills.
 */
public class Reception {

    private Patient patient;
    private Specialisation specialisations = new Specialisation(); // Initialize the specialisations object
    private static final String FILE_PATH = "Patient.xlsx";  // Path to Patient.xlsx

    Scanner sc = new Scanner(System.in);

    // Create or login a patient
    public void createPatient() {
        System.out.println("Enter your Patient ID (Enter 0 if you are a new Patient): ");
        String patientID = sc.nextLine();

        if (patientID.equals("0")) {
            registerNewPatient();
        } else if (patientExists(patientID)) {
            loginExistingPatient(patientID);
        } else {
            System.out.println("Patient ID not found. Please register as a new patient.");
            registerNewPatient();
        }
    }

    // Register a new patient
    private void registerNewPatient() {
        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("Enter your date of birth (YYYY-MM-DD): ");
        String dob = sc.nextLine();

        System.out.println("Enter your gender: ");
        String gender = sc.nextLine();

        System.out.println("Enter your contact information: ");
        String contactInfo = sc.nextLine();

        System.out.println("Create a new Patient ID: ");
        String newPatientID = sc.nextLine();

        System.out.println("Create a password: ");
        String password = sc.nextLine();

        addNewPatientToFile(newPatientID, password, name, dob, gender, contactInfo);

        System.out.println("Registration successful! Please login.");

        loginExistingPatient(newPatientID);
    }

    // Login an existing patient
    private void loginExistingPatient(String patientID) {
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        if (authenticatePatient(patientID, password)) {
            System.out.println("Login successful!");
            patient = new Patient(patientID);  // Assume constructor reads details from file
            determineSpecialisation();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    // Authenticate a patient from the file
    private boolean authenticatePatient(String patientID, String password) {
        try (FileInputStream file = new FileInputStream(new File(FILE_PATH))) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equals(patientID) &&
                    row.getCell(1).getStringCellValue().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error accessing patient file.");
        }
        return false;
    }

    // Check if a patient ID exists in the file
    private boolean patientExists(String patientID) {
        try (FileInputStream file = new FileInputStream(new File(FILE_PATH))) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equals(patientID)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error accessing patient file.");
        }
        return false;
    }

    // Add a new patient to the file
    private void addNewPatientToFile(String patientID, String password, String name, String dob, String gender, String contactInfo) {
        try (FileInputStream file = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();

            Row row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(patientID);
            row.createCell(1).setCellValue(password);
            row.createCell(2).setCellValue(name);
            row.createCell(3).setCellValue(dob);
            row.createCell(4).setCellValue(gender);
            row.createCell(5).setCellValue(contactInfo);

            // Write to the file
            FileOutputStream outFile = new FileOutputStream(new File(FILE_PATH));
            workbook.write(outFile);
            outFile.close();

        } catch (IOException e) {
            System.out.println("Error writing new patient data.");
        }
    }

    // Handle specialisation selection and appointment scheduling
    public void determineSpecialisation() {
        List<String> specialisationList = new ArrayList<>(specialisations.getSpecialisations());
        System.out.println("Available Departments for appointment with respective doctors: ");
        
        for (String sp : specialisationList) {
            System.out.println("Department: " + sp);
            List<Doctor> doctors = specialisations.getDoctorsBySpecialisation(sp);
            if (doctors.isEmpty()) {
                System.out.println("  No doctors available for this Department.");
            } else {
                System.out.println("  Available Doctors: ");
                for (Doctor doctor : doctors) {
                    System.out.println("  - " + doctor.getName());
                }
            }
        }

        // Input the department and schedule appointment
        System.out.println("Enter your desired department for appointment: ");
        String departmentName = sc.nextLine();
        if (specialisations.hasSpecialisation(departmentName)) {
            List<Doctor> doctors = specialisations.getDoctorsBySpecialisation(departmentName);
            System.out.println("Enter your desired doctor for the appointment: ");
            String doctorName = sc.nextLine();
            Doctor chosenDoctor = specialisations.getDoctorByName(departmentName, doctorName);
            if (chosenDoctor != null) {
                scheduleAppointment(chosenDoctor);
            } else {
                System.out.println("The selected doctor does not exist. Please try again.");
            }
        } else {
            System.out.println("The entered department does not exist. Please try again.");
        }
    }

    private void scheduleAppointment(Doctor doctor) {
        System.out.println("Enter the date for the appointment (YYYY-MM-DD): ");
        String appointmentDate = sc.nextLine();

        doctor.showAvailableTimeSlots();
        System.out.println("Choose an available time slot (enter the number): ");
        int slotChoice = sc.nextInt();
        sc.nextLine();  // Consume leftover newline

        TimeSlot chosenSlot = doctor.getTimeSlot(slotChoice - 1);
        if (chosenSlot.isAvailable()) {
            doctor.bookTimeSlot(slotChoice - 1);
            Appointment appointment = new Appointment(patient, doctor, chosenSlot, LocalDate.parse(appointmentDate));
            patient.addAppointment(appointment);
            appointment.viewAppointmentDetails();
        } else {
            System.out.println("This time slot is already booked. Please choose another.");
        }
    }


    // Generate the final bill
    public int generateFinalBill(Appointment appointment, Pharmacist pharmacist) {
        int consultationFee = appointment.getConsultationFee();      // Doctor consultation fee
        int serviceFee = appointment.getServiceTypeFee();            // Service type fee
        int pharmacistBill = pharmacist.generatePharmacistBill(appointment); // Bill for medicines

        int subTotal = consultationFee + serviceFee + pharmacistBill; // Subtotal before tax
        // double tax = subTotal * 0.1; // Uncomment if you want to apply a 10% tax
        // int total = (int) (subTotal + tax); // Total with tax
        int total = subTotal;  // Without tax

        System.out.println("Final Bill Breakdown:");
        System.out.println("Consultation Fee: $" + consultationFee);
        System.out.println("Service Type Fee: $" + serviceFee);
        System.out.println("Pharmacist Bill: $" + pharmacistBill);
        // System.out.println("Tax (10%): $" + tax);
        System.out.println("Total Bill: $" + total);

        return total;
    }

    // View the patient's medical record
    public void viewMedicalRecord() {
        if (patient != null) {
            patient.viewMedicalRecord(); // Call Patient's method to view medical record
        } else {
            System.out.println("No patient is logged in.");
        }
    }
}
