package application;

import java.util.Date;
import java.util.List;
import java.util.Queue;

public class SetPatientTriage implements ITriage, ICategorise {

	/**
	 * method to allow the Triage Nurse to select a patient who needs to be
	 * assigned a Triage category
	 * 
	 * do we really need this - linked to drop downs? - Paul/Fin
	 * 
	 * @param patientList
	 * @param nhsNumber
	 * @return
	 * @throws UserException
	 */
	public Patient findPatientByNhsNumber(List<Patient> patientList,
			String nhsNumber) throws UserException {
		try {
			int nhs = Integer.parseInt(nhsNumber);
			for (Patient patient : patientList) {

				if (patient.getNhsNumer() == nhs) {
					return patient;
				}
			}
		} catch (NumberFormatException e) {
			throw new UserException("NHS should be a number");
		}
		return null;
	}

	/**
	 * method to allow the triage nurse to display the patients in the GUI who
	 * are yet to be triaged
	 * 
	 * @param patientToBeTriaged
	 * @param patientPASList
	 */
	public void findPatientToBeTriaged(List<Patient> patientToBeTriaged,
			List<Patient> patientPASList) {
		for (Patient patient : patientPASList) {
			if (patient.getTriage() == null) {
				patientToBeTriaged.add(patient);

			}
		}
	}

	/**
	 * Method to allow the Triage nurse to set the category of the patient
	 * 
	 * @throws UserException
	 */
	@Override
	public boolean categorisePatient(Patient patient, Triage triage)
			throws UserException {
		if (patient != null) {
			if (patient.getTriage() == null) {
				patient.setTriage(triage);
				return true;
			} else {
				throw new UserException("Sorry, this patient has been triaged");
			}
		} else {
			throw new UserException("Sorry, cannot recogonise this patient");
		}
	}

	/**
	 * method to allow the triaged patient to be added to the queue
	 */
	//@Override
	//public boolean addToQueue(Queue<Patient> patientQueue, Patient patient)
		//	throws UserException {
		//if (patient != null && patient.getTriage() != null) {
			//return patientQueue.add(patient);
			//this.sort(new SortPatientTriageComparator());
		//} else {
			//throw new UserException(
				//	"Sorry, can not add the patient to the queue");
	//	}
//	}

	/**
	 * method to remove the patient from the triage queue once they have been
	 * assigned a category
	 */
	public boolean removeFromList(List<Patient> patientList, Patient patient) {
		return patientList.remove(patient);
	}

	/**
	 * the method is used to allocate patients who have been assigned a triage
	 * category to the queue
	 * 
	 * @return
	 * @throws UserException
	 */
	public void allocatePatient(Patient patient, List<Patient> allPatientList,
			PatientQueue patientQueue) throws UserException {

		// if patient triage category doesn't equal null then the patient is
		// added to the queue and removed from the triage list
		if (patient.getTriage() != null) {
			addToQueue(patientQueue, patient);
			removeFromList(allPatientList, patient);
		}
	}

	/**
	 * method to allow the Traige nurse to change the category of the patient
	 * should their condition change
	 * 
	 * @throws UserException
	 */
	@Override
	public boolean recategorisePatient(Patient patient, Triage triage)
			throws UserException {

		if (patient != null) {
			patient.setTriage(triage);
			return true;
		} else {
			throw new UserException("Sorry, can not recogonise this patient");
		}
	}

	@Override
	public boolean addToQueue(Queue<Patient> patientQueue, Patient patient)
			throws UserException {
		
		if (patient != null && patient.getTriage() != null) {
			return patientQueue.add(patient);
		} else {
			throw new UserException ("Sorry cannot add patient to queue");
		
	}

	}
}
