public class Patient extends User implements AppointmentManager, UserInterface {

	private MedicalRecord medicalRecord;
	private String name;
	private String gender;
	private int age;

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public int getAge(){
		return this.age;
	}
	public void setAge(int age){
		this.age = age;
	}

	public String getGender(){
		return this.gender;
	}
	public void setGender(String gender){
		this.gender = gender;
	}

	public MedicalRecord getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public void viewMedicalRecord() {
        System.out.println("Patient: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Gender: " + this.gender);
        System.out.println("Diagnoses: ");
        medicalRecord.getDiagnoses();
        System.out.println("Respective Treatments: ");
        medicalRecord.getTreatments();
        System.out.println("Prescribed Medications: ");
        medicalRecord.getPrescribedMedications();
    }

	/**
	 * 
	 * @param appointment
	 */
	public void scheduleAppointment(Appointment appointment) {
		// TODO - implement Patient.scheduleAppointment
		//throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param appointment
	 */
	public void rescheduleAppointment(Appointment appointment) {
		// TODO - implement Patient.rescheduleAppointment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param appointment
	 */
	public void cancelAppointment(Appointment appointment) {
		// TODO - implement Patient.cancelAppointment
		throw new UnsupportedOperationException();
	}

	public Patient(String name, String gender, int age, String userID, String password) {
		// TODO - implement Patient.Patient
		//throw new UnsupportedOperationException();
		this.age = age;
		this.gender = gender;
		this.name = name;
		super.setUserID(userID);
		super.setPassword(password);
	}

}