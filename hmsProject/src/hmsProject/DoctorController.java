package hmsProject;

public class DoctorController extends controller{
	private Doctor currentDoctor;
	
	public DoctorController(MainController maincont, Doctor dr) {
		super(maincont);
		this.currentDoctor=dr;
	}
	
	public AppointmentSystem getApptSys() {
		return this.getDataStorage().getApptSys();
	}

	public void declineAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.getDataStorage().declineAppt(currentDoctor,appt);
	}

	public void addAccAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.getDataStorage().acceptAppt(appt);
	}

	public void updateAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.getDataStorage().updateCompletedAppt(appt);
		
	}
}
