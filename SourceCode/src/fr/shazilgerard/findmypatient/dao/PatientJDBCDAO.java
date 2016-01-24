/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import java.util.ArrayList;
import java.util.List;

import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * @author Gerard
 *
 */
public class PatientJDBCDAO extends JDBCDAO<Patient> {

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#create(java.lang.Object)
	 */
	@Override
	public void create(Patient data) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#readAll()
	 */
	@Override
	public List<Patient> readAll() {

		// TODO fill with correct data
		ArrayList<Patient> patients = new ArrayList<Patient>();
		patients.add(new Patient("Gerard", "0231432", "Room 6"));
		
		return patients;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#search(java.lang.Object)
	 */
	@Override
	public List<Patient> search(Patient data, IMatcher<Patient> matcher) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#update(java.lang.Object)
	 */
	@Override
	public boolean update(Patient data) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Patient data) {
		// TODO Auto-generated method stub
		
	}

}
