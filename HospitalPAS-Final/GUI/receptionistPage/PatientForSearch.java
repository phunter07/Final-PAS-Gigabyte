/**
 * @author Paul 40133443
 */

package receptionistPage;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class PatientForSearch {
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty nhsNumber;
	private final StringProperty address;
	private final StringProperty bloodGroup;
	private final StringProperty contactNumber;

	

	public PatientForSearch(String nhsNumber, String firstName,
			String lastName, String address, String contactNumber,
			String bloodGroup) {
		this.nhsNumber = new SimpleStringProperty(nhsNumber);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		this.address = new SimpleStringProperty(address);
		this.contactNumber = new SimpleStringProperty(contactNumber);
		this.bloodGroup = new SimpleStringProperty(bloodGroup);
	}

	

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getNhsNumber() {
		return nhsNumber.get();
	}

	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber.set(nhsNumber);
	}

	public StringProperty nhsNumberProperty() {
		return nhsNumber;
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public StringProperty addressProperty() {
		return address;
	}

	public String getBloodGroup() {
		return bloodGroup.get();
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup.set(bloodGroup);
	}

	public StringProperty bloodGroupProperty() {
		return bloodGroup;
	}

	public String getContactNumber() {
		return contactNumber.get();
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber.set(contactNumber);
	}

	public StringProperty contactNumberProperty() {
		return contactNumber;
	}

}
