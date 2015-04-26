package application;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * class containing the details of the TiageNurse
 * 
 * @author Fin
 *
 */

public class NurseTriage extends Staff {

	/**
	 * default constructor
	 */
	public NurseTriage() {
	}

	/**
	 * constructor with arguments to obtain the details of the Triage Nurse
	 * 
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param staffID
	 * @param password
	 */
	public NurseTriage(String title, String firstName, String lastName,
			char gender, int staffID, String password) {
		super(title, firstName, lastName, gender, staffID, password);

	}

}
