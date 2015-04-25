package application;

public class Constants {

	/**
	 * Constant to set the Refresh time of the Thread (in miliseconds)
	 */
	public final static int REFRESHTIME = 1000;
	
	/**
	 *Constant to limit the number of patients in the PAS in order to control risks 
	 */
	public final static int PATIENT_LIMIT_IN_PAS = 100;
	
	/**
	 * Constant for the URL in order to establish the database connection
	 */
	public static final String DATABASE_URL = "jdbc:mysql://web2.eeecs.qub.ac.uk/40108307";
	
	/**
	 * Constant to get the class where the Database is stored
	 */
	public static final String DATABASE_CLASS = "com.mysql.jdbc.Driver";
	
	/**
	 * Constant for the username to access the database
	 */
	public static final String DATABASE_USERNAME = "40108307";
	
	/**
	 * Constant for the password to access the database
	 */
	public static final String DATABASE_PASSWORD = "CZB6355";
	
	/**
	 * Constant to hold the database query to access the staff details within the database
	 */
	public static final String DATABASE_STAFF_SELECT_QUERY = "select * from STAFF";
	
	/**
	 * Constant to hold the database query to access the doctor on call telephone number
	 */
	public static final String DATABASE_FIRSTDOCTORTELEPHONE_SELECT_QUERY="select telephone from STAFF where staff_id=1005";
	
	/**
	 * Constant to hold the database query to access the nurse on call telephone number
	 */
	public static final String DATABASE_NURSETELEPHONE_SELECT_QUERY="select telephone from STAFF where staff_team=onCall and staff_role=nurse";
	
	/**
	 * Constant containing the STAFF_ID key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_ID = "STAFF_ID";
	
	/**
	 * Constant containing the STAFF_TITLE key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_TITLE= "TITLE";
	
	/**
	 * Constant containing the STAFF_PASSWORD key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_PASSWORD = "STAFF_PASSWORD";
	
	/**
	 * Constant containing the STAFF_FIRST_NAME key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_FIRST_NAME = "FIRST_NAME";
	
	/**
	 * Constant containing the STAFF_LAST_NAME key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_LAST_NAME = "LAST_NAME";
	
	/**
	 * Constant containing the STAFF_ROLE key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_ROLE = "STAFF_ROLE";
	
	/**
	 * Constant containing the STAFF_TEAM key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_TEAM = "STAFF_TEAM";
	
	/**
	 * Constant containing the STAFF_EMAIL key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_EMAIL = "EMAIL_ADDRESS";
	
	/**
	 * Constant containing the STAFF_TELEPHONE key to be accessed in the database
	 */
	public static final String DATABASE_STAFF_TELEPHONE = "TELEPHONE";
	
	/**
	 * Constant containing the MANANGERTELEPHONE_SELECT_QUERY to be executed
	 */
	public static final String DATABASE_MANAGERTELEPHONE_SELECT_QUERY="select telephone from STAFF where staff_role=Hospital Manager";
	
	/**
	 * Constant containing the MANAGEREMAIL_SELECT_QUERY to be executed
	 */
	public static final String DATABASE_MANAGEREMAIL_SELECT_QUERY="select email_address from STAFF where staff_role=Hospital Manager";
	/**
	 * Constant to control the upper limit of the patient queue
	 */
	public static final int PATIENT_LIMIT_IN_QUEUE = 10;
	
	/**
	 * Constant in order to allow the patient waiting time to be converted to minutes
	 */
	public static final int MULTIPLY_MINUTES_TO_SECONDS = 60;
	
	/**
	 * Constant to determine the number of minutes a patient has been waiting in the queue before they have to be moved to the front
	 */
	public static final int MOVE_TO_FRONT_MINUTES = 25;
	
	/**
	 * constant for Username for TxtLocal SMS sender
	 */
	public static final String SMS_USERNAME = "hmcdade767@gmail.com";
	
	/**
	 * constant to set the Hash Key for sending the SMS
	 */
	public static final String SMS_HASHKEY = "64007dc125b1e33d8204dd6c6aaf10ce83c12fb7";
	
	/**
	 * constant to set who the sender will appear to be when the SMS is sent
	 */
	public static final String SMS_SENDER = "Hospital Alert Sender";
	
	/**
	 * Constant to establish the connection in order to send the SMS
	 */
	public static final String SMS_CONNECTION = "https://api.txtlocal.com/send/?";
	
	/**
	 * Constant to set the upper limit of the waiting time in the queue in minutes
	 */
	public static final int UPPERMINUTES_QUEUE_LIMIT = 30;
	
	/**
	 * Constant to set the subject of the email to be sent to the hospital manager
	 */
	public static final String EMAIL_SUBJECT_MESSAGE = "Hospital Alert";
	
	/**
	 * constant to hold the email address of the send of the email alert
	 */
	public static final String EMAIL_SENDER = "hospitalsender@gmail.com";
	
	/**
	 * constant to hold the password of the email alert sender
	 */
	public static final String EMAIL_SENDER_PASSWORD = "validPassword";
	
	/**
	 * constant to contain the SMTP of the email alert sender class
	 */
	public static final String EMAIL_SENDER_SMTP = "smtp.gmail.com";
	
	/**
	 * Constant for the Number of Treatment rooms within the Hospital
	 */
	public final static int NUMBERS_OF_ROOM = 5;
	
}
