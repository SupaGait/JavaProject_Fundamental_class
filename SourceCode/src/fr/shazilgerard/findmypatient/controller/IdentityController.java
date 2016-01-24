/**
 * 
 */
package fr.shazilgerard.findmypatient.controller;

import fr.shazilgerard.findmypatient.config.Settings;
import fr.shazilgerard.findmypatient.dao.IDAOManagement;
import fr.shazilgerard.findmypatient.dao.PatientJDBCDAO;
import fr.shazilgerard.findmypatient.dao.UserJDBCDAO;
import fr.shazilgerard.findmypatient.datamodel.PatientManagement;
import fr.shazilgerard.findmypatient.datamodel.UserManagement;
import fr.shazilgerard.findmypatient.view.PatientOverview;

/**
 * @author Gerard
 * 
 * The Identity controller is in charge of reading local settings, 
 * creating the sub classes and providing access based on current login 
 * User. 
 *
 */
public class IdentityController {
	private Settings settings;
	
	private UserManagement userManagement;
	private PatientManagement patientManagement;
	private IDAOManagement patientDAOManagement;
	private IDAOManagement userDAOManagement;
	
	private PatientJDBCDAO patientJDBCDAO;
	private UserJDBCDAO userJDBCDAO;
	
	private PatientOverview patientGUI; // TODO: Interface?
	
	public IdentityController()
	{
		settings = new Settings();
		
		// Create UserJDBCDAO, save the management for cleanup
		this.userJDBCDAO = new UserJDBCDAO();
		this.userDAOManagement = userJDBCDAO;

		// Create UserJDBCDAO, save the management for cleanup
		this.patientJDBCDAO = new PatientJDBCDAO();
		this.patientDAOManagement = patientJDBCDAO;
		
		// Create the Patient and User management, pass the relative DAO's
		this.userManagement = new UserManagement(userJDBCDAO);
		this.patientManagement = new PatientManagement(patientJDBCDAO, userManagement);
	}
	
	/**
	 * Setup and enable the database connection
	 * @param url Url to the JDBC database
	 * @param name User name for connection to the DB
	 * @param password password for the connection to the DB
	 */
	public void setupDatabase(String url, String name, String password)
	{
		patientJDBCDAO.setDatabaseConnection(url, name, password);
		userJDBCDAO.setDatabaseConnection(url, name, password);
		
		// Enable connections
		userDAOManagement.connect();
		patientDAOManagement.connect();
	}
	
	/**
	 * Always needs to be called before exiting the program
	 */
	public void closeDataBase()
	{
		userDAOManagement.disconnect();
		patientDAOManagement.disconnect();
	}
	
	public UserManagement getUserManagement()
	{
		return this.userManagement;
	}
	
	public PatientManagement getPatientManagement()
	{
		return this.patientManagement;
	}

}
