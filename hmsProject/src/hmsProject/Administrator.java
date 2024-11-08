package hmsProject;

public class Administrator extends User {
	private String name;
	private String gender;
	private int age;

	public Administrator(String uid, String pw, String name, String gender, int age) {
		super(uid, pw);
		this.name=name;
		//Remember getter setters
		this.gender=gender;
		this.age=age;
	}

	/**
	 * 
	 * @param apptSys
	 * @param inven
	 * @param users
	 */
	public void userInterface(AppointmentSystem apptSys, Inventory inven, User[] users) {
		// TODO - implement Administrator.userInterface
		throw new UnsupportedOperationException();
	}

	public void manageStaff() {
		// TODO - implement Administrator.manageStaff
		throw new UnsupportedOperationException();
	}

	public void apptOp() {
		// TODO - implement Administrator.apptOp
		throw new UnsupportedOperationException();
	}

}