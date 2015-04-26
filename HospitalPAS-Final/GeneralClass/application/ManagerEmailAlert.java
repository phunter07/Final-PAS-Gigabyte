package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * class to send the email alerts to the hospital manager
 * 
 * @author Catherine Geddis, Hannah McDade, Clare O'Toole
 * 
 */

public class ManagerEmailAlert implements IAlert {


	public static final String MANAGER_EMAIL_ALERT_ON_CALL_TEAM_FULLY_ENGAGED = "On Call team fully engaged.  Patients being redirected to nearest Hospital";

	
	public static final String MANAGER_EMAIL_ALERT_TWO_PATIENTS_WAITING_30_MINUTES = "Two or more patients have been waiting over 30 minutes";


	/**
	 * String to hold the managerEmailAddres once pulled from database
	 */
	private static String managerEmailAddress;
	
	
	static Properties mailServerProperties;

	static Session getMailSession;

	static MimeMessage generateMailMessage;
	
	/**
	 * method to connect to database and pull the managers email address
	 * from the staff table this method then sets the manager email address
	 * to be used when sending alerts via email
	 * @param mangerEmailAddress
	 * @return
	 */
	public String setManagerEmailAddress(String mangerEmailAddress) {
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
			stmt = con.createStatement();
			// supply the statement object with a string to execute
			ResultSet rs = stmt
					.executeQuery(Constants.DATABASE_MANAGEREMAIL_SELECT_QUERY);
			managerEmailAddress=rs.getString(Constants.DATABASE_STAFF_EMAIL);
			// close statement object
			stmt.close();
			// close connection
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return managerEmailAddress;
	

	}
	/**
	 * method to return managerEmailAddress
	 * @return
	 */
	public String getMangerEmailAddress(){
		return managerEmailAddress;
	}
	
	
	public static void getProperties(){
		
		
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
	}
	
	/**
	 * method to send email 
	 * @param getMailSession
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void transport(Session getMailSession) throws AddressException, MessagingException{

		Transport transport = getMailSession.getTransport("smtp");
		transport.connect(Constants.EMAIL_SENDER_SMTP, Constants.EMAIL_SENDER,
				Constants.EMAIL_SENDER_PASSWORD);
		transport.sendMessage(generateMailMessage,
				generateMailMessage.getAllRecipients());
		transport.close();
		
	}

	/**
	 * method to generate and send email alerts to the Hospital Manager
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void generateAndSendEmailPatientsWaitingThirtyMinutes() throws AddressException,
			MessagingException {

		getProperties();

		
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO,
				new InternetAddress(managerEmailAddress));
		generateMailMessage.addRecipient(Message.RecipientType.CC,
				new InternetAddress(managerEmailAddress));
		generateMailMessage.setSubject(Constants.EMAIL_SUBJECT_MESSAGE);
		String emailBody = MANAGER_EMAIL_ALERT_TWO_PATIENTS_WAITING_30_MINUTES;
		generateMailMessage.setContent(emailBody, "text/html");

		
		transport(getMailSession);
	}

	/**
	 * method to send the email to the Manager when on call team is fully engaged 
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void generateAndSendEmailOnCallFullyEngaged() throws AddressException,
			MessagingException {

		//calling method to get the properties 
		getProperties();

		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO,
				new InternetAddress(managerEmailAddress));
		generateMailMessage.addRecipient(Message.RecipientType.CC,
				new InternetAddress(managerEmailAddress));
		generateMailMessage.setSubject(Constants.EMAIL_SUBJECT_MESSAGE);
		String emailBody = MANAGER_EMAIL_ALERT_ON_CALL_TEAM_FULLY_ENGAGED;
		generateMailMessage.setContent(emailBody, "text/html");

		//calling the method to transport the email message
		transport(getMailSession);
		
	}

	@Override
	public void sendSMSToOnCallTeam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendSSMSManagerOnCallFullyEngaged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendSSMSManagerTwoPatientsWaitingThirtyMinutes() {
		// TODO Auto-generated method stub
		
	}
}
