package hmsProject;

/**
 * Controller superclass in which all User Controllers inherit from.
 */
public class Controller {
	private DataStorage dataStorage;

	/**
	 * Initialise DataStorage class.
	 */
	public Controller(){
		this.dataStorage=new DataStorage(); //initialisation
	}

	/**
	 * If DataStorage class is already initialised.
	 * @param mainCont Main Controller
	 */
	public Controller(MainController mainCont) { 	//for sub controllers
		this.dataStorage= mainCont.getDataStorage();
	}

	/**
	 * DataStorage class getter.
	 * @return DataStorage class.
	 */
	public DataStorage getDataStorage() {
		return dataStorage;
	}

}
