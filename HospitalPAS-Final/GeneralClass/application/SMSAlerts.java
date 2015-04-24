package application;

/**
 * @author Hannah McDade, Clare O'Toole, Catherine Geddis
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SMSAlerts implements IAlert {

	/**
	 * constant for Username for TxtLocal SMS sender
	 */
	public static final String USERNAME = "hmcdade767@gmail.com";

	/**
	 * constant to set the Hash Key for sending the SMS
	 */
	public static final String HASHKEY = "64007dc125b1e33d8204dd6c6aaf10ce83c12fb7";

	/**
	 * constant to set who the sender will appear to be when the SMS is sent
	 */
	public static final String SENDER = "Hospital Alert Sender";

	//to be pulled from database
	public static final String SEND_TO_NUMBER = "07718663689";

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
					"https://api.txtlocal.com/send/?").openConnection();

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
		String user = "username=" + USERNAME;
		String hash = "&hash=" + HASHKEY;
		String message = "&message=" + ON_CALL_SMS_ALERT;
		String sender = "&sender=" + SENDER;
		String numbers = "&numbers=" + SEND_TO_NUMBER;
		
		//calling the method to send the data
		sendData(user, hash, message, sender, numbers);
	}

	/**
	 * method to construct the SMS to the manager when the on call team is fully engaged in a and e 
	 */
	public void sendSSMSManagerOnCallFullyEngaged() {

		// Construct data from TxtLocal
		String user = "username=" + USERNAME;
		String hash = "&hash=" + HASHKEY;
		String message = "&message="
				+ MANAGER_SMS_ALERT_ON_CALL_TEAM_FULLY_ENGAGED;
		String sender = "&sender=" + SENDER;
		String numbers = "&numbers=" + SEND_TO_NUMBER;

		//calling the method to send the data
		sendData(user, hash, message, sender, numbers);
	}

	/**
	 * method to construct the SMS to send to the manager when there are two patients waiting 30 minutes or more
	 */
	public void sendSSMSManagerTwoPatientsWaitingThirtyMinutes() {

		// Construct data from TxtLocal
		String user = "username=" + USERNAME;
		String hash = "&hash=" + HASHKEY;
		String message = "&message="
				+ MANAGER_SMS_ALERT_TWO_PATIENTS_WAITING_30_MINUTES;
		String sender = "&sender=" + SENDER;
		String numbers = "&numbers=" + SEND_TO_NUMBER;

		// calling the method to send the data
		sendData(user, hash, message, sender, numbers);
	}

	
	
	@Override
	public void generateAndSendEmailOnCallFullyEngaged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateAndSendEmailPatientsWaitingThirtyMinutes() {
		// TODO Auto-generated method stub
		
	}
}
