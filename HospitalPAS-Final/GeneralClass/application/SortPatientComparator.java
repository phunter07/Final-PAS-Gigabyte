package application;

import java.util.Comparator;

/**
 * The comparator class is used to compare patient, the patient triage category
 * and the patient waiting time in the queue in order to reoragnise the queue
 * according to the requirements
 * 
 * @author Jiang Zhe Heng
 *
 */
public class SortPatientComparator implements Comparator<Patient> {

	/**
	 * default constructor for SortPatientComparator Class
	 */
	public SortPatientComparator() {
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
			int p1Triage = convertTriage(p1);
			int p2Triage = convertTriage(p2);
			if (Integer.compare(p1Triage, p2Triage) == 0) {
				return p1.getArriveTime().compareTo(p2.getArriveTime());
			} else {
				return Integer.compare(p1Triage, p2Triage);
			}
		} else {
			if (p1.getArriveTime().compareTo(p2.getArriveTime()) == 0) {
				int p1Triage = convertTriage(p1);
				int p2Triage = convertTriage(p2);
				return Integer.compare(p1Triage, p2Triage);
			} else {
				return p1.getArriveTime().compareTo(p2.getArriveTime());
			}
		}
	}

	/**
	 * Method containing a switch statement to allow the Triage Category of each
	 * patient to be changed to an integer value to allow the queue to be sorted
	 */
	public int convertTriage(Patient patient) {

		int triage;

		switch (patient.getTriage()) {
		case EMERGENCY:
			triage = Triage.EMERGENCY.getLevel();
			break;
		case URGENT:
			triage = Triage.URGENT.getLevel();
			break;
		case SEMI_URGENT:
			triage = Triage.SEMI_URGENT.getLevel();
			break;
		case NON_URGENT:
			triage = Triage.NON_URGENT.getLevel();
			break;
		default:
			triage = 0;
		}
		return triage;
	}

}
