package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewStaffController implements Initializable {
	
	
	@FXML
    private TableColumn<Staff, String> staffFirstNameColumn;

    @FXML
    private TableColumn<Staff, String> staffTeamColumn;

    @FXML
    private TableColumn<Staff, String> staffEmailColumn;

    @FXML
    private MenuItem newPatient;

    @FXML
    private TableColumn<Staff, String> staffTitleColumn;

    @FXML
    private TableColumn<Staff, String> staffLastNameColumn;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Staff, String> staffIDColumn;

    @FXML
    private TableColumn<Staff, String> staffRoleColumn;

    @FXML
    private TableColumn<Staff, String> staffTelephoneNumColumn;
    
    @FXML
    private TableView<Staff> viewStaffTable;

    @FXML
    void newPatientOnClick(ActionEvent event) {

    }

    @FXML
    void onClickBack(ActionEvent event) {

    }

    

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		//staffIDColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("STAFF_ID"));
		//staffTitleColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("TITLE"));
		//staffFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("FIRST_NAME"));


		//viewStaffTable.seItems(GUIMain.staffs);
		    
	}
	}