package application;

import java.util.Date;

/**
 * the class represents the treatment room in PAS system
 * @author Jiang Zhe Heng
 *
 */
public class TreatmentRoom {
	
	private Patient patient;
	
	private boolean occupied;
	
	private int treatmentRoom;
	
	private Date usedStartDate;
	

	public TreatmentRoom(int treatmentRoom,boolean occupied) {
		setOccupied(occupied);
		setTreatmentRoom(treatmentRoom);
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
		setOccupied(true);
	}

	/**
	 * @return the occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * @param occupied the occupied to set
	 */
	public void setOccupied(boolean occupied) {
		
		if(occupied){
		setUsedStartDate(new Date());
		}else{
			setUsedStartDate(null);
		}
		this.occupied = occupied;
	}

	/**
	 * @return the treatmentRoom
	 */
	public int getTreatmentRoom() {
		return treatmentRoom;
	}

	/**
	 * @param treatmentRoom the treatmentRoom to set
	 */
	public void setTreatmentRoom(int treatmentRoom) {
		this.treatmentRoom = treatmentRoom;
	}

	/**
	 * @return the usedStartDate
	 */
	public Date getUsedStartDate() {
		return usedStartDate;
	}

	/**
	 * @param usedStartDate the usedStartDate to set
	 */
	public void setUsedStartDate(Date usedStartDate) {
		this.usedStartDate = usedStartDate;
	}
	

}
