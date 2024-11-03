package hmsProject;

public class MedicalRecord {

	public Diagnosis[] getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(Diagnosis[] diagnoses) {
		this.diagnoses = diagnoses;
	}

	public Treatment[] getTreatments() {
		return treatments;
	}

	public void setTreatments(Treatment[] treatments) {
		this.treatments = treatments;
	}

	private Diagnosis[] diagnoses;
	private Treatment[] treatments;
	
	/**
	 * 
	 * @param patient
	 */
	public static void updateDiagnosis(Patient patient) {
		// TODO - implement MedicalRecord.updateDiagnosis
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param patient
	 */
	public static void updateTreatment(Patient patient) {
		// TODO - implement MedicalRecord.updateTreatment
		throw new UnsupportedOperationException();
	}

}