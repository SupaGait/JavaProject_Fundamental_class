/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.datamodel.User;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * @author Gerard
 *
 */
public class UserJDBCDAO extends JDBCDAO<User> {

	@Override
	public void create(User data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> search(User data, IMatcher<User> matcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User data) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(User data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readAllQuery(Connection connection, List<User> dataResult) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeDataField() {
		// TODO Auto-generated method stub
		
	}

}
