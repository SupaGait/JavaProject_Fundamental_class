/**
 * 
 */
package fr.shazilgerard.findmypatient.controller;

import fr.shazilgerard.findmypatient.config.Settings;
import fr.shazilgerard.findmypatient.dao.IDAOManagement;
import fr.shazilgerard.findmypatient.dao.PatientJDBCDAO;
import fr.shazilgerard.findmypatient.dao.UserJDBCDAO;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoInitializationException;
import fr.shazilgerard.findmypatient.datamodel.PatientManagement;
import fr.shazilgerard.findmypatient.datamodel.UserAuthority;
import fr.shazilgerard.findmypatient.datamodel.UserManagement;

/**
 *
 * 
 * The Identity identityController is in charge of reading local settings, 
 * creating the sub classes and providing access based on current login 
 * User. 
 *
 */
public class IdentityController {
	private Settings settings;
	
	private UserManagement userManagement;
	private UserAuthority userAuthority;
	private PatientManagement patientManagement;
	private IDAOManagement patientDAOManagement;
	private IDAOManagement userDAOManagement;
	
	private PatientJDBCDAO patientJDBCDAO;
	private UserJDBCDAO userJDBCDAO;
	
	/**
	 * Create a new instance of the identity controller
	 */
	public IdentityController()
	{
		this.settings = new Settings();
		
		// Create UserJDBCDAO, save the management for cleanup
		this.userJDBCDAO = new UserJDBCDAO();
		this.userDAOManagement = this.userJDBCDAO;

		// Create UserJDBCDAO, save the management for cleanup
		this.patientJDBCDAO = new PatientJDBCDAO();
		this.patientDAOManagement = this.patientJDBCDAO;
		
		// Create the User Authority
		this.userAuthority = new UserAuthority(this.userJDBCDAO);
		
		// Create the Patient and User management, pass the relative DAO's
		this.userManagement = new UserManagement(this.userJDBCDAO, this.userAuthority);
		this.patientManagement = new PatientManagement(this.patientJDBCDAO, this.userAuthority);
	}
	
	/**
	 * Setup and enable the database connection
	 * @param url Url to the JDBC database
	 * @param name User name for connection to the DB
	 * @param password password for the connection to the DB
	 * @throws DaoInitializationException 
	 */
	public void setupDatabase(String url, String name, String password) throws DaoInitializationException
	{
		patientJDBCDAO.setDatabaseConnection(url, name, password);
		userJDBCDAO.setDatabaseConnection(url, name, password);
		
		// Enable connections
		userDAOManagement.connect();
		patientDAOManagement.connect();
	}
	
	/**
	 * Closes the connections to the database
	 * Always needs to be called before exiting the program
	 */
	public void closeDataBase()
	{
		userDAOManagement.disconnect();
		patientDAOManagement.disconnect();
	}
	
	/**
	 * @return UserManagement 
	 */
	public UserManagement getUserManagement()
	{
		return this.userManagement;
	}
	
	/**
	 * @return Patient management
	 */
	public PatientManagement getPatientManagement()
	{
		return this.patientManagement;
	}

	/**
	 * @return the userAuthority
	 */
	public UserAuthority getUserAuthority() {
		return userAuthority;
	}
}
