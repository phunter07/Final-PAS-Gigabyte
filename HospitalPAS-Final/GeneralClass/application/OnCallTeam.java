package application;
/**
 * the class represents on call team
 * @author Jiang Zhe Heng
 *
 */

public class OnCallTeam {

	/**
	 * declaration for patient in on call team
	 */
	private Patient patient;
	/**
	 * declaration for nurse
	 */
	private Nurse[] nurse;
	/**
	 * declaration for doctor
	 */
	private Doctor[] doctor;
	
	/**
	 * declaration for occupied
	 */
	private boolean isOccupied;
	public OnCallTeam() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the nurse
	 */
	public Nurse[] getNurse() {
		return nurse;
	}
	/**
	 * @param nurse the nurse to set
	 */
	public void setNurse(Nurse[] nurse) {
		this.nurse = nurse;
	}
	
	/**
	 * @return the doctor
	 */
	public Doctor[] getDoctor() {
		return doctor;
	}
	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor[] doctor) {
		this.doctor = doctor;
	}
	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
		setOccupied(true);
	}
	/**
	 * @return the occupied
	 */
	public boolean isOccupied() {
		return isOccupied;
	}
	/**
	 * @param occupied the occupied to set
	 */
	public void setOccupied(boolean occupied) {
		this.isOccupied = occupied;
	}
	/**
	 * remove patient
	 * @return
	 */
	public Patient removePatient(){
		Patient patient=getPatient();
		setPatient(null);
		setOccupied(false);
		return patient;
	}

}
