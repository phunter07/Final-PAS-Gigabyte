package application;

/**
 * 
 * @author Hannah
 *
 */

public enum Alerts {
	
	SMSALERTONCALLTEAM("On Call Team Needed in A and E. Queue capacity has reached the maximum"),
	SMSALERTMANAGERONCALLFULLYENGAGED("On Call team fully engaged.  Patients being redirected") ,
	EMAILALERTMANAGERONCALLFULLYENGAGED("On Call team fully engaged.  Patients being redirected"),
	EMAILALERTMANAGERWAITINGTIME("Two or more patients have been waiting over 30 minutes"),
	SMSALERTMANAGERWAITINGTIME("Two or more patients have been waiting over 30 minutes");

	private String alert;
	
	private Alerts(String alert){
		this.alert = alert;
	}
	
	public String getAlert(){
		return alert;
	}
	
	public void setAlert(String alert){
		this.alert = alert;
	}
	
}
