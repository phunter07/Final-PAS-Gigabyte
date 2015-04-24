package application;

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

	// to be pulled from database
	public static final String MANAGER_EMAIL_ADDRESS = "cotoole@qub.ac.uk";

	public static final String SUBJECT_MESSAGE = "Hospital Alert";
	
	public static final String SENDER = "hospitalsender@gmail.com";
	
	public static final String PASSWORD = "validPassword";
	
	public static final String SMTP = "smtp.gmail.com";
	
	static Properties mailServerProperties;

	static Session getMailSession;

	static MimeMessage generateMailMessage;
	
	
	
	public static void getProperties(){
		
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
	}
	
	public static void transport(Session getMailSession) throws AddressException, MessagingException{

		Transport transport = getMailSession.getTransport("smtp");
		// Enter your correct gmail UserID and Password
		transport.connect(SMTP, SENDER,
				PASSWORD);
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

		// Step2
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO,
				new InternetAddress(MANAGER_EMAIL_ADDRESS));
		generateMailMessage.addRecipient(Message.RecipientType.CC,
				new InternetAddress(MANAGER_EMAIL_ADDRESS));
		generateMailMessage.setSubject(SUBJECT_MESSAGE);
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
				new InternetAddress(MANAGER_EMAIL_ADDRESS));
		generateMailMessage.addRecipient(Message.RecipientType.CC,
				new InternetAddress(MANAGER_EMAIL_ADDRESS));
		generateMailMessage.setSubject(SUBJECT_MESSAGE);
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
