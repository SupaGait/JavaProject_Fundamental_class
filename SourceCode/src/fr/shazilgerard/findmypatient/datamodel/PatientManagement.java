/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel;

import java.util.List;

import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoLoadObjectException;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoSaveObjectException;
import fr.shazilgerard.findmypatient.datamodel.UserAuthority.UserRights;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * Contains the logic for modifying the patients in the system.
 * @author Gerard
 *
 */
public class PatientManagement {
	private UserAuthority userAuthority;
	private IDataDAO<Patient> patientDAO;
	
	/**
	 * @param patientDAO DAO responsible for CRUD operations with patient data
	 * @param userAuthority takes care of authority checks
	 */
	public PatientManagement(IDataDAO<Patient> patientDAO, UserAuthority userAuthority)
	{
		this.userAuthority = userAuthority;
		this.patientDAO = patientDAO;
	}
	
	/**
	 * @param patient Patient to be added to the system
	 * @throws NoAuthorityException
	 * @throws DaoSaveObjectException 
	 */
	public void add(Patient patient) throws NoAuthorityException, DaoSaveObjectException
	{
		checkMinimalRights(UserRights.ReadWrite);
		this.patientDAO.create(patient);
	}
	/**
	 * Delete a patient from the database
	 * @param patient patient to be deleted
	 * @throws NoAuthorityException
	 * @throws DaoSaveObjectException 
	 */
	public void delete(Patient patient) throws NoAuthorityException, DaoSaveObjectException
	{
		checkMinimalRights(UserRights.ReadWrite);
		this.patientDAO.delete(patient);
	}
	/**
	 * Update the data from the given patient
	 * @param patient to be changed
	 * @throws NoAuthorityException
	 * @throws DaoSaveObjectException 
	 */
	public void modify(Patient patient) throws NoAuthorityException, DaoSaveObjectException
	{
		checkMinimalRights(UserRights.ReadWrite);
		this.patientDAO.update(patient);
	}
	/**
	 * @param patient patient containing fields to be searched for
	 * @param matcher matcher selected which search for specific fields in the given patient
	 * @return List of Patients
	 * @throws NoAuthorityException
	 * @throws DaoLoadObjectException 
	 */
	public List<Patient> search(Patient patient, IMatcher<Patient> matcher) throws NoAuthorityException, DaoLoadObjectException
	{
		checkMinimalRights(UserRights.ReadOnly);
		return this.patientDAO.search(patient, matcher);
	}
	/**
	 * @return a list of all patients
	 * @throws NoAuthorityException
	 * @throws DaoLoadObjectException 
	 */
	public List<Patient> readAll() throws NoAuthorityException, DaoLoadObjectException
	{
		checkMinimalRights(UserRights.ReadOnly);
		return this.patientDAO.readAll();
	}
	
	/**
	 * Checks if the current user has the minimal authority level
	 * @param rights minimal right level
	 * @throws NoAuthorityException
	 */
	private void checkMinimalRights(UserRights rights) throws NoAuthorityException
	{
		if(this.userAuthority.getUserRights().getValue() >= rights.getValue()){
			return;
		}
		else{
			throw new NoAuthorityException();
		}
	}
}
