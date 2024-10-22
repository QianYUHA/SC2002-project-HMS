public interface AppointmentManager {

	/**
	 * 
	 * @param appointment
	 */
	void scheduleAppointment(Appointment appointment);

	/**
	 * 
	 * @param appointment
	 */
	void rescheduleAppointment(Appointment appointment);

	/**
	 * 
	 * @param appointment
	 */
	void cancelAppointment(Appointment appointment);

}