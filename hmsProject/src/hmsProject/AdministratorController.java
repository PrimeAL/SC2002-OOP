package hmsProject;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Administrator Controller class that controls all interactions between Administrator class and other classes
 */
public class AdministratorController extends Controller{
	private Administrator currentAdm;

	/**
	 * AdminController class constructor
	 * @param maincont
	 * @param adm
	 */
	public AdministratorController(MainController maincont, Administrator adm) {
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
	
	public Inventory getInventory(){
		return this.getDataStorage().getInventory();
	}
	
	public ArrayList<User> getStaffs() {
		ArrayList<User> staffList = new ArrayList<User>();
		for (User user : this.getDataStorage().getUserList()) {
			if (user.getClass() == Doctor.class || user.getClass() == Pharmacist.class || user.getClass() == Administrator.class) {
				staffList.add(user);
			}
		}
		staffList.sort(Comparator.comparing(User::gethID , String.CASE_INSENSITIVE_ORDER));
		return staffList;
	}

	public void addNewStaff(User staffUser) {
		this.getDataStorage().addNewStaff(staffUser);
	}

	public void removeStaff(User staffUser) {
		this.getDataStorage().removeStaff(staffUser);
	}
	
	public void saveData() {
		this.getDataStorage().save();
	}
}
