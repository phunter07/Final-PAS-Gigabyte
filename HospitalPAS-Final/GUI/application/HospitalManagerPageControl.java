package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * 
 * @author Clare O'Toole
 *
 */
public class HospitalManagerPageControl implements Initializable {

	@FXML
	private Button logOut;
	
    @FXML
    private Button viewStaff;
    
    @FXML
    private Button viewTreatmentRooms;
    

    @FXML
    private Tab triageQueue;
    
    @FXML
    private TableColumn<Patient, Integer> nhs_number;

    @FXML
    private TableView<Patient> PatientTableManager; 

    @FXML
    private TableColumn<Patient, String> last_name;

    @FXML
    private TableColumn<Patient, String> title;
    
    @FXML
    private TableColumn<Patient, String> first_name;

    @FXML
    private TableColumn<Patient, Triage> triage;

	
    /**
	 * initialize this page and create a new thread to refresh this page every
	 * second
	 * 
	 * @author Jiang Zhe Heng
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Thread myThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(Constants.REFRESHTIME);
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

	}

	
	
	/**
	 * Log out of system
	 * 
	 * @param event
	 */
	@FXML
	public void onClickLogOut(ActionEvent event) {

		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/application/LoginScreen.fxml"));
			Scene scene = new Scene(root, 450, 400);
			newStage.setTitle("Hospital Manager");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
		Stage stage = (Stage) logOut.getScene().getWindow();

		stage.close();
	}

    @FXML
    void onClickStaff(ActionEvent event) {
    	Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/application/viewStaff.fxml"));
			Scene scene = new Scene(root, 450, 400);
			newStage.setTitle("Hospital Manager");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
		Stage stage = (Stage) logOut.getScene().getWindow();

		stage.close();
	
    }
	
    @FXML
    void onClickViewTreatmentRooms(ActionEvent event) {
    	Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/application/viewTreatmentRooms.fxml"));
			Scene scene = new Scene(root, 450, 400);
			newStage.setTitle("Hospital Manager");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
		Stage stage = (Stage) logOut.getScene().getWindow();

		stage.close();
	
    }
    
    /**
	 * the method is used to refresh table, update waiting time of every patient
	 * in queue and show the receptionist the next patient and the available treatment room
	 * @author Jiang Zhe Heng
	 */
	private void refresh() {
		refreshTable();

	}
}
