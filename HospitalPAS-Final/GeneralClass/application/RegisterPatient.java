package application;

/**
 * Class to allow the patients contained within the PAS to be registered to A and E
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RegisterPatient implements IReceptionist {

	/**
	 * Method to query the database and get and set the patients details
	 * 
	 * @param patient
	 * @return
	 */
	private boolean queryPAS(Patient patient) {
		boolean queryPAS = false;
		String url = Constants.DATABASE_URL;
		Connection con;
		Statement stmt;
		// loading driver
		try {
			Class.forName(Constants.DATABASE_CLASS);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		// making the connection
		try {
			con = DriverManager.getConnection(url, Constants.DATABASE_USERNAME,
					Constants.DATABASE_PASSWORD);
			// create a statement object
			stmt = con.createStatement();
			// supply the statement object with a string to execute

			// set these to constants
			ResultSet rs = stmt
					.executeQuery("select NHS_number,title, first_name,last_name,address,telephone,allergies,blood_group from PATIENT");
			while (rs.next()) {
				String nhs = rs.getString("NHS_number");
				if (nhs.equals(String.valueOf(patient.getNhsNumer()))) {
					patient.setTitle(rs.getString("title"));
					patient.setFirstName(rs.getString("first_name"));
					patient.setLastName(rs.getString("last_name"));
					patient.setAddress(rs.getString("address"));
					patient.setContactNum(rs.getString("telephone"));
					patient.setBloodGroup(rs.getString("blood_group"));
					patient.setAllergies(rs.getString("allergies"));
					queryPAS = true;
					break;
				} else {
					queryPAS = false;
				}
			}
			// close statement object
			stmt.close();
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return queryPAS;

	}

	/**
	 * Method to check if the patient has already been registered to A and E by
	 * the receptionist
	 * 
	 * @param patientList
	 * @param patient
	 * @return
	 */
	public boolean isPatientRegistered(List<Patient> patientList,
			Patient patient) {
		for (Patient pa : patientList) {
			if (pa.getNhsNumer() == patient.getNhsNumer()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to register the Patient to A and E
	 * 
	 * @param prePatientQueue
	 *            -the prePatientQueue in PAS
	 * @param newPatient
	 *            -the new patient
	 * @throws UserException
	 *             -in the case of fail
	 */
	@Override
	public void registerPatientToAandE(List<Patient> patientList,
			Patient newPatient) throws UserException {

		if (patientList.size() < Constants.PATIENT_LIMIT_IN_PAS) {

			// creating the patient object with the details obtained from the
			// queryPAS method
			if (queryPAS(newPatient)) {

				// if statement to check if the patient being searched for
				// hasn't been registered to a and e already - if not the
				// patient is
				// added to the patient list - calling method
				// isPatientRegistered
				if (!isPatientRegistered(patientList, newPatient)) {
					patientList.add(newPatient);
				} else {
					// needs to be changed
					throw new UserException("This patient has been registered");
				}
			} else {
				// new exceptions
				throw new UserException("Can not find in the NHS database");
			}
		} else {
			// new exceptions
			throw new UserException("Exceed the upper limited patient in queue");
		}
	}

}
