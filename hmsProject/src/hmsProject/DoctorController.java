package hmsProject;

/**
 * Doctor Controller that manages all Doctor operations and links to DataStorage.
 */
public class DoctorController extends Controller{
	private Doctor currentDoctor;

	/**
	 * DoctorController Constructor.
	 * @param maincont MainController
	 * @param dr Doctor
	 */
	public DoctorController(MainController maincont, Doctor dr) {
		super(maincont);
		this.currentDoctor=dr;
	}

	/**
	 * AppointmentSystem getter.
	 * @return AppointmentSystem
	 */
	public AppointmentSystem getApptSys() {
		return this.getDataStorage().retrieveApptSys();
	}

	/**
	 * Decline an appointment.
	 * @param appt declined appointment
	 */
	public void declineAppt(Appointment appt) {
		this.getDataStorage().declineAppt(currentDoctor,appt);
	}

	/**
	 * Accept an appointment.
	 * @param appt accepted appointment
	 */
	public void addAccAppt(Appointment appt) {
		this.getDataStorage().acceptAppt(appt);
	}

	/**
	 * Update appointment once it is completed. Create Outcome Record for the patient after an appointment.
	 * @param appt
	 */
	public void updateAppt(Appointment appt) {
		this.getDataStorage().updateCompletedAppt(appt);
		
	}

	/**
	 * Saves all changes.
	 */
	public void save() {
		this.getDataStorage().save();
	}

}
