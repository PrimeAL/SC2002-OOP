package hmsProject;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Outcome Record after an appointment.
 */
public class OutcomeRecord implements Serializable {
    private String dateOfAppointment;
    private String serviceProvided;
    private ArrayList<PrescribedMed> meds;
    private String consultationNote;

    /**
     * OutcomeRecord constructor which creates empty medicine list.
     */
    public OutcomeRecord() {
        this.meds = new ArrayList<PrescribedMed>();
    }

    /**
     * OutcomeRecord constructor which initialises all attributes.
     * @param appt Completed Appointment
     */
    public OutcomeRecord(Appointment appt) {
        this.dateOfAppointment = appt.getDate();
        this.serviceProvided = null;
        this.meds = new ArrayList<PrescribedMed>();
        this.consultationNote = null;
    }

    /**
     * Manually input new OutcomeRecord.
     * @param cmptAppt Completed appointment
     * @return new OutcomeRecord.
     */
    public static OutcomeRecord createOutcomeRecord(Appointment cmptAppt, Inventory inventory) {
        OutcomeRecord newRecord = new OutcomeRecord(cmptAppt);
        Scanner sc = new Scanner(System.in);
        newRecord.setServiceProvided(sc);
        newRecord.setMeds(sc, inventory);
        newRecord.writeNote(sc);
        return newRecord;
    }

    /**
     * Appointment note input.
     * @param sc Scanner class for input
     */
    public void writeNote(Scanner sc) {
        System.out.println("What are the notes for this appointment?");
        this.consultationNote = sc.nextLine();
    }

    /**
     * Medicine to be prescribed input.
     * @param sc Scanner class for input
     */
    public void setMeds(Scanner sc, Inventory inventory) {
        System.out.println("What are the medicine(s) to be prescribed?");
        ArrayList<Medicine> availableMeds = inventory.getMedicine();

        while (true) {
            System.out.println("Available Medicines:");
            for (int i = 0; i < availableMeds.size(); i++) {
                System.out.println((i + 1) + ". " + availableMeds.get(i).getName());
            }
            System.out.println("Enter the medicine number to prescribe or 0 when done.");

            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= availableMeds.size()) {
                Medicine selectedMed = availableMeds.get(choice - 1);
                System.out.println("Enter the quantity for " + selectedMed.getName() + ":");
                int quantity;
                try {
                    quantity = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }
                this.meds.add(PrescribedMed.createPrescribedMed(selectedMed.getName(), quantity));
                System.out.println("Medicine " + selectedMed.getName() + " with quantity " + quantity + " added to the prescription.");
            } else {
                System.out.println("Invalid choice. Please enter a number between 0 and " + availableMeds.size() + ".");
            }
        }
    }

    /**
     * Appointment service provided input.
     * @param sc Scanner class for input
     */
    public void setServiceProvided(Scanner sc) {
        System.out.println("What is the service provided?");
        this.serviceProvided = sc.nextLine();
    }

    // Getters

    /**
     * Appointment date getter.
     * @return appointment date.
     */
    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    /**
     * Appointment service provided getter.
     * @return service provided.
     */
    public String getServiceProvided() {
        return serviceProvided;
    }

    /**
     * List of prescribed medicine getter.
     * @return prescribed medicine list.
     */
    public ArrayList<PrescribedMed> getMeds() {
    	return this.meds;
    }

    /**
     * Print all medicines prescribed.
     */
    public void printMeds() {  
    	System.out.println("\tMedicine List:");
        for(PrescribedMed preMed: this.getMeds()) {
        	System.out.println("\tMedication Name:"+preMed.getMedicationName()+" Status:"+preMed.getStatus()+" Quantity:"+preMed.getQuantity());
        }
    }

    /**
     * Appointment consultation note getter.
     * @return consultation note
     */
    public String getConsultationNote() {
        return consultationNote;
    }

    /**
     * Print everything.
     */
    public void printAll() {
    	System.out.println("Consultation Notes:"+this.consultationNote);
    	System.out.println("Date of Appointment:"+this.dateOfAppointment);
    	System.out.println("Service Provided:"+this.serviceProvided);
    	printMeds();
    }
}