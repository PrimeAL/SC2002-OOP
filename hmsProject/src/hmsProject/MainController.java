package hmsProject;

/**
 * Main Controller.
 */
public class MainController extends Controller{
	public MainController() {
		//initialise the db
		super();
	}

	/**
	 * Checks if user is in database based on username and password.
	 * @param id unique user identifier
	 * @param pw password
	 * @return the user logging in, otherwise null.
	 */
	public User authenticateUser(String id, String pw) {
		return this.getDataStorage().getUser(id,pw);
	}

	/**
	 * Save everything.
	 */
	public void save() {
		//super.getDataStorage().saveUser(currentUser);
		//super.getDataStorage().saveApptSys();
		this.getDataStorage().save();
	}

	/**
	 * Create and return role-specific controllers.
	 * @param currentUser current user that is logged in
	 * @return user role controller.
	 */
	public Controller getUserCont(User currentUser) {
		if(currentUser instanceof Patient) {
			return new PatientController(this,(Patient)currentUser);
		}
		if(currentUser instanceof Doctor) {
			return new DoctorController(this,(Doctor)currentUser);
		}
		
		if(currentUser instanceof Administrator) {
			return new AdministratorController(this,(Administrator)currentUser);
		}

		if(currentUser instanceof Pharmacist){
			return new PharmacistController(this,(Pharmacist)currentUser);
		}

		return this;
	}
	
}
