package application;

/**
 * the class represents LoginPageControl
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginPageControl implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private Button LoginButton;

	@FXML
	private Label invalidMess;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	/**
	 * if the user click login button, system will open different pages
	 * dependent on the role of the user
	 * @author Jiang Zhe Heng
	 * @param event
	 */
	@FXML
	public void LoginButtonOnClick(ActionEvent event) {
		Task<Void> progressTask = new Task<Void>() {
			Staff staff = new Staff();

			@Override
			protected Void call() throws Exception {

				return null;
			}

			@Override
			protected void succeeded() {
				super.succeeded();

				if (!username.getText().isEmpty()
						&& !password.getText().isEmpty()) {
					String staffID = username.getText();
					String loginPassword = password.getText();
					staff = staff.login(staffID, loginPassword);
					if (staff != null) {
						switch (staff.getRole()) {
						case "Receptionist":
							GUIMain.user = staff;
							openPage("/application/ReceptionistPage.fxml");
							break;
						case "Doctor":
							openPage("/application/DoctorPage.fxml");
							break;
						case "Nurse":
							openPage("/application/NursePage.fxml");
							break;
						case "TriageNurse":
							openPage("/application/TriageNursePage.fxml");
							break;
						case "Hospital Manager":
							System.out.println(staff.getRole());
							openPage("/application/HospitalManagerPage.fxml");
							break;
						default:
							invalidMess.setText("You have no role");
							break;
						}

						invalidMess.setText("Success");
					} else {
						invalidMess.setText("Invalid Login Details");
					}
				} else {
					invalidMess.setText("Please complete the login page");
				}
			}

		};

		Thread myThread = new Thread(progressTask);
		myThread.setDaemon(true);
		myThread.start();

	}

	/**
	 * create new stage, the name of the page should be introduced
	 * @author Jiang Zhe Heng
	 * @param page
	 */
	public void openPage(String page) {
		Stage newStage = new Stage();

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(page));
			Scene scene = new Scene(root, 1000, 600);
			newStage.setTitle("Login");
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = (Stage) LoginButton.getScene().getWindow();
		stage.close();
	}

}
