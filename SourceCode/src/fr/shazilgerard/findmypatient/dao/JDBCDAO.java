/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	private final String DBTableName; 
	protected abstract List<DataType> parseQueryResultSet(ResultSet resultSet) throws SQLException;
	protected abstract String getInsertString(DataType dataType);
	
	
	/**
	 * @param DBTableName name of the main table in the DB containing the elements
	 */
	public JDBCDAO(String DBTableName)
	{
		this.DBTableName = DBTableName;
	}
	
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
			// TODO: create specialized exception DB connection exception.
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

		String queryString = getInsertString(data);
		try {
			final String sql = "INSERT INTO " + this.DBTableName+" "+ queryString;
			Statement createStatement = this.connection.createStatement();
			createStatement.execute(sql);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<DataType> readAll() {
		List<DataType> dataList = new ArrayList<DataType>();
		try {

			// Setup and execute the query
			PreparedStatement prepareStatement = this.connection.prepareStatement("select * from " + this.DBTableName);
			ResultSet rs = prepareStatement.executeQuery();

			// Let the concrete class parse the specifics
			dataList = parseQueryResultSet(rs);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return dataList;
	}

	@Override
	public List<DataType> search(DataType data, IMatcher<DataType> matcher) {
		List<DataType> dataList = new ArrayList<DataType>();

		try {
			// Setup and execute the query
			final String sqlMatchStatement = matcher.getSQLMatchStatement(this.DBTableName, data);
			PreparedStatement prepareStatement = this.connection.prepareStatement(sqlMatchStatement);
			ResultSet rs = prepareStatement.executeQuery();
			
			dataList = parseQueryResultSet(rs);

		} catch (Exception e) {
			System.out.println(e);

		}
		return dataList;
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
