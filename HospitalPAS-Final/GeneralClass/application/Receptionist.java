package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Queue;

public class Receptionist extends Staff implements IReceptionist {

	private final int PATIENT_LIMIT_IN_PAS = 100;


	@Override
	public void queryDatabase() {
		// TODO Auto-generated method stub

	}

	/**
	 * remove patient from system, the list of whole patients in PAS and the
	 * patient needed to be removed should be introduced
	 * @author Jiang Zhe Heng
	 * @param patients
	 * @param patient
	 * @return
	 */
	public Patient removePatientFromSystem(List<Patient> patients,
			Patient patient) {
		Date now = new Date();
		patient.setLeaveTime(now);
		patients.remove(patient);
		return patient;
	}

	/**
	 * poll patient from queue, the patient in queue should be introduced
	 * @author Jiang Zhe Heng
	 * @param patientQueue
	 * @return
	 */
	public Patient pollPatientFromQueue(Queue<Patient> patientQueue) {
		return patientQueue.poll();
	}

	/**
	 * This method is used to register patient to A&E.
	 * @author Jiang Zhe Heng
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
		if (patientList.size() < PATIENT_LIMIT_IN_PAS) {
			if (hasPatient(newPatient)) {
				if (!isInThePAS(patientList, newPatient)) {
					newPatient.setArriveTime(new Date());
					patientList.add(newPatient);
				} else {
					throw new UserException("This patient has been registered");
				}

			} else {
				throw new UserException("Can not find in the NHS database");
			}
		} else {
			throw new UserException("Exceed the upper limited patient in queue");
		}

	}

	/**
	 * The method is used to check if the incoming parameter of patient is in
	 * the list of all patients in PAS, if yes return true,or return false.
	 * @author Jiang Zhe Heng
	 * @param patientList
	 * @param patient
	 * @return
	 */
	public boolean isInThePAS(List<Patient> patientList, Patient patient) {
		for (Patient pa : patientList) {
			if (pa.getNhsNumer() == patient.getNhsNumer()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check patient, if the database has patient it returns true and set the
	 * information of patient,if not it returns false
	 * @author Jiang Zhe Heng
	 * @param patient
	 * @return
	 */
	private boolean hasPatient(Patient patient) {
		boolean hasPatient = false;
		String url = "jdbc:mysql://web2.eeecs.qub.ac.uk/40108307";
		Connection con;
		Statement stmt;
		// loading driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		// making the connection
		try {
			con = DriverManager.getConnection(url, "40108307", "CZB6355");
			// create a statement object
			stmt = con.createStatement();
			// supply the statement object with a string to execute
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
					patient.setArriveTime(new Date());;
					hasPatient = true;
					break;
				} else {
					hasPatient = false;
				}
			}
			// close statement object
			stmt.close();
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return hasPatient;

	}

	/**
	 * add patient into treatment room, if it is added successfully return true,
	 * or return false
	 * @author Jiang Zhe Heng
	 * @param patient
	 * @param treatmentRoom
	 * @return
	 * @throws UserException
	 */
	public boolean addPatientToTreatmentRoom(Patient patient,
			TreatmentRoom treatmentRoom) throws UserException {
		if (treatmentRoom.isOccupied() == false) {
			treatmentRoom.setPatient(patient);
			treatmentRoom.setOccupied(true);
			return true;
		} else {
			throw new UserException(
					"Can not add the patient to the treatment room");
		}

	}

	/**
	 * the method is used by receptionist to find empty treatment room, if the
	 * empty treatment room is found, return the empty treatment room, or return
	 * null
	 * @author Jiang Zhe Heng
	 * @return
	 */
	public TreatmentRoom findEmptyTreatmentRoom(TreatmentRoom[] rooms) {
		for (int loop = 0; loop < rooms.length; loop++) {
			if (GUIMain.treatmentRoom[loop].isOccupied() == false) {
				return GUIMain.treatmentRoom[loop];

			}
		}
		return null;
	}

}
