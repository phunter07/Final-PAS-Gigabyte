package application;

/**
 * class to contain the details of the nurse
 * 
 * @author Fin
 *
 */

public class Nurse extends Staff {

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


}
