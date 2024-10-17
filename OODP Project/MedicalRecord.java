import java.util.*;

public class MedicalRecord {

    private Patient owner;  // Patient who owns this record
    private List<String> diagnoses = new ArrayList<>();
    private List<String> treatments = new ArrayList<>();
    private List<Medicine> prescribedMedications = new ArrayList<>();


    public MedicalRecord(Patient owner) {
        this.owner = owner;
    }

    public void setPrescribedMedications(List<Medicine> medications) {
        this.prescribedMedications = medications;
    }

    // Get all diagnoses
    public void getDiagnoses() {
        if (!diagnoses.isEmpty()) {
            for (String diagnosis : diagnoses) {
                System.out.println(diagnosis);
            }
        } else {
            System.out.println("No diagnoses available.");
        }
    }

    // Add a diagnosis to the list
    public void setDiagnoses(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    // Get all treatments
    public void getTreatments() {
        if (!treatments.isEmpty()) {
            for (String treatment : treatments) {
                System.out.println(treatment);
            }
        } else {
            System.out.println("No treatments available.");
        }
    }

    // Add a treatment to the list
    public void setTreatments(String treatment) {
        treatments.add(treatment);
    }

    public void addMedications(List<Medicine> medications) {
        prescribedMedications.addAll(medications);
    }

    // Get all prescribed medications
    public void getPrescribedMedications() {
        if (!prescribedMedications.isEmpty()) {
            for (Medicine medication : prescribedMedications) {
                System.out.println("Medication: " + medication);
            }
        } else {
            System.out.println("No prescribed medications.");
        }
    }
}
