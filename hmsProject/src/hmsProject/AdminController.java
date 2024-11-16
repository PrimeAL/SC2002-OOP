package hmsProject;

import java.util.ArrayList;

/**
 * Administrator Controller class that controls all interactions between Administrator class and other classes
 */
public class AdminController extends controller{
	private Administrator currentAdm;

	/**
	 * AdminController class constructor
	 * @param maincont
	 * @param adm
	 */
	public AdminController(MainController maincont, Administrator adm) {
		super(maincont);
		this.currentAdm=adm;
	}

	/**
	 * AppointmentSystem class Getter
	 * @return AppointmentSystem
	 */
	public AppointmentSystem getApptSys(){
		return this.getDataStorage().retrieveApptSys();
	}

	/**
	 * Scheduled appointment list Getter
	 * @return ArrayList<Appointment>
	 */
	public ArrayList<Appointment> getScheduledAppts(){
		return this.getApptSys().getSchAppt();
	}
}
