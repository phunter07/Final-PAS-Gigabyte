package application;

import java.util.Queue;

/**
 * interface containing the methods to allow both the Doctor and the TriageNurse
 * to categorise/recategorise a patient's condition
 * 
 * @author Hannah, Clare, Catherine
 *
 */

public interface ICategorise {

	/**
	 * method to initially categorise the patient when they arrive to the A and
	 * E - once the patient is categorised they are automatically added to the
	 * queue
	 * 
	 * @return
	 */
	public boolean categorisePatient(Patient patient,Triage category) throws UserException;

	/**
	 * method to change the category of the patient and automatically change
	 * their position in the queue - to be used by Triage Nurse and Doctor
	 * 
	 * @return
	 */
	public boolean recategorisePatient(Patient patient,Triage triage) throws UserException;

}