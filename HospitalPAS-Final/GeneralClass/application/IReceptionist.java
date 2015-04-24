package application;

import java.util.List;


public interface IReceptionist {

	public void queryDatabase();

	public void registerPatientToAandE(List<Patient> patientList,Patient patient) throws UserException;//need to modify uml

}

