package hmsProject;
import java.util.Objects;
import java.util.Scanner;

public class HMS {

	private MainController mainController;
	private User currentUser;
	
	public HMS() {
		this.mainController=new MainController();
		this.currentUser=null;
	}
	
	/**
	 * @param sc
	 */
	public void initialise(Scanner sc) {
		// TODO - implement HMS.initialise
		while(true) {
			while(currentUser==null) {
				this.login(sc);
			}
			
			controller userCont=mainController.getUserCont(currentUser);
			if(currentUser instanceof Patient) {
				System.out.println("Patient");
				((Patient)(currentUser)).userInterface((PatientController)userCont, sc); 
			}
			if(currentUser instanceof Doctor) {
				System.out.println("Doctor");
				((Doctor)(currentUser)).userInterface((DoctorController)userCont, sc); 
			}
			if(currentUser instanceof Administrator) {
				System.out.println("Admin");
				((Administrator)(currentUser)).userInterface((AdministratorController)userCont, sc); 
			}
			if(currentUser instanceof Pharmacist){
				System.out.println("Pharmacist");
				((Pharmacist)(currentUser)).userInterface((PharmacistController)userCont, sc);  
			}
		
			//exit of user interface means logout
			this.getMainController().save();
			currentUser=null;
		}
	}

	private void login(Scanner sc) {
		// TODO - implement HMS.login
		System.out.println("1.Login\n2.Exit");
		if(sc.nextInt()==1) {
			sc.nextLine();//clear buffer
			System.out.print("Username:");
			String userName=sc.nextLine();
			System.out.print("Password:");
			String pw=sc.nextLine();
			try {
			this.currentUser=this.mainController.authenticateUser(userName,pw);
			if (Objects.equals(currentUser.getPw(), "default")) {
				currentUser.changePW(sc);
				this.getMainController().save();
			}
			} catch (Exception e) {
				System.out.println("Authentication failed, please try again");
			}
		}
		else {
			System.exit(0);
		}
		
	}

	public MainController getMainController() {
		return this.mainController;
	}
}