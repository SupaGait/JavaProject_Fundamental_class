/**
 * 
 */
package fr.shazilgerard.findmypatient.testcases;

import java.util.Iterator;
import java.util.List;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.dao.PatientJDBCDAO;
import fr.shazilgerard.findmypatient.dao.UserJDBCDAO;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoLoadObjectException;
import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.datamodel.UserAuthority;
import fr.shazilgerard.findmypatient.datamodel.UserManagement;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;
import fr.shazilgerard.findmypatient.helpers.MatchPatientDisplayName;
import fr.shazilgerard.findmypatient.helpers.MatchPatientfrontName;

/**
 * Testing different functionalities of the PatientFind application
 * 
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
		testDAOAddAndDelete().run();
		testDAOSearch().run();
		testDAOConnectionWithReadAll().run();
		testControllerAndDAO().run();
		testUserAuthority().run();
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
				
				Patient patientToAdd = new Patient("ssnNo", "fName", "lName", "dob", "cellNo","Email","displayName","roomNo");
				patientDAO.create(patientToAdd);
				printPatients(patientDAO.readAll() );
				patientDAO.disconnect();
			}
		};
		return test;
	}
	/**
	 * Test delete an object from the DAO
	 * @return
	 */
	private TestWrapper testDAOAddAndDelete(){
		TestWrapper test = new TestWrapper() {
			
			@Override
			protected void test() throws Exception {
				final String tempPatientDisplayName = "NewPatientToBeDeleted";
				
				System.out.println("--Test DAO delete--");
				PatientJDBCDAO patientDAO = new PatientJDBCDAO();
				patientDAO.setDatabaseConnection("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
				patientDAO.connect();
				
				// Add a patient
				Patient patientToAdd = new Patient("SSNO", "fName", "lName", "dob", "cellNo","Email",tempPatientDisplayName,"roomNo");
				patientDAO.create(patientToAdd);
				
				// Search the new patient
				Patient patientToSearch = new Patient();
				patientToSearch.setDisplayName(tempPatientDisplayName);
				List<Patient> patientList = patientDAO.search(patientToSearch, new MatchPatientDisplayName());
				
				if(patientList.size() <= 0)
					throw new Exception("Added patient did not succeed");
				
				printPatients(patientList);
				// Delete the patients
				for (Patient patient : patientList) {
					patientDAO.delete(patient);
				}
				
				if(patientList.size() > 0)
					throw new Exception("Removing patient did not succeed");
				else
					System.out.println("Succesfully deleted the newly added patient.s");
				
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
				MatchPatientfrontName matcher = new MatchPatientfrontName();
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
	 * Test if the Login of a user works
	 * @throws Exception 
	 */
	private TestWrapper testUserAuthority(){
		TestWrapper test = new TestWrapper() {	
			
			@Override
			public void test() throws Exception {
				System.out.println("--Test User authority --");
				
				// Create a user DAO
				UserJDBCDAO userDAO = new UserJDBCDAO();
				userDAO.setDatabaseConnection("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
				userDAO.connect();
				
				// Test Authority functionality
				UserAuthority userAuthority = new UserAuthority(userDAO);
				
				// Log in as admin and read and get rights
				userAuthority.login("admin", "admin");
				System.out.println(String.format("Logged in as: %s, Rights: %s", 
						userAuthority.getUserName(),
						userAuthority.getUserRights()) );
				
				// Test logout
				userAuthority.logout();
				if(!userAuthority.getUserName().equals(""))
					throw new Exception("Logout not working");
				
				// Test without password
				{
					boolean userRejected = false;
					try {
						userAuthority.login("admin", "");
					} catch (NoAuthorityException e) {
						userRejected = true;
					}
					if(!userRejected)
						throw new Exception("Password check not working");
				}
				
				// Test unknown user, should be rejected
				{
					boolean userRejected = false;
					try {
						userAuthority.login("348942njasdh", "");
					} catch (NoAuthorityException e) {
						userRejected = true;
					}
					if(!userRejected)
						throw new Exception("Rejecting unkown users not working");
				}

				// Disconnect
				userDAO.disconnect();
			}
		};
		return test;
	}

	
	/**
	 * Test if the authorization integrated with controller works
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
	 * 
	 */
	private void printPatients(List<Patient> patients)
	{
		if(patients != null)
		{
			for (Patient patient : patients) {
				System.out.println( String.format("%s %s", patient.getpId() , patient) );
			}
		}
	}
}
