package application;

/**
 * JDoe interface to allocate emergency details to a patient who arrives while
 * unconscious. To be implemented by TriageNurse
 * 
 * @author Hannah, Catherine, Clare
 *
 */

public interface IJDoe {

	/**
	 * method to allocate a first name to an unconscious patient
	 * 
	 * @return
	 */
	public String allocateFirstNameJDoe();

	/**
	 * method to allocat a last name to an unconscious patient
	 * 
	 * @return
	 */
	public String allocatelastNameJDoe();

	/**
	 * method to allocate a NHS Number to an unconscious patient - incrementing
	 * values
	 * 
	 * @return
	 */
	public int allocateNSHNumberJDoe();

}
