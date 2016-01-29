/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.shazilgerard.findmypatient.datamodel.Patient;

/**
 * @author Gerard
 *
 */
public class PatientJDBCDAO extends JDBCDAO<Patient> {

	public PatientJDBCDAO()
	{
		// Pass the table name
		super("PATIENTS");
	}
	
	@Override
	protected void initPrepareStatments() throws SQLException {
		String query;
		
		query = "INSERT INTO PATIENTS(NAME, ROOM) VALUES(?,?)";
		this.insertStmt = this.connection.prepareStatement(query);
		
		query = "UPDATE PATIENTS SET NAME=?,ROOM=? WHERE ID=?";
		this.updateStmt = this.connection.prepareStatement(query);
		
		query = "DELETE FROM PATIENTS WHERE ID=?";
		this.deleteStmt = this.connection.prepareStatement(query);
	}

	@Override
	protected List<Patient> parseQueryResultSet(ResultSet rs) throws SQLException {
		List<Patient> patientList = new ArrayList<Patient>();
		
		// Iterate through the results and add to list
		while (rs.next()) {
			String name = rs.getString("NAME");
			String room = rs.getString("ROOM");
			String id = rs.getString("ID");

			Patient patient = new Patient(name, room, id);
			patientList.add(patient);
		}
		return patientList;
	}
	
	@Override
	protected PreparedStatement insertData(Patient patient) throws SQLException
	{
		this.insertStmt.setString(1, patient.getName());
		this.insertStmt.setString(2, patient.getRoom());
		return this.insertStmt;
	}
	@Override
	protected PreparedStatement updateData(Patient patient) throws SQLException
	{
		this.updateStmt.setString(1, patient.getName());
		this.updateStmt.setString(2, patient.getRoom());
		this.updateStmt.setString(3, patient.getId());
		return this.updateStmt;
	}
	@Override
	protected PreparedStatement deleteData( Patient patient) throws SQLException
	{
		deleteStmt.setString(3, patient.getId());
		return this.deleteStmt;
	}
}
