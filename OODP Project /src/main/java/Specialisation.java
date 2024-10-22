import java.util.*;

public class Specialisation {

    // HashMap to store the mapping between specialisation and doctors
    private Map<String, List<Doctor>> specialisationToDoctors = new HashMap<>();

    // Method to add a doctor to a specialisation
    public void addDoctorToSpecialisation(String specialisation, Doctor doctor) {
        if (!specialisationToDoctors.containsKey(specialisation)) {
            specialisationToDoctors.put(specialisation, new ArrayList<>());
        }
        specialisationToDoctors.get(specialisation).add(doctor);
    }

    // Method to get the list of doctors for a given specialisation
    public List<Doctor> getDoctorsBySpecialisation(String specialisation) {
        return specialisationToDoctors.getOrDefault(specialisation, new ArrayList<>());
    }

    // Method to get a doctor by name in a specific specialisation
    public Doctor getDoctorByName(String specialisation, String doctorName) {
        List<Doctor> doctors = specialisationToDoctors.get(specialisation);
        if (doctors != null) {
            for (Doctor doctor : doctors) {
                if (doctor.getName().equalsIgnoreCase(doctorName)) {
                    return doctor;
                }
            }
        }
        return null;  // Return null if the doctor is not found
    }

    // Method to print all specialisations and corresponding doctors
    public void printAllSpecialisationsAndDoctors() {
        for (Map.Entry<String, List<Doctor>> entry : specialisationToDoctors.entrySet()) {
            System.out.println("Specialisation: " + entry.getKey());
            System.out.println("Doctors:");
            for (Doctor doctor : entry.getValue()) {
                System.out.println("  - " + doctor.getName());
            }
        }
    }

    // Method to get all specialisations
    public Set<String> getSpecialisations() {
        return specialisationToDoctors.keySet();
    }

    // Method to check if a specialisation exists
    public boolean hasSpecialisation(String specialisation) {
        return specialisationToDoctors.containsKey(specialisation);
    }
}
