/**
 * @author Paul 40133443
 */



package receptionistPage;

import application.Patient;
import application.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * View-Controller for the Patient table.
 * 
 * @author Paul Hunter
 */
public class SearchPatientControl {

	@FXML
	private TextField filterField;
	@FXML
	private TableView<PatientForSearch> patientTable;

	@FXML
	private TableColumn<PatientForSearch, String> nhsNumberColumn;

	@FXML
	private TableColumn<PatientForSearch, String> firstNameColumn;
	@FXML
	private TableColumn<PatientForSearch, String> lastNameColumn;

	@FXML
	private TableColumn<PatientForSearch, String> addressColumn;
	@FXML
	private TableColumn<PatientForSearch, String> contactNumberColumn;
	@FXML
	private TableColumn<PatientForSearch, String> bloodGroupColumn;

	private ObservableList<PatientForSearch> masterData = FXCollections
			.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public SearchPatientControl() {
		masterData.add(new PatientForSearch("1000","Neville","Vance","5 Ballymiscaw Road","9733492995","A"));
		masterData.add(new PatientForSearch("1001","Patricia","Vance","32 Begny Road","9863527465","AB"));
		masterData.add(new PatientForSearch("1002","Jennifer","Lyttle","21 Lenaghan Crescent","9863549264","B"));
		masterData.add(new PatientForSearch("1003","Declan","Cranberry","10 Country Road","9788853741","A"));
		masterData.add(new PatientForSearch("1004","Bryan","Quinn","12 Farmville Road","9677728491","AB"));
		masterData.add(new PatientForSearch("1005","Amanda","Tom","21 Glenwood","9866743952","B"));
		masterData.add(new PatientForSearch("1006","Chris","Tom","8 Shipquay Street","9888885326","AB"));
		masterData.add(new PatientForSearch("1007","James","Symth","11 Cavehill road","9733492995","B"));
		masterData.add(new PatientForSearch("1008","Stacey","Monro","21 Johnson drive","9863789891","A"));
		masterData.add(new PatientForSearch("1009","Andrew","Cusack","54 Ulsterville Gardens","9877738369","O"));
		masterData.add(new PatientForSearch("1010","Fiona","Cusack","19 Donnybrook Street","9863584765","A"));
		masterData.add(new PatientForSearch("1011","Claire","Hunter","2 Strand road","9872547665","AB"));
		masterData.add(new PatientForSearch("1012","Maria","Stephenson","8 Glenavy road","9864763524","AB"));
		masterData.add(new PatientForSearch("1013","Andrew","Simpson","20 Stormount park","9899763574","B"));
		masterData.add(new PatientForSearch("1014","Tony","Andrews","14 Spencer road","9765112345","A"));
		masterData.add(new PatientForSearch("1015","Rachel","Stevens","30 Shankhill road","9833274658","O"));
		masterData.add(new PatientForSearch("1016","Catherine","Stevens","29 Green grove","9855524356","AB"));
		masterData.add(new PatientForSearch("1017","Timothy","Stevens","73 Meadow lane","9844499998","O"));
		masterData.add(new PatientForSearch("1018","Allan","Curry","46 Castle muse","9755375869","B"));
		masterData.add(new PatientForSearch("1019","Jordan","Watson","51 Grey crescent","9863977648","A"));
		masterData.add(new PatientForSearch("1020","Agnes","Brown","41 Windview drive","9863587654","O"));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * Initializes the table columns and sets up sorting and filtering.
	 */
	@FXML
	private void initialize() {
		// 0. Initialize the columns.
		nhsNumberColumn.setCellValueFactory(cellData -> cellData.getValue()
				.nhsNumberProperty());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.lastNameProperty());
		addressColumn.setCellValueFactory(cellData -> cellData.getValue()
				.addressProperty());
		contactNumberColumn.setCellValueFactory(cellData -> cellData.getValue()
				.contactNumberProperty());

		bloodGroupColumn.setCellValueFactory(cellData -> cellData.getValue()
				.bloodGroupProperty());

		// 1. Wrap the ObservableList in a FilteredList (initially display all
		// data).
		FilteredList<PatientForSearch> filteredData = new FilteredList<>(
				masterData, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(Patient -> {
						// If filter text is empty, display all Patients.
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							// Compare first name and last name of every Patient
							// with filter text.
							String lowerCaseFilter = newValue.toLowerCase();

							if (Patient.getFirstName().toLowerCase()
									.indexOf(lowerCaseFilter) != -1) {
								return true; // Filter matches first name.
							} else if (Patient.getLastName().toLowerCase()
									.indexOf(lowerCaseFilter) != -1) {
								return true; // Filter matches last name.
							} else if (Patient.getNhsNumber().toLowerCase().indexOf(lowerCaseFilter) != -1){
								return true;
							}else if (Patient.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1){
								return true;
							} else if (Patient.getBloodGroup().toLowerCase().indexOf(lowerCaseFilter) != -1){
								return true;
							}
							return false; // Does not match.
						});
				});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<PatientForSearch> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(patientTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		patientTable.setItems(sortedData);
	}
}
