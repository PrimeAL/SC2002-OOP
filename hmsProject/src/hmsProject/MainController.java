package hmsProject;


public class MainController extends Controller{
	public MainController() {
		//initialise the db
		super();
	}
	
	public User authenticateUser(String id, String pw) {
		return this.getDataStorage().getUser(id,pw);
	}

	public void save() {
		//super.getDataStorage().saveUser(currentUser);
		//super.getDataStorage().saveApptSys();
		this.getDataStorage().save();
	}

	public Controller getUserCont(User currentUser) {
		// TODO Auto-generated method stub
		if(currentUser instanceof Patient) {
			return new PatientController(this,(Patient)currentUser);
		}
		if(currentUser instanceof Doctor) {
			return new DoctorController(this,(Doctor)currentUser);
		}
		
		if(currentUser instanceof Administrator) {
			return new AdminController(this,(Administrator)currentUser);
		}

		if(currentUser instanceof Pharmacist){
			return new PharmacistController(this,(Pharmacist)currentUser);
		}

		return this;
	}
	
}
