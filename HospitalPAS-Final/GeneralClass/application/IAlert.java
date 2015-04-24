package application;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * Interface to manage the Alerts 
 * @author Hannah, Catherine, Clare
 *
 */
public interface IAlert {
	
	/**
	 * method to construct the SMS message to the on call team when the queue capacity reaches 10
	 */
	public void sendSMSToOnCallTeam();
	
	/**
	 * method to construct the SMS to the manager when the on call team is fully engaged in a and e 
	 */
	public void sendSSMSManagerOnCallFullyEngaged();
	
	/**
	 * method to construct the SMS to send to the manager when there are two patients waiting 30 minutes or more
	 */
	public void sendSSMSManagerTwoPatientsWaitingThirtyMinutes();
	

	/**
	 * method to send the email to the Manager when on call team is fully engaged 
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void generateAndSendEmailOnCallFullyEngaged() throws AddressException, MessagingException; 
	
	/**
	 * method to generate and send email alerts to the Hospital Manager
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void generateAndSendEmailPatientsWaitingThirtyMinutes() throws AddressException, MessagingException;
	
	
	
	

}
