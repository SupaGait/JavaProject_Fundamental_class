/**
 * 
 */
package fr.shazilgerard.findmypatient.controller;

import fr.shazilgerard.findmypatient.dao.IDAOManagement;
import fr.shazilgerard.findmypatient.dao.PatientJDBCDAO;
import fr.shazilgerard.findmypatient.dao.UserJDBCDAO;

/**
 * @author Gerard
 *
 */
public class IdentityController {
	private UserManagement userManagement;
	private PatientManagement patientManagement;
	
	private IDAOManagement patientDAOManagement;
	private IDAOManagement userDAOManagement;
	
	public IdentityController()
	{
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

}
