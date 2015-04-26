package application;

import java.util.Comparator;

/**
 * The comparator class is used to take in the details of the queue and compare
 * waiting times for all patients in the A and E queue. If a patient has been
 * waiting over 25 minutes they are automatically bumped to the top of the queue
 *
 */
public class SortPatientTimeComparator implements Comparator<Patient> {

	/**
	 * creating an instance of the class SortPatientTraigeComparator to be used
	 * to compare times
	 */
	private SortPatientTriageComparator sortPatientTriage;

	/**
	 * default constructor for SortPatientComparator Class
	 */
	public SortPatientTimeComparator() {
	}

	/**
	 * Method to compare the argument of the Patients so that they can be
	 * ordered. It returns a negative integer, zero, or a positive integer if
	 * the first argument is less than, equal to, or greater than the second,
	 * respectively. This will automatically move the patient who has been
	 * waiting 25minutes or more to the top of the queue
	 */
	@Override
	public int compare(Patient p1, Patient p2) {
		if (p1.getWaitingTime() <= Constants.MOVE_TO_FRONT_MINUTES
				* Constants.MULTIPLY_MINUTES_TO_SECONDS
				&& p2.getWaitingTime() <= Constants.MOVE_TO_FRONT_MINUTES
						* Constants.MULTIPLY_MINUTES_TO_SECONDS) {
			int p1Triage = sortPatientTriage.convertTriage(p1);
			int p2Triage = sortPatientTriage.convertTriage(p2);
			if (Integer.compare(p1Triage, p2Triage) == 0) {
				return p1.getTimePatientJoinsQueue().compareTo(
						p2.getTimePatientJoinsQueue());
			} else {
				return Integer.compare(p1Triage, p2Triage);
			}
		} else {
			if (p1.getTimePatientJoinsQueue().compareTo(
					p2.getTimePatientJoinsQueue()) == 0) {
				int p1Triage = sortPatientTriage.convertTriage(p1);
				int p2Triage = sortPatientTriage.convertTriage(p2);
				return Integer.compare(p1Triage, p2Triage);
			} else {
				return p1.getTimePatientJoinsQueue().compareTo(
						p2.getTimePatientJoinsQueue());
			}
		}
	}

}
