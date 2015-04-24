package application;

/**
 * class to contain the details of the nurse
 * 
 * @author Fin
 *
 */

public class Nurse extends Staff implements IStaffOnCall {

	/**
	 * default constructor
	 */
	public Nurse() {

	}

	/**
	 * constructor with arguments
	 * 
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param staffID
	 * @param password
	 */
	public Nurse(String title, String firstName, String lastName, char gender,
			int staffID, String password) {
		super(title, firstName, lastName, gender, staffID, password);

	}

	/**
	 * method to obtain whether the nurse is on call - to return a boolean value
	 * 
	 * @return
	 */
	@Override
	public boolean staffOnCall() {
		return false;

	}

	/**
	 * method to obtain if the nurse is on duty in A and E - to return a boolean
	 * value
	 * 
	 * @return
	 */
	@Override
	public boolean staffOnDuty() {
		return false;

	}

}
