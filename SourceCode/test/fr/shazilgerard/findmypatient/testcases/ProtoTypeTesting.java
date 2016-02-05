/**
 * 
 */
package fr.shazilgerard.findmypatient.testcases;

import java.util.List;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.dao.PatientJDBCDAO;
import fr.shazilgerard.findmypatient.dao.UserJDBCDAO;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoLoadObjectException;
import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.datamodel.UserAuthority;
import fr.shazilgerard.findmypatient.datamodel.UserManagement;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;
import fr.shazilgerard.findmypatient.helpers.MatchPatientName;

/**
 * Testing different functionalities of the PatientFind application
 * @author Gerard
 */
public class ProtoTypeTesting {
	public static void main(String[] args) 
	{
		ProtoTypeTesting tests = new ProtoTypeTesting();
		tests.runTests();
		
		System.out.println("Program ended.");
	}
	
	/**
	 * Run the different test cases
	 */
	public void runTests()
	{
		testDAOCreate().run();
		testDAOSearch().run();
		testDAOConnectionWithReadAll().run();
		testControllerAndDAO().run();
		testUserLogin().run();
	}
	
	
	/**
	 * Test if the Login of a user works
	 * @throws Exception 
	 */
	private TestWrapper testUserLogin(){
		TestWrapper test = new TestWrapper() {	
			
			@Override
			public void test() throws Exception {
				System.out.println("--Test User login --");
				
				// Create a DAO
				UserJDBCDAO userDAO = new UserJDBCDAO();
				userDAO.setDatabaseConnection("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
				userDAO.connect();
				
				// Test Authority functionality
				UserAuthority userAuthority = new UserAuthority(userDAO);
				try {
					userAuthority.login("admin", "admin");
					System.out.println(String.format("Logged in as: %s", userAuthority.getUserName()) );
					userAuthority.login("unknown use 2134", "");
					
				} catch (NoAuthorityException e) {
					System.out.println("No authority to enter the system");
				}
				
				// Disconnect
				userDAO.disconnect();
			}
		};
		return test;
	}

	/**
	 * Test if DAO create works
	 */
	private TestWrapper testDAOCreate(){
		TestWrapper test = new TestWrapper() {
			
			@Override
			protected void test() throws Exception {
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
		};
		return test;
	}

	/**
	 * Test DAO search functionality
	 * @return
	 */
	private TestWrapper testDAOSearch() {
		TestWrapper test = new TestWrapper() {
			
			@Override
			protected void test() throws Exception {
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
		};
		return test;
	}

	/**
	 * Test if setting up connection and reading patients works
	 * @return
	 */
	private TestWrapper testDAOConnectionWithReadAll() {
		TestWrapper test = new TestWrapper() {
			
			@Override
			protected void test() throws Exception {
				System.out.println("--Test DAO connection--");
				PatientJDBCDAO patientDAO = new PatientJDBCDAO();
				patientDAO.setDatabaseConnection("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
				patientDAO.connect();
				
				List<Patient> patients = patientDAO.readAll();
				printPatients(patients);
				
				patientDAO.disconnect();
			}
		};
		return test;
	}

	/**
	 * Test if the authorization works
	 * @return
	 */
	private TestWrapper testControllerAndDAO()
	{
		TestWrapper test = new TestWrapper() {
			
			@Override
			protected void test() throws Exception {
				System.out.println("--Test identityController with login and dao--");
				
				IdentityController identityController = new IdentityController();
				
				// For testing, hard-code the address of DB now
				identityController.setupDatabase("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
				
				// Login : Make sure admin admin is in database
				try {
					identityController.getUserAuthority().login("admin", "admin");
				} catch (NoAuthorityException e1) {
					e1.printStackTrace();
				}
				
				// DAO : get all patients
				List<Patient> patients = null;
				try {
					patients = identityController.getPatientManagement().readAll();
				} catch (NoAuthorityException e) {
					e.printStackTrace();
				}
				
				System.out.println("Fetched: " + patients.size() + " patients from the DB.");
				
				identityController.closeDataBase();
			}
		};
		return test;
	}
	
	/**
	 * Print patients
	 * @param patients
	 */
	private void printPatients(List<Patient> patients)
	{
		if(patients != null)
		{
			for (Patient patient : patients) {
				System.out.println( String.format("%s %s", patient.getpId() , patient.getfName()) );
			}
		}
	}
}
