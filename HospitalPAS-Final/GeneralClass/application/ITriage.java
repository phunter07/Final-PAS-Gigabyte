package application;

import java.util.Queue;

public interface ITriage {

	public boolean categorisePatient(Patient patient,Triage category) throws UserException;

	public void displayAandEQueue();

	public boolean addToQueue(Queue<Patient> patientQueue,Patient patient) throws UserException;

}
