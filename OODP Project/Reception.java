import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Reception {

    private Patient patient;
    private Specialisation specialisations = new Specialisation(); // Initialize the specialisations object

    Scanner sc = new Scanner(System.in);

    public void createPatient() {
        System.out.println("Enter your Patient ID (Enter 0 if you are a new Patient): ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline

        if (id == 0) {
            System.out.println("Enter your name: ");
            String name = sc.nextLine(); // Correct usage of nextLine()

            System.out.println("Enter your age: ");
            int age = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline after nextInt()

            System.out.println("Enter your gender: ");
            String gender = sc.next();

            System.out.println("Create a new account: ");
            System.out.println("Enter your Patient ID:");
            String userID = sc.next();

            System.out.println("Enter your password: ");
            String password = sc.next();

            patient = new Patient(name, gender, age, userID, password);
            patient.setMedicalRecord(new MedicalRecord(patient));  // Initialize medical record for the patient

            System.out.println("Login Again!");
            System.out.println("Enter your Patient ID:");
            int userid = sc.nextInt();
            System.out.println("Enter your password: ");
            String p = sc.next();
            patient.login(userid, p);

            determineSpecialisation();
        } else {
            // Existing patient flow
            System.out.println("Enter your password: ");
            String p = sc.next();
            patient.login(id, p);

            viewMedicalRecord();  // Reception views the medical record
            determineSpecialisation();
        }
    }

    public void determineSpecialisation() {
        
    // Get and display all specializations
		List<String> s = new ArrayList<>(specialisations.getSpecialisations());

		System.out.println("Available Departments for appointment with respective doctors: ");
		
		// Iterate through each specialisation and print departments with doctors
		for (String sp : s) {
			System.out.println("Department: " + sp);

			List<Doctor> doctors = specialisations.getDoctorsBySpecialisation(sp);

			if (doctors.isEmpty()) {
				System.out.println("  No doctors available for this Department.");
			} else {
				System.out.println("  Available Doctors: ");
				for (Doctor doctor : doctors) {
					System.out.println("    - " + doctor.getName());
				}
			}
		}

		// Ask user for their desired department
		// Ask user for their desired department
        System.out.println("Enter your desired department for appointment: ");
        String departmentName = sc.nextLine();  // Capture the user input for department

        // Check if the entered specialization exists
        if (specialisations.hasSpecialisation(departmentName)) {
            // Retrieve and print the doctors for the selected department
            List<Doctor> doctors = specialisations.getDoctorsBySpecialisation(departmentName);

            if (doctors.isEmpty()) {
                System.out.println("No doctors are available for the " + departmentName + " department.");
            } else {
                System.out.println("Doctors available in " + departmentName + " department: ");
                for (Doctor doctor : doctors) {
                    System.out.println("  - " + doctor.getName());
                }

                // Ask for the patient's desired doctor
                System.out.println("Enter your desired doctor for the appointment: ");
                String doctorName = sc.nextLine();  // Capture the user input for doctor name
                Doctor chosenDoctor = specialisations.getDoctorByName(departmentName, doctorName);

                if (chosenDoctor != null) {
                    // Show available time slots
                    chosenDoctor.showAvailableTimeSlots();

                    // Input the appointment date
                    LocalDate appointmentDate = null;
                    boolean validDate = false;
                    while (!validDate) {
                        System.out.println("Enter the appointment date (YYYY-MM-DD):");
                        String dateInput = sc.nextLine();
                        try {
                            appointmentDate = LocalDate.parse(dateInput);  // Parse input into LocalDate
                            validDate = true;  // Date is valid
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please try again.");
                        }
                    }

                    // Ask the patient to choose a time slot
                    System.out.println("Choose an available time slot (enter the number):");
                    int timeSlotChoice = sc.nextInt() - 1;
                    sc.nextLine();  // Consume the leftover newline

                    // Book the selected time slot for the chosen doctor
                    chosenDoctor.bookTimeSlot(timeSlotChoice);

                    // Create and display the appointment
                    Appointment appointment = new Appointment(patient, chosenDoctor, chosenDoctor.getTimeSlot(timeSlotChoice), appointmentDate);
                    appointment.viewAppointmentDetails();
                } else {
                    System.out.println("The selected doctor does not exist. Please try again.");
                }
            }
        } else {
            System.out.println("The entered department does not exist. Please try again.");
        }
	}

	public double finalBill() {
        // TODO - implement Reception.finalBill
        throw new UnsupportedOperationException();
    }

	public void viewMedicalRecord() {
        if (patient != null) {
            patient.viewMedicalRecord(); // Call Patient's method to view medical record
        } else {
            System.out.println("No patient is logged in.");
        }
    }

}


