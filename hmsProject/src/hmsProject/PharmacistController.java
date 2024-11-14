package hmsProject;

public class PharmacistController extends controller{
    private Pharmacist currentPharmacist;

    public PharmacistController(MainController mainCont, Pharmacist pharmacist){
        super(mainCont);
        this.currentPharmacist=pharmacist;
    }

    public AppointmentSystem getApptSys(){
		return this.getDataStorage().retrieveApptSys();
	}

    public Inventory getInventory(){
        return this.getDataStorage().getInventory();
    }
}
