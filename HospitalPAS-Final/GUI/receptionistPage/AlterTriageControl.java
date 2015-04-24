package receptionistPage;

/**
 * the class represents setTriageControl
 */
import java.net.URL;
import java.util.ResourceBundle;

import application.GUIMain;
import application.NurseTriage;
import application.OnCallTeam;
import application.Patient;
import application.Triage;
import application.UserException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AlterTriageControl implements Initializable {

	/**
	 * declaration of nurseTriage
	 */
	private NurseTriage nurseTriage = new NurseTriage();

	/**
	 * declaration of the list of patient needed to be triaged
	 */
	private ObservableList<Patient> patientNeededToBeAltered;

	/**
	 * declaration of the list of nhs_number needed to be triaged
	 */
	private ObservableList<String> nhsNeededToBeAltered;

	public AlterTriageControl() {
	}

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
	private ChoiceBox<String> nhsNumber;

	@FXML
	private ChoiceBox<String> triageChoiceBox;

	@FXML
	private Button okButton;

	@FXML
	private Button cancelButton;

	@FXML
	private Label warning;

	/**
	 * if OK button is clicked, firstly the system will find the patient by
	 * nhs_number, then change the triage of this patient
	 * 
	 * @author Jiang Zhe Heng
	 * @param event
	 */
	@FXML
	public void okOnClick(ActionEvent event) {
		warning.setText("");
		if ((nhsNumber.getValue() != null)
				&& (triageChoiceBox.getValue() != null)) {
			try {
				Patient patient = nurseTriage.findPatientByNhsNumber(
						GUIMain.patientQueue, nhsNumber.getValue());
				if (patient != null) {
					nurseTriage.alterPatientTriage(patient, Triage
							.valueOf(triageChoiceBox.getValue().toUpperCase()));
					try {
						nurseTriage.removeFromList(GUIMain.patientQueue, patient);
						nurseTriage.allocatePatient(patient,
								GUIMain.allPatientList, GUIMain.patientQueue,
								GUIMain.treatmentRoom, GUIMain.onCallTeamList);
					} catch (UserException e) {
						warning.setText(e.getMessage());
					}

					// if (triageChoiceBox.getValue() != Triage.EMERGENCY
					// .getTriage()) {
					// nurseTriage.removeFromList(GUIMain.patientQueue,
					// patient);
					// nurseTriage.addToQueue(GUIMain.patientQueue, patient);
					// nurseTriage.removeFromList(GUIMain.allPatientList,
					// patient);
					// warning.setText("Patient has been moved to the queue");
					// } else {
					// boolean success = nurseTriage.allocateEmergencyPatient(
					// patient, GUIMain.treatmentRoom,
					// GUIMain.patientQueue);
					// if (success) {
					// warning.setText("The emergency patient has been moved to the treatment room");
					// } else {
					// boolean available = false;
					//
					// for (OnCallTeam onCallTeam : GUIMain.onCallTeamList) {
					// if (!onCallTeam.isOccupied()) {
					// onCallTeam.setPatient(patient);
					// warning.setText(Alerts.SMSALERTONCALLTEAM
					// .getAlert());
					// available = true;
					// break;
					// }
					// }
					// if (!available) {
					// warning.setText(Alerts.SMSALERTMANAGERONCALLFULLYENGAGED
					// .getAlert());
					// }
					// }
					// }
				} else {
					warning.setText("Can not find this patient");
				}
			} catch (UserException e) {
				warning.setText(e.getMessage());
			}
		} else {
			warning.setText("Please complete the form");
		}
		refresh();
	}

	/**
	 * close this stage
	 * 
	 * @author Jiang Zhe Heng
	 * @param event
	 */
	@FXML
	public void cancelOnClick(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * initialize the stage, set the items of the choice box and refresh the
	 * other components
	 * 
	 * @author Jiang Zhe Heng
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		triageChoiceBox.setItems(FXCollections.observableArrayList(
				Triage.EMERGENCY.getTriage(), Triage.URGENT.getTriage(),
				Triage.SEMI_URGENT.getTriage(), Triage.NON_URGENT.getTriage()));
		refresh();
	}

	/**
	 * find the patient needed to be triaged and his/her nhsnumber,which can be
	 * added into patient table and nhsNumber choice box, so that triageNurse
	 * can select the nhsnumber to add/alter triage of the patient owning this
	 * nhsnumber
	 * 
	 * @author Jiang Zhe Heng
	 */
	public void refresh() {
		patientNeededToBeAltered = FXCollections.observableArrayList();
		nhsNeededToBeAltered = FXCollections.observableArrayList();
		nurseTriage.findPatientNeededToBeAltered(patientNeededToBeAltered,
				GUIMain.patientQueue);
		nhsOfPatientNeededToBeAltered();
		refreshTable();
		nhsNumber.setItems(nhsNeededToBeAltered);
	}

	/**
	 * refresh the table of all patient in Queue
	 * 
	 * @author Jiang Zhe Heng
	 */
	public void refreshTable() {
		if (!GUIMain.patientQueue.isEmpty()) {
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

			patientTable.setItems(patientNeededToBeAltered);

		} else {
			patientTable.setItems(null);
		}
	}

	/**
	 * add the nhs_number of the patient needed to be triaged into a list of
	 * nhs_number
	 * 
	 * @author Jiang Zhe Heng
	 */
	public void nhsOfPatientNeededToBeAltered() {
		for (Patient patient : patientNeededToBeAltered) {
			nhsNeededToBeAltered.add(String.valueOf(patient.getNhsNumer()));
		}
	}
}
