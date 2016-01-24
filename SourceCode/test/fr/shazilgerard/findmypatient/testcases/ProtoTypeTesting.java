/**
 * 
 */
package fr.shazilgerard.findmypatient.testcases;

import java.util.List;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.dao.PatientJDBCDAO;
import fr.shazilgerard.findmypatient.datamodel.Patient;

/**
 * @author Gerard
 *
 */
public class ProtoTypeTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ProtoTypeTesting tests = new ProtoTypeTesting();
		tests.runTests();
		
		System.out.println("Program ended.");
	}
	
	public void runTests()
	{
		testDAOConnection();
		testControllerAndDAO();
	}
	
	/**
	 * 
	 */
	private void testDAOConnection() {
		PatientJDBCDAO patientDAO = new PatientJDBCDAO();
		patientDAO.setDatabaseConnection("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
		patientDAO.connect();
		
		List<Patient> patients = patientDAO.readAll();
		printPatients(patients);
		
		patientDAO.disconnect();
	}

	private void testControllerAndDAO()
	{
		IdentityController identityController = new IdentityController();
		List<Patient> patients = identityController.getPatientManagement().readAll();
		
		printPatients(patients);
	}
	
	private void printPatients(List<Patient> patients)
	{
		for (Patient patient : patients) {
			System.out.println( String.format("%s %s %s", patient.getName(), patient.getId(), patient.getRoom()) );
		}
	}
}
