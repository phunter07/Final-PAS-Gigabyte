package application;

import java.util.Date;

/**
 * 
 * @author Paul,Jiang Zheheng
 *
 */
public class Patient extends Person implements IDisplay{

	/**
	 * declaration of var NHS number
	 */
	private int nhsNumer;
	/**
	 * declaration of var blood group
	 */
	private String bloodGroup;
	/**
	 * declaration of var contact number
	 */
	private String contactNum;
	
	/**
	 * declaration of triage
	 */
	private Triage triage;
	
	/**
	 * declaration of allergies
	 */
	private String allergies;
	
	private long waitingTime;
	
	/**
	 * declaration of arriveTime
	 */
	private Date arriveTime;
	
	/**
	 * declaration of leaveTime
	 */
	private Date leaveTime;
	
	/**
	 * declaration of address
	 */
	private String address;
	

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
	 * 
	 * @return Patients NHS Number
	 */
	public int getNhsNumer() {
		return nhsNumer;
	}

	/**
	 * 
	 * @param nhsNumer
	 *            gets set
	 */
	public void setNhsNumer(int nhsNumer) {
		this.nhsNumer = nhsNumer;
	}

	/**
	 * 
	 * @return patients blood group
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * 
	 * @param string
	 *            gets set
	 */
	public void setBloodGroup(String string) {

		this.bloodGroup = string;
	}

	/**
	 * 
	 * @return patient contact number
	 */
	public String getContactNum() {
		return contactNum;
	}

	/**
	 * 
	 * @param contactNum
	 *            gets set
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return allergies;
	}

	/**
	 * @param allergies the allergies to set
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	/**
	 * overrides super display results method to show patients
	 */
	@Override
	public void displayAll() {
		super.displayAll();
		System.out.println("NHS Number: " + this.nhsNumer);
		System.out.println("Blood Group: " + this.bloodGroup);
		System.out.println("Contact Number: " + this.contactNum);
	}

	/**
	 * @return the triage
	 */
	public Triage getTriage() {
		return triage;
	}

	/**
	 * @param triage the triage to set
	 */
	public void setTriage(Triage triage) {
		this.triage = triage;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the arriveTime
	 */
	public Date getArriveTime() {
		return arriveTime;
	}

	/**
	 * @param arriveTime the arriveTime to set
	 */
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	/**
	 * @return the leaveTime
	 */
	public Date getLeaveTime() {
		return leaveTime;
	}

	/**
	 * @param leaveTime the leaveTime to set
	 */
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	/**
	 * @return the waitingTime
	 */
	public Long getWaitingTime() {
		if(this.getArriveTime()!=null){
			this.waitingTime=new Date().getTime()-this.getArriveTime().getTime();
			}else{
				this.waitingTime=0;
			}
		return this.waitingTime;
	}

	

}