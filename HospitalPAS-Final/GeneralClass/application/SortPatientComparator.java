package application;

import java.util.Comparator;

/**
 * The comparator class is used to compare patient, the more urgent patient is
 * allocated to the front
 * 
 * @author Jiang Zhe Heng
 *
 */
public class SortPatientComparator implements Comparator<Patient> {

	public final int PATIENT_LIMIT_IN_QUEUE = 10;

	public final int UPPERMINUTES = 30;

	public final int MOVE_TO_FRONT_MINUTES = 25;
	
	public final int MULTIPLY_MINUTES_TO_SECONDS = 60*1000;

	public SortPatientComparator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     * @author Jiang Zhe Heng
	 */
	@Override
	public int compare(Patient p1, Patient p2) {
		if (p1.getWaitingTime() <= MOVE_TO_FRONT_MINUTES *  MULTIPLY_MINUTES_TO_SECONDS
				&& p2.getWaitingTime() <= MOVE_TO_FRONT_MINUTES *  MULTIPLY_MINUTES_TO_SECONDS) {
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
	 * the method is used to covert triage to the level, so that it can be
	 * sorted.
	 * 
	 * @param patient
	 * @return
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
