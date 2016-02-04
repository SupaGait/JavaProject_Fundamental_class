/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel;

import java.util.List;

import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

public class PatientManagement {
	private UserAuthority userAuthority;
	private IDataDAO<Patient> patientDAO;
	
	/**
	 * @param patientDAO DAO responsible for patient data
	 * @param userAuthority User management which checks credentials
	 */
	public PatientManagement(IDataDAO<Patient> patientDAO, UserAuthority userAuthority)
	{
		this.userAuthority = userAuthority;
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
	public List<Patient> readAll()
	{
		return this.patientDAO.readAll();
		// TODO: check rights
	}
}
