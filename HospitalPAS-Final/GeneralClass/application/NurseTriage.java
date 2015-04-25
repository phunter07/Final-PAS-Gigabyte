package application;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * class containing the details of the TiageNurse
 * 
 * @author Fin
 *
 */

public class NurseTriage extends Staff implements ITriage, IJDoe, ICategorise {

	/**
	 * default constructor
	 */
	public NurseTriage() {
	}

	/**
	 * constructor with arguments to obtain the details of the Triage Nurse
	 * 
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param staffID
	 * @param password
	 */
	public NurseTriage(String title, String firstName, String lastName, char gender, int staffID, String password, String role, String email,
			String telephone, String team) {
		super(title, firstName, lastName, gender, staffID, password, role, email, telephone, team);

	}

	/**
	 * the method is used to allocate patient including emergency and
	 * non-emergency patient
	 * 
	 * @author Jiang Zhe Heng
	 * @return
	 * @throws UserException
	 */
	public void allocatePatient(Patient patient, List<Patient> allPatientList,
			PatientQueue patientQueue, TreatmentRoom[] treatmentRoom,
			List<OnCallTeam> onCallTeamList) throws UserException {
		// if the patient is a non-emergency patient, then system will find the
		// empty treatment room
		if (patient.getTriage() != Triage.EMERGENCY) {
			TreatmentRoom room = findEmptyTreatmentRoom(treatmentRoom);
			// if system can not find the empty treatment room, then system will
			// add this patient into queue
			if (room == null) {
				if (addToQueue(patientQueue, patient)) {
					throw new UserException(
							"Patient has been moved to the queue");
				} else {
					// if system failed to add patient into queue, it means the
					// queue is full,
					// according to requirements of
					// "If the queue is full and a non-emergency patient arrives
					// at A&E they should be immediately redirected to the
					// nearest hospital."
					// and
					// " When the queue reaches 10 then an automated emergency
					// response is
					// sent to the On Call Team. (SMS Message)."
					// So removeFromList and patientQueue.sendSMS method make
					// these requirements come true
					removeFromList(allPatientList, patient);
					// adding the PatientQueue class and sendSMS
					// method to allow the SMS to be sent to the
					// on call team
					if (patientQueue.sendSMS()) {
						throw new UserException(
								"The patient being redirected to the nearest hospital");
					}
				}
			} else {
				// if the system can find the empty treatment room,then add the
				// patient into treatment room directly
				room.setPatient(patient);
				throw new UserException(
						"The patient being moved to treatment room");
			}
		// if the patient is an emergency patient
		} else {
			//check if the emergency patient can be allocated into treatment room
			boolean success = allocateEmergencyPatient(patient, treatmentRoom,
					patientQueue);
			if (success) {
				// the emergency patient is allocated successfully
				throw new UserException(
						"The emergency patient has been moved to the treatment room");
			} else {
				// the emergency patient can not be allocated, check if there is available onCallTeam
				boolean availableOnCallTeam = false;

				for (OnCallTeam onCallTeam : onCallTeamList) {
					//if OnCallTeam is not occupied, put the patient into it
					if (!onCallTeam.isOccupied()) {
						onCallTeam.setPatient(patient);
						availableOnCallTeam = true;
						break;
					}
				}
				
				if (availableOnCallTeam) {
					//if there is available OnCallTeam,then send alert to onCallTeam 
					//that a emergency will come
					//new SMSAlerts().sendSMSToOnCallTeam();
					throw new UserException(
							Alerts.SMSALERTONCALLTEAM.getAlert());
					

				} else {
					//if there is no availableOnCallTeam send alert, and remove patient from all patientList
					removeFromList(allPatientList, patient);
					new SMSAlerts().sendSSMSManagerOnCallFullyEngaged();
					throw new UserException(
							Alerts.SMSALERTMANAGERONCALLFULLYENGAGED.getAlert());
					
				}

			}
		}
	}

	/**
	 * initially give the patient condition a category, the patient prepared to
	 * be triage and his triage should be introduced,if the nurseTriage can not
	 * categorize this patient, a userException will be thrown
	 * 
	 * @author Jiang Zhe Heng
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
			throw new UserException("Sorry, can not recogonise this patient");
		}
	}

	/**
	 * 
	 * alter patient triage
	 * 
	 * @author Jiang Zhe Heng
	 * @param patient
	 * @param triage
	 * @return
	 * @throws UserException
	 */
	public boolean alterPatientTriage(Patient patient, Triage triage)
			throws UserException {
		if (patient != null) {
			patient.setTriage(triage);
			return true;
		} else {
			throw new UserException("Sorry, can not recogonise this patient");
		}
	}

	/**
	 * method to recategorize the patient prepared to be triage and his triage
	 * should be introduced,if the nurseTriage can not recategorize this
	 * patient, a userException will be thrown
	 * 
	 * @author Jiang Zhe Heng
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

	/**
	 * the nurseTriage can add the catagorized patient into the patientQueue,
	 * the patientQueue and the catagorized patient should be introduced,if the
	 * nurseTriage can not add this patient, a userException will be thrown
	 * 
	 * @author Jiang Zhe Heng
	 */
	@Override
	public boolean addToQueue(Queue<Patient> patientQueue, Patient patient)
			throws UserException {
		if (patient != null && patient.getTriage() != null) {
			return patientQueue.add(patient);
		} else {
			throw new UserException(
					"Sorry, can not add the patient to the queue");
		}

	}

