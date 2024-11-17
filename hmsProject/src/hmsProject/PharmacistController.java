package hmsProject;

public class PharmacistController extends Controller{
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

	public void save() {
		this.getDataStorage().save();
	}

	public void addStockReq(StockRequest stockReq) {
		this.getDataStorage().addStockReq(stockReq);		
	}
}
