package hmsProject;

public class DoctorController extends controller{
	private Doctor currentDoctor;
	
	public DoctorController(MainController maincont, Doctor dr) {
		super(maincont);
		this.currentDoctor=dr;
	}
	
	public AppointmentSystem getApptSys() {
		return this.getDataStorage().retrieveApptSys();
	}

	public void declineAppt(Appointment appt) {
		this.getDataStorage().declineAppt(currentDoctor,appt);
	}

	public void addAccAppt(Appointment appt) {
		this.getDataStorage().acceptAppt(appt);
	}

	public void updateAppt(Appointment appt) {
		this.getDataStorage().updateCompletedAppt(appt);
		
	}
	
	public void save() {
		this.getDataStorage().save();
	}

}
