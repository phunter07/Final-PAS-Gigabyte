package application;

public class HospitalManager extends Staff implements IManager {

	private int contactNum;
	private String email;

	public HospitalManager() {

	}

	public HospitalManager(String title, String firstName, String lastName, char gender, int staffID, String password, String role, String email,
			String telephone, String team) {
		super(title, firstName, lastName, gender, staffID, password, role, email, telephone, team);
	
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
