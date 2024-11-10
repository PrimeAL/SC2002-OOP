package hmsProject;

public class AdministratorController extends controller{
	private Administrator currentAdministrator;
	
	public AdministratorController(MainController maincont, Administrator admin) {
		super(maincont);
		this.currentAdministrator = admin;
	}
	
 	public AppointmentSystem getApptSys() {
		return this.getDataStorage().getApptSys();
	}

	public Inventory getInventory() {
		return this.getDataStorage().getInventory();
	}
}
