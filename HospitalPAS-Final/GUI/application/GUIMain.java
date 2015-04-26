package application;

/**
 * The class represent the application, application will start from this class
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GUIMain extends Application {

	/**
	 * declaration of array for treatment rooms
	 */
	public static TreatmentRoom[] treatmentRoom;

	/**
	 * List containing the details of all the patients in the PAS
	 */
	public static List<Patient> patientPASList;
	
	/**
	 * instance variable to allow staff to log in to the system
	 */
	public static Staff user;
	
	/**
	 * instance variable to declare the next patient within the system
	 */
	public static Patient nextPatient;
	
	/**
	 * declaration of class patientQueue
	 */
	public static PatientQueue patientQueue;

	/**
	 * main method to launch the system
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// launch the system
		launch(args);

	}

	/**
	 * Method to set up the FXML and call the thread to start the system
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			Parent root = FXMLLoader.load(getClass().getResource(
					"/application/LoginScreen.fxml"));
			Scene scene = new Scene(root, 450, 400);
			primaryStage.setTitle("FXMLLogin");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			threadStart();
			primaryStage.show();
			// need to create exceptions and give them suitable names
		} catch (Exception stageNotFound) {
			// do we need to print the stack trace?
			stageNotFound.printStackTrace();
		}

	}

	/**
	 * Method to start the thread for the system
	 */
	public void threadStart() {
		
		//calling method to instantiate Patient List
		instantiatePatientList();

		//calling method to instantiate Patient Queue
		instantiatePatientQueue();
		
		// calling method to instantiate treatment rooms
		instantiateTreatmentRooms();

		// declaration of threat to kick off when login screen is loaded
		Thread loginThread = new Thread(new Runnable() {
			@Override
			public void run() {

				while (true) {
					try {
						// calling the refresh time of the thread from the
						// constants class which is set at 30 seconds
						Thread.sleep(Constants.REFRESHTIME);
						refresh();
						// need to create this exception
					} catch (InterruptedException threadInterrupted) {
						threadInterrupted.printStackTrace();
					}
				}
			}

		});
		loginThread.setDaemon(true);
		loginThread.start();
	}

	/**
	 * method to refresh the queue when it is called in the thread
	 */
	public void refresh() {
		// queue needs to go in here to be sorted
		// call to calculate the queue the status
		// call method to write the queue to file

	}
	
	/**
	 * method to instntiate patient list
	 */
	public void instantiatePatientList(){
		
		patientPASList = new LinkedList<Patient>();
		
	}
	
	/**
	 * method to instntiate patient queue
	 */
	public void instantiatePatientQueue(){
		
		patientQueue = new PatientQueue();
		
	}

	/**
	 * method to instantiate the teatment rooms at the beginning of the program
	 */
	public void instantiateTreatmentRooms() {

		// declaring the number of rooms to 5
		treatmentRoom = new TreatmentRoom[Constants.NUMBERS_OF_ROOM];

		for (int loop = 0; loop < Constants.NUMBERS_OF_ROOM; loop++) {
			// setting the initial state of each treatment room to unoccupied
			// and a new date
			treatmentRoom[loop] = new TreatmentRoom(loop, false, new Date());

		}

	}

}
