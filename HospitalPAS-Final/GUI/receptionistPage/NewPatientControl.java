package receptionistPage;

import java.net.URL;
import java.util.ResourceBundle;

import application.GUIMain;
import application.Patient;
import application.Receptionist;
import application.RegisterPatient;
import application.UserException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPatientControl implements Initializable {
	private Patient patient;
	private Receptionist receptionist;

	@FXML
	private Label warning;

	@FXML
	private TextField nhsNumber;

	@FXML
	private Button okButton;

	@FXML
	private Button cancelButton;

	@FXML
	private void cancelButtonOnClick(ActionEvent event) {
		Stage stage  = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}

	@FXML
	private void okButtonOnClick(ActionEvent event) {
		if (!nhsNumber.getText().isEmpty()) {
			patient = new Patient();
			try{
			patient.setNhsNumer(Integer.parseInt(nhsNumber.getText()));
			new RegisterThread().start();
			}
			catch(NumberFormatException e){
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								warning.setText("Wrong Type");
								
							}
						});
						
					}
				}).start();
				
			}
		} else {
			warning.setText("Please complete the NHS number");
		}

	}

	public NewPatientControl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public class RegisterThread extends Thread {

		@Override
		public void run() {
			RegisterPatient RegisterPatient =new RegisterPatient();
			try {
				RegisterPatient.registerPatientToAandE(GUIMain.patientPASList,
						patient);
				
//add patient to the list of the patient prepared to triage
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						warning.setText(patient.getFirstName() + " "
								+ patient.getLastName() + " is recorded into system");
						
					}
				});
			} catch (UserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						warning.setText(e1.getMessage());
						
					}
					
				});
			
			}
		}

	}

}
