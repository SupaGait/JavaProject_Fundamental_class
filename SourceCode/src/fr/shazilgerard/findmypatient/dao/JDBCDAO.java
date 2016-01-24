/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * @author Gerard
 * @param <DataType> DataDAO type which is saved and retrieved through the DAO methods
 *
 */
public abstract class JDBCDAO<DataType> implements IDataDAO<DataType>, IDAOManagement {

	private Connection connection;
	private String url;
	private String userName;
	private String password;
	
	protected abstract void readAllQuery(Connection connection, List<DataType> dataResult) throws SQLException;
	protected abstract void writeDataField();
	
	/**
	 * @param url Url to the JDBC database
	 * @param name User name for connection to the DB
	 * @param password password for the connection to the DB
	 */
	public void setDatabaseConnection(String url, String name, String password)
	{
		this.url = url;
		this.userName = name;
		this.password = password;
	}
	
	@Override
	public void connect() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			
			// Set up the connection
			this.connection = DriverManager.getConnection(this.url, this.userName, this.password);
			System.out.println("SQL connection opened.");
			
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		try {
			this.connection.close();
			System.out.println("SQL connection closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void create(DataType data) {

	}

	@Override
	public List<DataType> readAll() {
		List<DataType> dataList = new ArrayList<DataType>();
		try {
			
			// Let the concrete class do the specifics
			readAllQuery(this.connection, dataList);

		} catch (Exception e) {
			System.out.println(e);
		}
		return dataList;
	}

	@Override
	public List<DataType> search(DataType data, IMatcher<DataType> matcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(DataType data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DataType data) {
		// TODO Auto-generated method stub
		
	}

}
