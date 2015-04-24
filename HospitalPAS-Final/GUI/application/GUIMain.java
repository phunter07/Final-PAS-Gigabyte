package application;

/**
 * The class represent the application, application will start from this class
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GUIMain extends Application {

	public final static int REFRESHTIME = 1000;

	/**
	 * the limit number of treatment room,it is global variable
	 */
	public final static int NUMBERS_OF_ROOM = 1;
	/**
	 * the limit number of on call team
	 */
	public final static int NUMBERS_OF_ONCALLTEAM = 1;

	/**
	 * all treatment room in PAS,it is global variable
	 */
	public static TreatmentRoom[] treatmentRoom;

	/**
	 * all on call team in PAS,it is global variable
	 */
	public static List<OnCallTeam> onCallTeamList;
	/**
	 * patient queue in PAS,it is global variable
	 */
	public static PatientQueue patientQueue;

	/**
	 * all patient in PAS,it is global variable
	 */
	public static List<Patient> allPatientList;

	/**
	 * alert in PAS,it is global variable
	 */
	public static String alert;

	/**
	 * status in PAS,it is global variable
	 */
	public static Integer status;
	List<Staff> staffs = new ArrayList<Staff>();

	public static Patient nextPatient;
	public static Staff user;

	/**
	 * Initialize all global vars
	 * 
	 * @author Jiang Zhe Heng
	 */
	public void start(Stage primaryStage) {
		allPatientList = new ArrayList<Patient>();
		allPatientList = new ArrayList<Patient>();
		patientQueue = new PatientQueue();
		treatmentRoom = new TreatmentRoom[NUMBERS_OF_ROOM];
		for (int loop = 0; loop < NUMBERS_OF_ROOM; loop++) {
			GUIMain.treatmentRoom[loop] = new TreatmentRoom(loop, false);
		}
		onCallTeamList = new ArrayList<OnCallTeam>();
		for (int loop = 0; loop < NUMBERS_OF_ONCALLTEAM; loop++) {
			GUIMain.onCallTeamList.add(new OnCallTeam());
		}
		try {

			Parent root = FXMLLoader.load(getClass().getResource(
					"/application/LoginScreen.fxml"));

			Scene scene = new Scene(root, 450, 400);

			primaryStage.setTitle("FXML Welcome");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			threadStart();

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void threadStart() {
		// this thread is used to deal with every process need refresh every
		// seconds
		Thread freshThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {

						Thread.sleep(REFRESHTIME);
						refresh();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		freshThread.setDaemon(true);
		freshThread.start();
		// this thread is used to deal with send alert, it will refresh based on
		// REFRESHTIME until more than two patient wait more than 30 minutes
		Thread alertThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					try {
						Thread.sleep(REFRESHTIME);
						if (patientQueue
								.patientNumberWaitingMoreThanUpperMinutes() >= 2) {
							System.out.println("Add hospital manager send alert");

							Thread.sleep(5 * 60 * 1000);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
			}
		});
		alertThread.setDaemon(true);
		alertThread.start();

	}

	public void refresh() {
		patientQueue.sort(new SortPatientComparator());
		calculateStatus();
	}

	/**
	 * the method is used to get all staffs in PAS
	 * 
	 * @author Jiang Zhe Heng
	 * @return
	 */
	private static List<Staff> getAllStaff() {
		List<Staff> allStaff = new ArrayList<Staff>();
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
			Staff staff = new Staff();
			ResultSet rs = stmt.executeQuery("select * from STAFF");
			while (rs.next()) {
				staff.setStaffID(Integer.parseInt(rs.getString("STAFF_ID")));
				staff.setTitle(rs.getString("TITLE"));
				staff.setFirstName(rs.getString("FIRST_NAME"));
				staff.setLastName(rs.getString("LAST_NAME"));
				staff.setPassword(rs.getString("STAFF_PASSWORD"));
				staff.setRole(rs.getString("STAFF_ROLE"));
				staff.setTeam(rs.getString("STAFF_TEAM"));
				staff.setEmail(rs.getString("EMAIL_ADDRESS"));
				staff.setTelephone(rs.getString("TELEPHONE"));
				allStaff.add(staff);
			}
			// close statement object
			stmt.close();
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return allStaff;
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Calculate the waiting time
	 * 
	 * @author Jiang Zhe Heng
	 */
	public void calculateStatus() {
		if (patientQueue.size() <= 10) {
			// find the longest waiting time
			long longest = 0;
			for (Patient patient : patientQueue) {
				if (patient.getWaitingTime() > longest) {
					longest = patient.getWaitingTime() / 1000 / 60;
				}
			}
			if (longest >= 0 && longest < 10) {
				status = 1;
			} else if (longest >= 10 && longest < 20) {
				status = 2;
			} else if (longest >= 20) {
				status = 3;
			} else {
				status = 4;
			}
		} else {
			status = 4;
		}
	}
	
	public void TreatmentRoomThread(){
		
		
		
	}
}
