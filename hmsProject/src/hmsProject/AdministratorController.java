package hmsProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdministratorController extends controller{
	private Administrator currentAdm;
	
	public AdministratorController(MainController maincont, Administrator adm) {
		super(maincont);
		this.currentAdm=adm;
	}
	
	public AppointmentSystem getApptSys(){
		return this.getDataStorage().retrieveApptSys();
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


	public void saveData(){
		this.getDataStorage().save();
	}
}
