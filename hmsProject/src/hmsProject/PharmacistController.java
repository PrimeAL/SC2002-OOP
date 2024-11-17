package hmsProject;

/**
 * Pharmacist Controller.
 */
public class PharmacistController extends Controller{
    private Pharmacist currentPharmacist;

    /**
     * Pharmacist Controller constructor.
     * @param mainCont Main Controller
     * @param pharmacist pharmacist
     */
    public PharmacistController(MainController mainCont, Pharmacist pharmacist){
        super(mainCont);
        this.currentPharmacist=pharmacist;
    }

    /**
     * Appointment System getter.
     * @return AppointmentSystem.
     */
    public AppointmentSystem getApptSys(){
		return this.getDataStorage().retrieveApptSys();
	}

    /**
     * Inventory getter.
     * @return Inventory.
     */
    public Inventory getInventory(){
        return this.getDataStorage().getInventory();
    }

    /**
     * Save everything.
     */
	public void save() {
		this.getDataStorage().save();
	}

    /**
     * Add StockRequest
     * @param stockReq
     */
	public void addStockReq(StockRequest stockReq) {
		this.getDataStorage().addStockReq(stockReq);		
	}
}
