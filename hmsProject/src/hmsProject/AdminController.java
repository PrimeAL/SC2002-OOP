package hmsProject;

import java.util.ArrayList;

public class AdminController extends controller{
	private Administrator currentAdm;
	
	public AdminController(MainController maincont, Administrator adm) {
		super(maincont);
		this.currentAdm=adm;
	}
	
	public AppointmentSystem getApptSys(){
		return this.getDataStorage().retrieveApptSys();
	}
	
	public ArrayList<Appointment> getScheduledAppts(){
		return this.getApptSys().getSchAppt();
	}
}
