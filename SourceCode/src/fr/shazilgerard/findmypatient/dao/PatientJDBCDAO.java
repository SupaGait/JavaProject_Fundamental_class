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
		PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO PATIENTS(NAME, ROOM) VALUES(?,?)");
		stmt.setString(1, patient.getName());
		stmt.setString(2, patient.getRoom());
		return stmt;
	}
	@Override
	protected PreparedStatement updateData(Patient patient) throws SQLException
	{
		PreparedStatement stmt = this.connection.prepareStatement("UPDATE PATIENTS SET NAME=?,ROOM=? WHERE ID=?");
		stmt.setString(1, patient.getName());
		stmt.setString(2, patient.getRoom());
		stmt.setString(3, patient.getId());
		return stmt;
	}
	@Override
	protected PreparedStatement deleteData( Patient patient) throws SQLException
	{
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM PATIENTS WHERE ID=?");
		stmt.setString(3, patient.getId());
		return stmt;
	}
}
