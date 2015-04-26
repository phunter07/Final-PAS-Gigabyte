package application;

import java.util.Queue;

public interface ITriage {


	public boolean addToQueue(Queue<Patient> patientQueue,Patient patient) throws UserException;

}
