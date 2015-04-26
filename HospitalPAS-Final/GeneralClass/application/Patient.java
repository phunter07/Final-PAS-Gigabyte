package application;

import java.util.Date;

/**
 * Class to contain the details of the Patient
 * @author Paul
 *
 */
public class Patient extends Person {

	/**
	 * declaration of var NHS number
	 */
	private int nhsNumer;

	/**
	 * declaration of address
	 */
	private String address;

	/**
	 * declaration of var blood group
	 */
	private String bloodGroup;
	/**
	 * declaration of var contact number
	 */
	private String contactNum;

	/**
	 * declaration of triage category to be set by Tirage Nurse
	 */
	private Triage triage;

	/**
	 * declaration of allergies
	 */
	private String allergies;

	/**
	 * declaration date arriveTime to take in the current date and time of when
	 * the patient enters the queue - after being triaged
	 */
	private Date timePatientJoinsQueue;

	/**
	 * declaration of date leaveTime to take in the current date and time of
	 * when the patient leaves the queue and enters a treatment room
	 */
	private Date leaveTime;

	/**
	 * declaration of instance variable to calculate the waiting time of the
	 * patient in the queue
	 */
	private long waitingTime;

	/**
	 * default constructor
	 */
	public Patient() {
	}

	/**
	 * constructor with args
	 * 
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param nhsNumer
	 * @param bloodGroup
	 * @param contactNum
	 */
	public Patient(String title, String firstName, String lastName,
			char gender, int nhsNumer, String bloodGroup, String contactNum) {
		super(title, firstName, lastName, gender);
		this.nhsNumer = nhsNumer;
		this.bloodGroup = bloodGroup;
		this.contactNum = contactNum;
	}

	/**
	 * method to get the NHSNumber of the patient
	 * @return 
	 */
	public int getNhsNumer() {
		return nhsNumer;
	}

	/**
	 * 
	 * method to set the NHSNumber of the patient
	 * @param 
	 */
	public void setNhsNumer(int nhsNumer) {
		this.nhsNumer = nhsNumer;
	}

	/**
	 * method to get the patients blood group
	 * @return 
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * method to set the patients blood group
	 * @param string
	 */
	public void setBloodGroup(String string) {

		this.bloodGroup = string;
	}

	/**
	 * method to get the patients contact number
	 * @return 
	 */
	public String getContactNum() {
		return contactNum;
	}

	/**
	 * method to set the patients contact number
	 * @param contactNum
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	/**
	 * method to get the patients allergies
	 * @return
	 */
	public String getAllergies() {
		return allergies;
	}

	/**
	 * method to set the allergies
	 * @param allergies
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	/**
	 * method to get the traige category of the patient
	 * @return the triage
	 */
	public Triage getTriage() {
		return triage;
	}

	/**
	 * method to set the traige category of the patient
	 * @param triage
	 */
	public void setTriage(Triage triage) {
		this.triage = triage;
	}

	/**
	 * method to get the patients address
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * method to set the patients address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * method to get the arrival time of the patient into the queue
	 * @return
	 */
	public Date getTimePatientJoinsQueue() {
		return timePatientJoinsQueue;
	}

	/**
	 * method to set the arrival time of the patient into the queue
	 * @param arriveTime
	 */
	public void setTimePatientJoinsQueue(Date timePatientJoinsQueue) {
		this.timePatientJoinsQueue = timePatientJoinsQueue;
	}

	/**
	 * method to get the time the patient leaves the queue
	 * @return
	 */
	public Date getLeaveTime() {
		return leaveTime;
	}

	/**
	 * method to set the time the patient leaves the queue
	 * @param leaveTime
	 */
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	/**
	 * method to get the waiting time of patient
	 * @return double waitingTime
	 */

	/**
	 * @return the waitingTime
	 */
	public Long getWaitingTime() {
		if(this.getTimePatientJoinsQueue()!=null){
			this.waitingTime=new Date().getTime()-this.getTimePatientJoinsQueue().getTime();
			}else{
				this.waitingTime=0;
			}
		return this.waitingTime;
	}

	

}