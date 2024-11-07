package hmsProject;
import java.util.Scanner;

public class HMS {

	private MainController mainController;
	private User currentUser;
	
	public HMS() {
		this.mainController=new MainController();
		this.currentUser=null;
	}
	
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
			//exit of user interface means logout
			getMainController().save(currentUser);
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
			this.currentUser=this.mainController.authenticateUser(userName,pw);
		}
		else {
			System.exit(0);
		}
		
	}

	public MainController getMainController() {
		return this.mainController;
	}

	public void stop() {
		// TODO - implement HMS.stop
		throw new UnsupportedOperationException();
	}
	
	public void logout() {
		// TODO - implement HMS.logout
		throw new UnsupportedOperationException();
	}

}