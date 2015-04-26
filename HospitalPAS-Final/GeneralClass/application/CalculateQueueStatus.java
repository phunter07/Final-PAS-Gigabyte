package application;

/**
 * method to calculate the status of the queue
 * 
 * status 1 = 0 - 9 minutes 
 * status 2 =  10-19 minutes
 * status 3 = 20 + 
 * status 4 = unable to treat new patients
 * 
 * @author Hannah, Clare, Cat
 *
 */

public class CalculateQueueStatus {
	
	/**
	 * declaration of Integer to reflect the status of the queue
	 */
	public static Integer status;

	/**
	 * method to calculate the status of the queue
	 */
	public void calculateStatus(){
		
		if (GUIMain.patientQueue.size() <= Constants.PATIENT_LIMIT_IN_QUEUE) {
			
			//find the longest waiting time of the patients in the queue
			long longestWaitingTime = 0;
			
			for (Patient patient : GUIMain.patientQueue){
				if (patient.getWaitingTime() > longestWaitingTime){
					longestWaitingTime = patient.getWaitingTime() / 1000 / 60;
				}
			}
			if (longestWaitingTime >= 0 && longestWaitingTime <10) {
				status = 1;
			} else if (longestWaitingTime >= 10 && longestWaitingTime < 20) {
				status = 2;
			} else if (longestWaitingTime >= 20) {
				status = 3;
			} else if (GUIMain.patientQueue.size() == Constants.PATIENT_LIMIT_IN_QUEUE){
				status = 4;
			}
		}
		
	}
	
}
