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

	public void addAvailAppointment(Appointment newAppt) {
		// TODO Auto-generated method stub
		this.getDataStorage().updateAvailAppt(currentDoctor,newAppt);
	}
	public void addCompletedAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.getDataStorage().updateCompletedAppt(appt);
	}
}
