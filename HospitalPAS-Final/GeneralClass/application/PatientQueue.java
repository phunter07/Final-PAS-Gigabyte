package application;

/**
 * the class represents the queue of patient in PAS
 * @author Jiang Zhe Heng
 */
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PatientQueue extends LinkedList<Patient> {

	/**
	 * default Constructor
	 */
	public PatientQueue() {

	}

	/**
	 * Constructor with arguments Collection 'c' represents the patient list
	 * which is being used to control the queue
	 * 
	 * @param c
	 */
	public PatientQueue(Collection<? extends Patient> c) {
		super(c);
	}
	
	
	/**
	 * 
	 */
	/*
	@Override
	public boolean add(Patient patient){
		if (this.size() < Constants.PATIENT_LIMIT_IN_QUEUE) {
			super.add(patient);
			this.sort(new SortPatientTriageComparator());
			return true;
		} else {
			return false;
		}
	}
	*/

	/**
	 * removes the patient from the top of the queue once they have moved to the
	 * treatment room
	 * 
	 * @param patientQueue
	 * @return
	 */
	public Patient pollPatientFromQueue(Queue<Patient> patientQueue) {
		return patientQueue.poll();
	}

	/**
	 * method to allow a patient to be added to the treatment room once the
	 * treatmentroom is unoccupied
	 * 
	 * @param patient
	 * @param treatmentRoom
	 * @return
	 * @throws UserException
	 */
	public boolean addPatientToTreatmentRoom(Patient patient,
			TreatmentRoom treatmentRoom) throws UserException {
		if (treatmentRoom.isOccupied() == false) {
			treatmentRoom.setPatient(patient);
			treatmentRoom.setOccupied(true);
			return true;

		} else {
			throw new UserException(
					"Can not add the patient to the treatment room");
		}

	}

	/**
	 * calling the method from SMSAlerts Class to send an SMS to the On Call
	 * team when the queue reaches the maximum capacity of 10 patients
	 */
	public boolean queueAtMaximumCapacitySendSMSToOnCall() {

		// creating an instance of the class SMSAlerts
		SMSAlerts onCallAlerts = new SMSAlerts();

		// if statement to check the size of the queue and to send the SMS to
		// the OnCall Team
		if (this.size() >= Constants.PATIENT_LIMIT_IN_QUEUE) {

			onCallAlerts.sendSMSToOnCallTeam();
			return true;
		}
		return false;
	}
}