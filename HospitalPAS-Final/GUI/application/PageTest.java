package application;

/**
 * the class is used to test individual page
 */
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageTest extends Application {

	public PageTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GUIMain.allPatientList = new ArrayList<Patient>();
		GUIMain.patientQueue = new PatientQueue();
		GUIMain.treatmentRoom = new TreatmentRoom[GUIMain.NUMBERS_OF_ROOM];
		for (int loop = 0; loop < GUIMain.NUMBERS_OF_ROOM; loop++) {
			GUIMain.treatmentRoom[loop] = new TreatmentRoom(loop, false);
		}
		GUIMain.onCallTeamList = new ArrayList<OnCallTeam>();
		for (int loop = 0; loop < GUIMain.NUMBERS_OF_ONCALLTEAM; loop++) {
			GUIMain.onCallTeamList.add(new OnCallTeam());
		}
		try {
			Parent root = FXMLLoader.load(getClass().getResource(
					"/application/ReceptionistPage.fxml"));
			Scene scene = new Scene(root, 1000, 600);
			primaryStage.setTitle("FXML Welcome");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			Thread myThread = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(GUIMain.REFRESHTIME);
							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									refresh();

								}
							});

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			myThread.setDaemon(true);
			myThread.start();

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void refresh() {
		if(!GUIMain.patientQueue.isEmpty()){
		GUIMain.patientQueue.sort(new SortPatientComparator());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
