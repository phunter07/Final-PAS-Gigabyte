package application;

/**
 * @author Hannah McDade, Clare O'Toole, Catherine Geddis
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SMSAlerts implements IAlert {

	/**
	 * constant to set the SMS message to be sent to the on call team
	 */
	public static final String ON_CALL_SMS_ALERT = "On Call Team Needed in A and E. Queue capacity has reached the maximum";

	/**
	 * constant to set the message to be sent to the hospital manager when the on call team is fully engaged
	 */
	public static final String MANAGER_SMS_ALERT_ON_CALL_TEAM_FULLY_ENGAGED = "On Call team fully engaged.  Patients being redirected to nearest Hospital";

	/**
	 * constant to set the message to be sent to the hospital manager when two people in the queue have been waiting for 30 minutes
	 */
	public static final String MANAGER_SMS_ALERT_TWO_PATIENTS_WAITING_30_MINUTES = "Two or more patients have been waiting over 30 minutes";
	
	/**
	 * String to hold managerPhoneNumber
	 */
	private String managerPhoneNumber;
	
	/**
	 * String to hold first doctor on call telephone number
	 */
	private String firstDoctorOnCall;
	
	/**
	 * String to hold second doctor on call telephone number
	 */
	private String secondDoctorOnCall;
	
	/**
	 * String to hold first nurse on call telephone number
	 */
	private String firstNurseOnCall;
	
	/**
	 * String to hold second nurse on call telephone number
	 */
	private String secondNurseOnCall;
	
	/**
	 * String to hold third nurse on call telephone number
	 */
	private String thirdNurseOnCall;
	
	
	/**
	 * Method to connect to database and pull manager phone
	 *  number from staff table then set the String
	 *  managerPhoneNumber
	 * @param mangerPhoneNumber
	 * @return
	 */
	public String setManagerPhoneNumber(String mangerPhoneNumber) {
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
			con = DriverManager.getConnection(url, Constants.DATABASE_USERNAME, Constants.DATABASE_PASSWORD);
			// create a statement object
			stmt = con.createStatement();
			// supply the statement object with a string to execute
			ResultSet rs = stmt
					.executeQuery(Constants.DATABASE_MANAGERTELEPHONE_SELECT_QUERY);
			managerPhoneNumber=rs.getString(Constants.DATABASE_STAFF_TELEPHONE);
			// close statement object
			stmt.close();
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return managerPhoneNumber;
	

	}
	/**
	 * method to return managerPhoneNumber
	 * @return managerPhoneNumber
	 */
	public String getMangerPhoneNumber(){
		return managerPhoneNumber;
	}
	
	public String setFirstDoctorOnCallPhoneNumber(String firstDoctorOnCall) {
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
			con = DriverManager.getConnection(url, Constants.DATABASE_USERNAME, Constants.DATABASE_PASSWORD);
			// create a statement object
			stmt = con.createStatement();
			// supply the statement object with a string to execute
			ResultSet rs = stmt
					.executeQuery(Constants.DATABASE_FIRSTDOCTORTELEPHONE_SELECT_QUERY);
			firstDoctorOnCall=rs.getString(Constants.DATABASE_STAFF_TELEPHONE);
			// close statement object
			stmt.close();
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return firstDoctorOnCall;

	}
	
	/**
	 * 
	 * @return firstDoctorOnCall
	 */
	public String getFirstDoctorOnCallPhoneNumber(){
		return firstDoctorOnCall;
	}
	
	/**
	 * method to send the relevant SMS when called 
	 * @param user
	 * @param hash
	 * @param message
	 * @param sender
	 * @param numbers
	 * @return
	 */
	
	public String sendData(String user, String hash, String message,
			String sender, String numbers) {

		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(
					Constants.SMS_CONNECTION).openConnection();

			// data to be sent
			String data = user + hash + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length",
					Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			//user exception to go here 
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}

	/**
	 * method to construct the SMS message to the on call team when the queue capacity reaches 10
	 */
	public void sendSMSToOnCallTeam() {
		
		// Construct data from TxtLocal
		String user = "username=" + Constants.SMS_USERNAME;
		String hash = "&hash=" + Constants.SMS_HASHKEY;
		String message = "&message=" + ON_CALL_SMS_ALERT;
		String sender = "&sender=" + Constants.SMS_SENDER;
		String number1 = "&numbers=" + firstDoctorOnCall;
		String number2="&numbers="+secondDoctorOnCall;
		String number3="&numbers="+firstNurseOnCall;
		String number4="&numbers="+secondNurseOnCall;
		String number5="&numbers="+thirdNurseOnCall;
		
		//calling the method to send the data to all 5 on call staff
		sendData(user, hash, message, sender, number1);
		sendData(user, hash, message, sender, number2);
		sendData(user, hash, message, sender, number3);
		sendData(user, hash, message, sender, number4);
		sendData(user, hash, message, sender, number5);
	}

	/**
	 * method to construct the SMS to the manager when the on call team is fully engaged in a and e 
	 */
	public void sendSSMSManagerOnCallFullyEngaged() {

		// Construct data from TxtLocal
		String user = "username=" + Constants.SMS_USERNAME;
		String hash = "&hash=" + Constants.SMS_HASHKEY;
		String message = "&message="
				+ MANAGER_SMS_ALERT_ON_CALL_TEAM_FULLY_ENGAGED;
		String sender = "&sender=" + Constants.SMS_SENDER;
		String numbers = "&numbers=" + managerPhoneNumber;

		//calling the method to send the data
		sendData(user, hash, message, sender, numbers);
	}

	/**
	 * method to construct the SMS to send to the manager when there are two patients waiting 30 minutes or more
	 */
	public void sendSSMSManagerTwoPatientsWaitingThirtyMinutes() {

		// Construct data from TxtLocal
		String user = "username=" + Constants.SMS_USERNAME;
		String hash = "&hash=" + Constants.SMS_HASHKEY;
		String message = "&message="
				+ MANAGER_SMS_ALERT_TWO_PATIENTS_WAITING_30_MINUTES;
		String sender = "&sender=" + Constants.SMS_SENDER;
		String numbers = "&numbers=" + managerPhoneNumber;

		// calling the method to send the data
		sendData(user, hash, message, sender, numbers);
	}

	
	/**
	 * method to send the Email Alert - to be used in ManagerEmailALert Class
	 */
	@Override
	public void generateAndSendEmailOnCallFullyEngaged() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * method to send the Email Alert - to be used in ManagerEmailALert Class
	 */
	@Override
	public void generateAndSendEmailPatientsWaitingThirtyMinutes() {
		// TODO Auto-generated method stub
		
	}
}