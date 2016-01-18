/**
 * 
 */
package fr.shazilgerard.findmypatient.controller;

import java.util.List;

import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * @author Gerard
 *
 */
public class PatientManagement {
	private UserManagement userManagement;
	private IDataDAO<Patient> patientDAO;
	
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
	
	public void add(Patient patient)
	{
		// TODO: check rights
		this.patientDAO.create(patient);
	}
	public void delete(Patient patient)
	{
		// TODO: check rights
		this.patientDAO.delete(patient);
	}
	public void modify(Patient patient)
	{
		this.patientDAO.update(patient);
		// TODO: check rights
	}
	public List<Patient> search(Patient patient, IMatcher<Patient> matcher)
	{
		// TODO: check rights
		return this.patientDAO.search(patient, matcher);
	}
}
