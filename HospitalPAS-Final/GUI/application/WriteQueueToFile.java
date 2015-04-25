package application;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

public class WriteQueueToFile {

	/*
	 public void WriteOutput(List<Patient> allPatientList) {
	
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"queueInfo.txt", true));
			for (Patient patient : allPatientList) {
				writer.write(patient.toString());
				writer.close();
			}
		} catch (IOException ex) {
			System.out.println();
		}
	}
	*/
	
	public void writeQueue(List<Patient> allPatientList){
		try{
			
			FileOutputStream fos = new FileOutputStream("QueueData.txt", true);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new Date().toString());
			oos.writeObject(allPatientList.toString()+"\n");
			oos.close();
			}catch(Exception e){
				System.out.println("file does not exist");
			}
		
	}
}
