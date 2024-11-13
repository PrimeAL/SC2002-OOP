package hmsProject;


public class MainController extends controller{
	public MainController() {
		//initialise the db
		super();
	}
	
	public User authenticateUser(String id, String pw) {
		return super.getDataStorage().getUser(id,pw);
	}

	public void save(User currentUser) {
		super.getDataStorage().saveUser(currentUser);
		super.getDataStorage().saveApptSys();
	}

	public controller getUserCont(User currentUser) {
		// TODO Auto-generated method stub
		if(currentUser instanceof Patient) {
			return new PatientController(this,(Patient)currentUser);
		}
		if(currentUser instanceof Doctor) {
			return new DoctorController(this,(Doctor)currentUser);
		}
		if(currentUser instanceof Pharmacist){
			return new PharmacistController(this,(Pharmacist)currentUser);
		}
		
		return this;
	}
	
}
