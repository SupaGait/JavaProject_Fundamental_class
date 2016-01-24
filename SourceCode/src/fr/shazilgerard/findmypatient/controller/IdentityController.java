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
	
	private PatientOverview patientGUI; // TODO: Interface?
	
	public IdentityController()
	{
		settings = new Settings();
		
		// Create UserJDBCDAO, save the management for cleanup
		UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
		this.userDAOManagement = userJDBCDAO;

		// Create UserJDBCDAO, save the management for cleanup
		PatientJDBCDAO patientJDBCDAO = new PatientJDBCDAO();
		this.patientDAOManagement = patientJDBCDAO;
		
		// Create the Patient and User management, pass the relative DAO's
		this.userManagement = new UserManagement(userJDBCDAO);
		this.patientManagement = new PatientManagement(patientJDBCDAO, userManagement);
		
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
