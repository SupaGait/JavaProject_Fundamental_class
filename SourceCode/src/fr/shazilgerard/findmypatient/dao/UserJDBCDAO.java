/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import java.util.List;

import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.datamodel.User;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * @author Gerard
 *
 */
public class UserJDBCDAO extends JDBCDAO<User> {

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#create(java.lang.Object)
	 */
	@Override
	public void create(User data) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#readAll()
	 */
	@Override
	public List<User> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#search(java.lang.Object)
	 */
	@Override
	public List<User> search(User data, IMatcher<User> matcher) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#update(java.lang.Object)
	 */
	@Override
	public boolean update(User data) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.IDataDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(User data) {
		// TODO Auto-generated method stub
		
	}

}
