package hmsProject;

public class Pharmacist extends User {
	private String name;
	private String gender;
	private int age;

	public Pharmacist(String uid, String pw, String name, String gender, int age) {
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
	 */
	public void userInterface(AppointmentSystem apptSys, Inventory inven) {
		// TODO - implement Pharmacist.userInterface
		throw new UnsupportedOperationException();
	}

	public void apptOp() {
		// TODO - implement Pharmacist.apptOp
		throw new UnsupportedOperationException();
	}

}