package application;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TreatmentRoom {

	/**
	 * instance var for patient
	 */
	private Patient patient;

	/**
	 * instance var to determine if the treatment room is occupied or not
	 */
	private boolean occupied;

	/**
	 * instance var for the treatment room
	 */
	private int treatmentRoom;

	/**
	 * instance var to determine the patient time into the treatment room
	 */
	private Date patientTimeIn;

	/**
	 * instnce var to calculate the time the patient is in the treatment room
	 */
	private long patientTimeInTreatmentRoom;

	/**
	 * constructor with args
	 */
	public TreatmentRoom(int treatmentRoom, boolean occupied, Date patientTimeIn) {
		this.occupied = occupied;
		this.patientTimeIn = patientTimeIn;
		this.treatmentRoom = treatmentRoom;

	}

	/**
	 * method to get the patient in the treatment room
	 * 
	 * @return
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * method to set the patient in the treatment room and to set the treatment
	 * room to occupied if a patient variable is present
	 * 
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
		setOccupied(true);
	}

	/**
	 * method to determine if the treatment room is occupied
	 * 
	 * @return
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * method to check if the treatment room is occupied if free the patient
	 * time into the treatment room is set to the current date and time
	 * 
	 * @param occupied
	 */
	public void setOccupied(boolean occupied) {
		if (occupied) {
			setPatientTimeIn(new Date());
		} else {
			setPatientTimeIn(null);
			this.occupied = occupied;
		}
	}

	/**
	 * method to get the treatment room
	 * 
	 * @return
	 */
	public int getTreatmentRoom() {
		return treatmentRoom;
	}

	/**
	 * method to set the treatment room
	 * 
	 * @param treatmentRoom
	 */
	public void setTreatmentRoom(int treatmentRoom) {
		this.treatmentRoom = treatmentRoom;
	}

	/**
	 * method to get the patient time into the treatment room
	 * 
	 * @return
	 */
	public Date getPatientTimeIn() {
		return patientTimeIn;
	}

	/**
	 * method to set the time in the treatment room
	 * 
	 * @param patientTimeIn
	 */
	public void setPatientTimeIn(Date patientTimeIn) {
		this.patientTimeIn = patientTimeIn;
	}

	/**
	 * method to calculate the length of time a patient has been in the
	 * treatment room
	 * 
	 * @return
	 */
	public Long getPatientTimeInTreatmentRoom() {
		if (this.getPatientTimeIn() != null) {
			this.patientTimeInTreatmentRoom = new Date().getTime()
					- this.getPatientTimeIn().getTime();
		} else {
			this.patientTimeInTreatmentRoom = 0;
		}
		return this.patientTimeInTreatmentRoom;
	}

	/**
	 * method to find an empty treatment room - loops through the array of
	 * treatment rooms and returns the treatment room which is empty
	 * 
	 * @param rooms
	 * @return
	 */
	public TreatmentRoom findEmptyTreatmentRoom(TreatmentRoom[] rooms) {
		for (int loop = 0; loop < rooms.length; loop++) {
			if (rooms[loop].isOccupied() == false) {
				return GUIMain.treatmentRoom[loop];

			}
		}
		return null;
	}

	/**
	 * method to compare the triage category of the patients in the treatment
	 * rooms
	 *
	 */
	public class SortTreatment implements Comparator<TreatmentRoom> {

		@Override
		public int compare(TreatmentRoom t1, TreatmentRoom t2) {
			SortPatientTriageComparator sortPatientComparator = new SortPatientTriageComparator();
			return sortPatientComparator.compare(t2.getPatient(),
					t1.getPatient());
		}

		/**
		 * method find a treatment room that contains a non emergency patient
		 * 
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
	}

	/**
	 * method to allow the emergency patient to be added automatically to the
	 * treatment room - an a non emergency case is removed and added to the top
	 * of the queue
	 * 
	 * @param emergencyPatient
	 * @param treatmentRooms
	 * @param patientQueue
	 * @return
	 */
	public boolean allocateEmergencyPatient(Patient emergencyPatient,
			TreatmentRoom[] treatmentRooms, PatientQueue patientQueue) {
		if (emergencyPatient != null
				&& emergencyPatient.getTriage().equals(Triage.EMERGENCY)) {
			TreatmentRoom availableRoom = findEmptyTreatmentRoom(treatmentRooms);
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

}
