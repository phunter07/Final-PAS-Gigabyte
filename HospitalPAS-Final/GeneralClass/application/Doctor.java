package application;

public class Doctor extends Staff implements ILogin, IDoctor, ICategorise {

	/**
	 * Vars for the class
	 */
	private boolean statusAE;
	private boolean statusOnCall;

	/**
	 * Default constructor
	 */
	public Doctor() {

	}

	/**
	 * Constructor with args
	 */
	public Doctor(boolean statusAE, boolean statusOnCall) {
		statusAE = this.statusAE;
		statusOnCall = this.statusOnCall;
	}

	/**
	 * Boolean to see if staff is working A and E
	 * 
	 * @return statusAE
	 */
	public boolean isStatusAE() {
		return statusAE;
	}

	/**
	 * Setter method for statusAE
	 * 
	 * @param statusAE
	 */
	public void setStatusAE(boolean statusAE) {
		this.statusAE = statusAE;
	}

	/**
	 * Boolean to see if staff are on call
	 * 
	 * @return statusOnCall
	 */
	public boolean isStatusOnCall() {
		return statusOnCall;
	}

	/**
	 * Setter method for statusOnCall
	 * 
	 * @param statusOnCall
	 */
	public void setStatusOnCall(boolean statusOnCall) {
		this.statusOnCall = statusOnCall;
	}

	/**
	 * Method to allocate extra time to a patient
	 */
	@Override
	public void allocateExtraTime() {

	}

	/**
	 * method to initially categorise the patient when they arrive to the A and
	 * E - once the patient is categorised they are automatically added to the
	 * queue - only to be used by triage Nurse
	 * 
	 * @return
	 */
	@Override
	public boolean categorisePatient(Patient patient, Triage category)
			throws UserException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * method to change the category of the patient and automatically change
	 * their position in the queue
	 * 
	 * @return
	 */
	@Override
	public boolean recategorisePatient(Patient patient, Triage triage)
			throws UserException {
		// TODO Auto-generated method stub
		return false;
	}

}
