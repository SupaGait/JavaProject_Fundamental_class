/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.datamodel.User;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * @author Gerard
 *
 */
public class UserJDBCDAO extends JDBCDAO<User> {

	public UserJDBCDAO()
	{
		// Pass the table name
		super("USERS");
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.JDBCDAO#initPrepareStatments()
	 */
	@Override
	protected void initPrepareStatments() throws SQLException {
		
		// TODO
		this.insertStmt = this.connection.prepareStatement(
				"INSERT INTO USERS(NAME, PASSWORD) VALUES(?,?)");
		
		this.updateStmt = this.connection.prepareStatement(
				"UPDATE USERS SET NAME=?,PASSWORD=? WHERE ID=?");
		
		this.deleteStmt = this.connection.prepareStatement(
				"DELETE FROM USERS WHERE ID=?");
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.JDBCDAO#parseQueryResultSet(java.sql.ResultSet)
	 */
	@Override
	protected List<User> parseQueryResultSet(ResultSet resultSet) throws SQLException {
		List<User> userList = new ArrayList<User>();
		
		// Iterate through the results and add to list
		while (resultSet.next()) {
			String name = resultSet.getString("NAME");
			String pass = resultSet.getString("PASSWORD");
			String id = resultSet.getString("ID");

			User user = new User(name, pass, id);
			userList.add(user);
		}
		return userList;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.JDBCDAO#insertData(java.lang.Object)
	 */
	@Override
	protected PreparedStatement insertData(User user) throws SQLException {
		this.insertStmt.setString(1, user.getName());
		this.insertStmt.setString(2, user.getPassword());
		return this.insertStmt;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.JDBCDAO#updateData(java.lang.Object)
	 */
	@Override
	protected PreparedStatement updateData(User user) throws SQLException {
		this.updateStmt.setString(1, user.getName());
		this.updateStmt.setString(2, user.getPassword());
		this.updateStmt.setString(3, user.getId());
		return this.updateStmt;
	}

	/* (non-Javadoc)
	 * @see fr.shazilgerard.findmypatient.dao.JDBCDAO#deleteData(java.lang.Object)
	 */
	@Override
	protected PreparedStatement deleteData(User user) throws SQLException {
		deleteStmt.setString(3, user.getId());
		return this.deleteStmt;
	}
}
