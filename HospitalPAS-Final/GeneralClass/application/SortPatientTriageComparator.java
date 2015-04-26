package application;

import java.util.Comparator;

public class SortPatientTriageComparator implements Comparator<Patient> {

	/**
	 * the method is used to covert triage ctegory to an integer value so it can
	 * be sorted.
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

	/**
	 * method to compare the triage categories of the patients in the queue and
	 * to sort them according to the prioirity
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	@Override
	public int compare(Patient p1, Patient p2) {

		int p1Triage = convertTriage(p1);
		int p2Triage = convertTriage(p2);

		return Integer.compare(p1Triage, p2Triage);

	}

}
