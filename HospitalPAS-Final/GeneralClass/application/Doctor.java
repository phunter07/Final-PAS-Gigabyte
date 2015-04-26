package application;

public class Doctor extends Staff implements ILogin, IDoctor, ICategorise {


	/**
	 * Default constructor
	 */
	public Doctor() {

	}
	
	/**
	 * Method to allocate extra time to a patient
	 */
	@Override
	public void allocateExtraTime() {
		//cat has method for this 

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
		
		return false;
	}

}