	/**
	 * method to allow the TriageNurse to allocate a first name an unconscious
	 * patient
	 * 
	 * @return
	 */
	@Override
	public String allocateFirstNameJDoe() {

		return null;
	}

	/**
	 * method to allow the TriageNurse to allocate a last name to an unconscious
	 * patient
	 * 
	 * @return
	 */
	@Override
	public String allocatelastNameJDoe() {

		return null;
	}

	/**
	 * method to allow a TriageNurse to allocate an NHS Number to an unconscious
	 * patient - should be incrementing values which are automatically generated
	 * 
	 * @return
	 */
	@Override
	public int allocateNSHNumberJDoe() {

		return 0;
	}

	@Override
	public void displayAandEQueue() {

	}

	/**
	 * method to allow a TriageNurse to remove patient from the list of the
	 * patient, for example, a TriageNurse alter the triage of a patient in
	 * queue to emergency, then this patient should be removed from the queue
	 * and added to treatment room directly
	 * 
	 * @author Jiang Zhe Heng
	 * @return
	 */
	public boolean removeFromList(List<Patient> patientList, Patient patient) {
		return patientList.remove(patient);
	}

	/**
	 * method to allow a TriageNurse to find a patient by nhs number from a list
	 * of patient, if find the patient,return this patient, or return null. if
	 * the nhs_number is not a number throw UserException
	 * 
	 * @author Jiang Zhe Heng
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
	 * method to allow a TriageNurse to allocate an emergency patient, the
	 * emergency patient, treatment rooms and the patient queue should be
	 * introduced. If success return true, or return false
	 * 
	 * @author Jiang Zhe Heng
	 * @param emergencyPatient
	 * @param treatmentRooms
	 * @param patientQueue
	 * @return
	 */
	public boolean allocateEmergencyPatient(Patient emergencyPatient,
			TreatmentRoom[] treatmentRooms, PatientQueue patientQueue) {
		if (emergencyPatient != null
				&& emergencyPatient.getTriage().equals(Triage.EMERGENCY)) {
			TreatmentRoom availableRoom = findAvailableTreatmentRoom(treatmentRooms);
			if (availableRoom != null) {
				if (availableRoom.isOccupied() == false) {
					availableRoom.setPatient(emergencyPatient);
					return true;
				} else {
					patientQueue.addFirst(availableRoom.getPatient());
					availableRoom.setPatient(emergencyPatient);
					return true;
				}
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * method to allow a TriageNurse to find available treatment room(including
	 * empty room and the room occupied by less emergency patient). If sucess
	 * return available treatment room, or return null
	 * 
	 * @author Jiang Zhe Heng
	 * @param emergencyPatient
	 * @param treatmentRooms
	 * @param patientQueue
	 * @return
	 */
	public TreatmentRoom findAvailableTreatmentRoom(
			TreatmentRoom[] treatmentRooms) {
		List<TreatmentRoom> treatmentRoomList = new LinkedList<TreatmentRoom>();
		for (int loop = 0; loop < treatmentRooms.length; loop++) {
			if (treatmentRooms[loop].isOccupied() == false) {
				return treatmentRooms[loop];

			}
			treatmentRoomList.add(treatmentRooms[loop]);
		}
		treatmentRoomList.sort(new SortTreatment());
		if (treatmentRoomList.get(0).getPatient().getTriage() != Triage.EMERGENCY) {
			return treatmentRoomList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * method to allow a TriageNurse to extract the patient needed to be triage
	 * from all patient in PAS system
	 * 
	 * @author Jiang Zhe Heng
	 * @param emergencyPatient
	 * @param treatmentRooms
	 * @param patientQueue
	 * @return
	 */
	public void findPatientNeededToBeTriage(
			List<Patient> patientNeededToBeTriage, List<Patient> allPatientList) {
		for (Patient patient : allPatientList) {
			if (patient.getTriage() == null) {
				patientNeededToBeTriage.add(patient);
			}
		}
	}

	/**
	 * method to allow a TriageNurse to extract the patient needed to be altered
	 * from all patient in PAS system
	 * 
	 * @author Jiang Zhe Heng
	 * @param emergencyPatient
	 * @param treatmentRooms
	 * @param patientQueue
	 * @return
	 */
	public void findPatientNeededToBeAltered(
			List<Patient> patientNeededToBeAltered, List<Patient> allPatientList) {
		for (Patient patient : allPatientList) {
			if (patient.getTriage() != null) {
				patientNeededToBeAltered.add(patient);
			}
		}
	}

	/**
	 * the class represents the comparator of sortTreatment, which is based on
	 * comparing of the patient in every treatment room
	 * 
	 * @author Jiang Zhe Heng
	 *
	 */
	public class SortTreatment implements Comparator<TreatmentRoom> {

		@Override
		public int compare(TreatmentRoom o1, TreatmentRoom o2) {
			SortPatientComparator sortPatientComparator = new SortPatientComparator();
			return sortPatientComparator.compare(o2.getPatient(),
					o1.getPatient());
		}

	}

	/**
	 * the method is used by nurseTriage to find empty treatment room, if the
	 * empty treatment room is found, return the empty treatment room, or return
	 * null
	 * 
	 * @author Jiang Zhe Heng
	 * @return
	 */
	public TreatmentRoom findEmptyTreatmentRoom(TreatmentRoom[] rooms) {
		for (int loop = 0; loop < rooms.length; loop++) {
			if (GUIMain.treatmentRoom[loop].isOccupied() == false) {
				return GUIMain.treatmentRoom[loop];

			}
		}
		return null;
	}

}
