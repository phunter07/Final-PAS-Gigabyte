package application;

public abstract class Person implements IDisplay {

	private String title;
	private String firstName;
	private String lastName;
	private char gender;

	/**
	 * default constructor
	 */
	public Person() {

	}

	/**
	 * constructor with args
	 * 
	 * @param title
	 * @param firstName
	 * @param lastName
	 */
	public Person(String title, String firstName, String lastName, char gender) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	/**
	 * 
	 * @return title of person
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            gets set
	 */
	public void setTitle(String title) throws NullPointerException {
		if (title.isEmpty()) {
			throw new NullPointerException();
		} else {

			this.title = title;
		}
	}

	/**
	 * 
	 * @return persons first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            gets set
	 */
	public void setFirstName(String firstName) throws NullPointerException {
		if (firstName.isEmpty()) {
			throw new NullPointerException();
		} else {
			this.firstName = firstName;
		}
	}

	/**
	 * 
	 * @return last name of person
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            gets set
	 */
	public void setLastName(String lastName) throws NullPointerException {
		if (lastName.isEmpty()) {
			throw new NullPointerException();
		} else {
			this.lastName = lastName;
		}
	}

	/**
	 * 
	 * @return gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * gender as a char - must be M/m for male or F/f for female.
	 * 
	 * @param gender
	 * @throws IllegalArgumentException
	 *             if invalid input
	 */
	public void setGender(char gender) throws IllegalArgumentException {
		if ((gender == 'M') || (gender == 'F')) {
			this.gender = gender;
		} else if ((gender == 'm') || (gender == 'f')) {
			this.gender = gender;
		} else {
			throw new IllegalArgumentException("Wrong Gender Input");
		}
	}

	/**
	 * display results method
	 */
	@Override
	public void displayAll() {
		System.out.println("Title: " + this.title);
		System.out.println("First Name: " + this.firstName);
		System.out.println("Last Name: " + this.lastName);
		System.out.println("Gender: " + this.gender);
	}
}