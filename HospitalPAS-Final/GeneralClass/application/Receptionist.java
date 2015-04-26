package application;

public class Receptionist extends Staff {

	/**
	 * default constructor
	 */
	public Receptionist() {
			
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
	public Receptionist(String title, String firstName, String lastName, char gender,
			int staffID, String password) {
		super(title, firstName, lastName, gender, staffID, password);

	}
	
}
	

	