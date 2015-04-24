package application;

public class HospitalManager extends Staff implements IManager {

	private int contactNum;
	private String email;

	public HospitalManager() {

	}

	public HospitalManager(String title, String firstName, String lastName,
			char gender, int staffID, String password, int contactNum,
			String email) {
		super(title, firstName, lastName, gender, staffID, password);
		this.contactNum = contactNum;
		this.email = email;
	}

	public int getContactNum() {
		return contactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactNum(int contactNum) {
		this.contactNum = contactNum;
	}

	@Override
	public void assignStaff() {
		// TODO Auto-generated method stub

	}

	@Override
	public void alerts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayAllStaff() {

	}

	@Override
	public void registerStaff() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayAll() {
		super.displayAll();
		System.out.println("Contact Number: " + this.contactNum);
		System.out.println("Email: " + this.email);
	}
}
