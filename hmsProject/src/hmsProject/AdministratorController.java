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
	 * @param maincont Main Controller
	 * @param adm Admin
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
	 * @return Appointments list
	 */
	public ArrayList<Appointment> getScheduledAppts(){
		return this.getApptSys().getSchAppt();
	}

	/**
	 * Inventory getter.
	 * @return Inventory
	 */
	public Inventory getInventory(){
		return this.getDataStorage().getInventory();
	}

	/**
	 * Staff (Doctors, Pharmacists and Admin) list getter.
	 * @return list of Staffs
	 */
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

	/**
	 * Add new staff.
	 * @param staffUser staff to add
	 */
	public void addNewStaff(User staffUser) {
		this.getDataStorage().addNewStaff(staffUser);
	}

	/**
	 * Remove staff.
	 * @param staffUser staff to remove
	 */
	public void removeStaff(User staffUser) {
		this.getDataStorage().removeStaff(staffUser);
	}

	/**
	 * Save entire project.
	 */
	public void saveData() {
		this.getDataStorage().save();
	}
}
