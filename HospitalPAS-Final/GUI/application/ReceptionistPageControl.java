package application;

/**
 * the class represents the receptionistPageControl
 */

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ReceptionistPageControl implements Initializable {
	private final static long REFRESHTIME = 1000;
	private TreatmentRoom treatmentRoom;
	private Receptionist receptionist = new Receptionist();
	private int count = 1;

	@FXML
	private MenuItem alterTriage;

	@FXML
	private MenuItem patientSearch;

	@FXML
	private Button okButton;

	@FXML
	private Label status;

	@FXML
	private Label treatmentNo;

	@FXML
	private TextArea alertTextArea;

	@FXML
	private MenuItem newPatient;

	@FXML
	private MenuItem setTriage;

	@FXML
	private Button logOut;

	@FXML
	private TableColumn<Patient, Triage> triage;

	@FXML
	private TableColumn<Patient, String> allergies;

	@FXML
	private TableColumn<Patient, String> address;

	@FXML
	private TableColumn<Patient, Integer> nhs_number;

	@FXML
	private TableColumn<Patient, String> last_name;

	@FXML
	private TableColumn<Patient, String> telephone;

	@FXML
	private TableColumn<Patient, String> title;

	@FXML
	private TableColumn<Patient, String> blood_group;

	@FXML
	private TableColumn<Patient, String> first_name;
	
	@FXML
	private TableView<Patient> patientTable;

	@FXML
	private TableColumn<Patient, Triage> triage_nextPatient;

	@FXML
	private TableColumn<Patient, String> allergies_nextPatient;

	@FXML
	private TableColumn<Patient, String> address_nextPatient;

	@FXML
	private TableColumn<Patient, Integer> nhs_number_nextPatient;

	@FXML
	private TableColumn<Patient, String> last_name_nextPatient;

	@FXML
	private TableColumn<Patient, String> telephone_nextPatient;

	@FXML
	private TableColumn<Patient, String> title_nextPatient;

	@FXML
	private TableColumn<Patient, String> blood_group_nextPatient;

	@FXML
	private TableColumn<Patient, String> first_name_nextPatient;
	
	@FXML
	private TableView<Patient> nextPatient;
	
	
	
	@FXML
	void patienSearchOnClick(ActionEvent event) {
		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/receptionistPage/SearchPatient.fxml"));
			Scene scene = new Scene(root, 900, 522);
			newStage.setTitle("Patient Search");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Open login page
	 * 
	 * @param event
	 */
	@FXML
	void alterTriageOnClick(ActionEvent event) {
		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/application/LoginScreen.fxml"));
			Scene scene = new Scene(root, 450, 400);
			newStage.setTitle("Receptionist");
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
	 * Open login page
	 * 
	 * @param event
	 */
	@FXML
	public void logOutOfSystem(ActionEvent event) {

		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/application/LoginScreen.fxml"));
			Scene scene = new Scene(root, 450, 400);
			newStage.setTitle("Receptionist");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
		Stage stage = (Stage) logOut.getScene().getWindow();

		stage.close();
	}

	public ReceptionistPageControl() {

	}

	/*
	 * Open new patient page
	 */
	@FXML
	public void newPatientOnClick(ActionEvent event) {
		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/receptionistPage/NewPatient.fxml"));
			Scene scene = new Scene(root, 450, 400);
			newStage.setTitle("New Patient");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Open login page
	 * 
	 * @param event
	 */
	@FXML
	private void setTriageOnClick(ActionEvent event) {
		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/application/LoginScreen.fxml"));
			Scene scene = new Scene(root, 450, 400);
			newStage.setTitle("Receptionist");
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
						Thread.sleep(REFRESHTIME);
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
	 * the method is used to refresh table, update waiting time of every patient
	 * in queue and show the receptionist the next patient and the available treatment room
	 * @author Jiang Zhe Heng
	 */
	private void refresh() {
		refreshTable();
		refreshNextPatient();
		refreshAlertTextArea();
		status.setText(String.valueOf(CalculateQueueStatus.status));
	}

	private void refreshAlertTextArea() {

	}

	/**
	 * refresh next patient
	 * @author Jiang Zhe Heng
	 * @param treatmentRoom
	 */
	private void refreshNextPatient() {
		if (!GUIMain.patientQueue.isEmpty()) {
			ObservableList<Patient> observableList=FXCollections.observableArrayList();
			GUIMain.nextPatient=GUIMain.patientQueue.peek();
			observableList.add(GUIMain.nextPatient);
			nextPatient.setItems(null);
			nhs_number_nextPatient
					.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
							"nhsNumer"));
			title_nextPatient.setCellValueFactory(new PropertyValueFactory<Patient, String>(
					"title"));
			first_name_nextPatient
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"firstName"));
			last_name_nextPatient
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"lastName"));
			triage_nextPatient.setCellValueFactory(new PropertyValueFactory<Patient, Triage>(
					"triage"));
			triage_nextPatient.setCellFactory(new Callback<TableColumn<Patient, Triage>, TableCell<Patient, Triage>>() {

				@Override
				public TableCell<Patient, Triage> call(
						TableColumn<Patient, Triage> param) {

					return new TableCell<Patient, Triage>() {
						@Override
						protected void updateItem(Triage item, boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item.getTriage());
							}
						}
					};
				}
			});
			address_nextPatient.setCellValueFactory(new PropertyValueFactory<Patient, String>(
					"address"));
			telephone_nextPatient
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"contactNum"));
			allergies_nextPatient
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"allergies"));
			blood_group_nextPatient
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"bloodGroup"));

			nextPatient.setItems(observableList);
		} else {
			nextPatient.setItems(null);
		}

	}

	/**
	 * refresh all patient in queue
	 * @author Jiang Zhe Heng
	 * @param treatmentRoom
	 */
	private void refreshTable() {
		ObservableList<Patient> observableList=FXCollections.observableArrayList(GUIMain.patientPASList);
		if (!GUIMain.patientPASList.isEmpty()) {
			patientTable.setItems(null);
			nhs_number
					.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
							"nhsNumer"));
			title.setCellValueFactory(new PropertyValueFactory<Patient, String>(
					"title"));
			first_name
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"firstName"));
			last_name
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"lastName"));
			triage.setCellValueFactory(new PropertyValueFactory<Patient, Triage>(
					"triage"));
			triage.setCellFactory(new Callback<TableColumn<Patient, Triage>, TableCell<Patient, Triage>>() {

				@Override
				public TableCell<Patient, Triage> call(
						TableColumn<Patient, Triage> param) {

					return new TableCell<Patient, Triage>() {
						@Override
						protected void updateItem(Triage item, boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item.getTriage());
							}
						}
					};
				}
			});
			address.setCellValueFactory(new PropertyValueFactory<Patient, String>(
					"address"));
			telephone
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"contactNum"));
			allergies
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"allergies"));
			blood_group
					.setCellValueFactory(new PropertyValueFactory<Patient, String>(
							"bloodGroup"));

			patientTable.setItems(observableList);

		} else {
			patientTable.setItems(null);
		}
	}
}
