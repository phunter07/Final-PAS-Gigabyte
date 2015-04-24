package application;

/**
 * the class represents the queue of patient in PAS
 * @author Jiang Zhe Heng
 */
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class PatientQueue extends LinkedList<Patient> {

	private static final long serialVersionUID = 1L;

	/**
	 * constant to declare the patient limit in the queue
	 */
	private static final int PATIENT_LIMIT_IN_QUEUE = 10;
	
	private static final int UPPERMINUTES = 30;

	@Override
	public boolean add(Patient patient) {
		if (this.size() < PATIENT_LIMIT_IN_QUEUE) {
			super.add(patient);
			this.sort(new SortPatientComparator());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * calculate the number of patient waiting more than UpperMinutes
	 * @param patientQueue
	 * @return
	 */
	public int patientNumberWaitingMoreThanUpperMinutes(){
		int number=0;
		for(Patient patient:this){
			if(patient.getWaitingTime()>=UPPERMINUTES){
				number++;
			}
		}
		return number;
	}
	/**
	 * method to send an SMS to the On Call team when the queue reaches maximum capacity
	 */
	public boolean sendSMS(){
		
		//creating an instance of the class SMSAlerts
		SMSAlerts onCallAlerts = new SMSAlerts();
		
		//if statement to check the size of the queue and to send the SMS to the OnCall Team
		if(this.size()>=PATIENT_LIMIT_IN_QUEUE-1){

			onCallAlerts.sendSMSToOnCallTeam();
			return true;
		}
		return false;
	}

	public PatientQueue() {
		// TODO Auto-generated constructor stub
	}

	public PatientQueue(Collection<? extends Patient> c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

}
