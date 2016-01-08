/**
 * 
 */
package fr.shazilgerard.findmypatient.controller;

import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.datamodel.Patient;

/**
 * @author Gerard
 *
 */
public class PatientManagement {
	UserManagement userManagement;
	IDataDAO<Patient> patientDAO;
	
	/**
	 * @param patientDAO DAO responsible for patient data
	 * @param userManagement User management which checks credentials
	 */
	public PatientManagement(IDataDAO<Patient> patientDAO, 
			UserManagement userManagement)
	{
		this.userManagement = userManagement;
		this.patientDAO = patientDAO;
	}
}
