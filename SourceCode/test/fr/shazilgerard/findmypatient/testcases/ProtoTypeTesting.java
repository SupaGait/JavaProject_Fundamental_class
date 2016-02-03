/**
 * 
 */
package fr.shazilgerard.findmypatient.testcases;

import java.util.List;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.dao.PatientJDBCDAO;
import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.helpers.MatchPatientName;

public class ProtoTypeTesting {
	public static void main(String[] args) 
	{
		ProtoTypeTesting tests = new ProtoTypeTesting();
		tests.runTests();
		
		System.out.println("Program ended.");
	}
	
	public void runTests()
	{
		testDAOCreate();
		testDAOSearch();
		testDAOConnection();
		//testControllerAndDAO();
		
		
	}
	

	private void testDAOCreate() {
		System.out.println("--Test DAO create--");
		PatientJDBCDAO patientDAO = new PatientJDBCDAO();
		patientDAO.setDatabaseConnection("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
		patientDAO.connect();
		System.out.println("Database connected !");
		
		
		String newPatientName = "NewlyAddedPatient";
		Patient patientToAdd = new Patient(newPatientName, newPatientName, newPatientName, newPatientName, newPatientName, newPatientName, newPatientName, newPatientName);
		patientDAO.create(patientToAdd);
		printPatients(patientDAO.readAll() );
		patientDAO.disconnect();
	}

	private void testDAOSearch() {
		System.out.println("--Test DAO search--");
		PatientJDBCDAO patientDAO = new PatientJDBCDAO();
		patientDAO.setDatabaseConnection("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
		patientDAO.connect();
		
		Patient patientToSearch = new Patient("Gerard", null, null, null, null, null, null, null);
		MatchPatientName matcher = new MatchPatientName();
		List<Patient> patientsMatched = patientDAO.search(patientToSearch, matcher);
		
		printPatients(patientsMatched);
		
		patientDAO.disconnect();
	}

	private void testDAOConnection() {
		System.out.println("--Test DAO connection--");
		PatientJDBCDAO patientDAO = new PatientJDBCDAO();
		patientDAO.setDatabaseConnection("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
		patientDAO.connect();
		
		List<Patient> patients = patientDAO.readAll();
		printPatients(patients);
		
		patientDAO.disconnect();
	}

	private void testControllerAndDAO()
	{
		System.out.println("--Test identityController & dao--");
		IdentityController identityController = new IdentityController();
		List<Patient> patients = identityController.getPatientManagement().readAll();
		
		printPatients(patients);
	}
	
	private void printPatients(List<Patient> patients)
	{
		for (Patient patient : patients) {
			System.out.println( String.format("%s %s", patient.getpId() , patient.getfName()) );
		}
	}
}
