package hmsProject;

public class controller {
	private DataStorage dataStorage;
	
	public controller(){
		this.dataStorage=new DataStorage(); //initialisation
	}
	
	public controller(MainController mainCont) { 	//for sub controllers
		this.dataStorage= mainCont.getDataStorage();
	}

	public DataStorage getDataStorage() {
		return dataStorage;
	}

}
