package application;

/**
 * the class represents the triageNursePageControl
 * @author Jiang Zhe Heng
 */
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TriageNursePageControl implements Initializable {

	private final long REFRESHTIME=1000;
	@FXML
	private MenuItem setTriage;

	@FXML
	private MenuItem newPatient;

	@FXML
	private MenuItem patientSearch;

	@FXML
	private MenuItem alterTriage;

	@FXML
	private Button logout;

	@FXML
	private TableView<Patient> tableView;

	@FXML
	private TableColumn<Patient, String> allergies;

	@FXML
	private TableColumn<Patient, String> address;

	@FXML
	private TableColumn<Patient, Integer> nhs_number;

	@FXML
	private TableColumn<Patient, String> last_name;

	@FXML
	private TableColumn<Patient, Triage> triage;
	
	@FXML
	private TableColumn<Patient, Long> waitingTime;

	@FXML
	private TableColumn<Patient, String> telephone;

	@FXML
	private TableColumn<Patient, String> title;

	@FXML
	private TableColumn<Patient, String> blood_group;

	@FXML
	private TableColumn<Patient, String> first_name;

	@FXML
	void logoutOnClick(ActionEvent event) {

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
		Stage stage = (Stage) logout.getScene().getWindow();

		stage.close();
	}

	@FXML
	void newPatientOnClick(ActionEvent event) {

	}

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
	

	@FXML
	void setTriageOnClick(ActionEvent event) {
		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/receptionistPage/SetTriage.fxml"));
			Scene scene = new Scene(root, 687, 488);
			newStage.setTitle("Set Triage");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void alterTriageOnClick(ActionEvent event) {
		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/receptionistPage/AlterTriage.fxml"));
			Scene scene = new Scene(root, 687, 488);
			newStage.setTitle("Alter Triage");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
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
	}

	/**
	 * refresh all patient in queue
	 * 
	 * @author Jiang Zhe Heng
	 * @param treatmentRoom
	 */
	private void refreshTable() {
		if (!GUIMain.patientQueue.isEmpty()) {
			ObservableList<Patient> list = FXCollections.observableArrayList();
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

			waitingTime.setCellValueFactory(new PropertyValueFactory<Patient,Long>("waitingTime"));
			waitingTime
					.setCellFactory(new Callback<TableColumn<Patient, Long>, TableCell<Patient, Long>>() {

						@Override
						public TableCell<Patient, Long> call(
								TableColumn<Patient, Long> param) {

							return new TableCell<Patient, Long>() {
								@Override
								protected void updateItem(Long item,
										boolean empty) {
									super.updateItem(item, empty);
									if (item != null) {
										long seconds = item / 1000;
										if (seconds < 60) {
											setText("00:"
													+ String.format("%02d",
															seconds));
										} else if (seconds >= 60
												&& seconds < 3600) {
											setText(String.format("%02d:",
													seconds / 60)
													+ String.format("%02d",
															seconds % 60));
										} else {
											setText("More than one hour");
										}
									}
								}
							};
						}
					});
			for (Patient patient : GUIMain.patientQueue) {

				list.add(patient);
				tableView.setItems(list);
			}
		} else {
			tableView.setItems(null);
		}
	}
}
