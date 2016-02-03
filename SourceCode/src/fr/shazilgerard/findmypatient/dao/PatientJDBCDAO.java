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

	
	protected List<Patient> parseQueryResultSet(ResultSet rs) throws SQLException {
		List<Patient> patientList = new ArrayList<Patient>();
		
		// Iterate through the results and add to list
		while (rs.next()) {
			String pId = rs.getString("id");
			String ssnNo = rs.getString("ssn");
			String fName = rs.getString("fname");
			String lName = rs.getString("lname");
			String dob = rs.getString("dob");
			String cellNo = rs.getString("cellNo");
			String email = rs.getString("email");
			String displayname = rs.getString("displayname");
			
			Patient Patient = new Patient(pId, ssnNo, fName, lName, dob, cellNo, email, displayname);
			patientList.add(Patient);
		}
		return patientList;
	}
	
	@Override
	protected PreparedStatement insertData(Patient patient) throws SQLException
	{
		PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO PATIENTS(SSN, FNAME, LNAME, DOB, CELLNO, EMAIL, DISPLAYNAME) VALUES(?,?,?,?,?,?,?)");
		
		
		stmt.setString(1, patient.getSsnNo());
		stmt.setString(2, patient.getfName());
		stmt.setString(3, patient.getlName());
		stmt.setString(4, patient.getDob());
		stmt.setString(5, patient.getCellNo());
		stmt.setString(6, patient.getEmail());
		stmt.setString(7, patient.getDisplayName());
		
		return stmt;
	}
	@Override
	protected PreparedStatement updateData(Patient patient) throws SQLException
	{
		PreparedStatement stmt = this.connection.prepareStatement("UPDATE PATIENTS SET ID=?, SSN=?, FNAME=?, LNAME=?, DOB=?, CELLNO=?, EMAIL=?, DISPLAYNAME=? WHERE ID=?,SSN=?, FNAME=?, LNAME=?, DOB=?, CELLNO=?, EMAIL=?, DISPLAYNAME=? ");
		stmt.setString(1, patient.getpId());
		stmt.setString(2, patient.getSsnNo());
		stmt.setString(3, patient.getfName());
		stmt.setString(4, patient.getlName());
		stmt.setString(5, patient.getDob());
		stmt.setString(6, patient.getCellNo());
		stmt.setString(7, patient.getEmail());
		stmt.setString(8, patient.getDisplayName());
		return stmt;
	}
	@Override
	protected PreparedStatement deleteData( Patient patient) throws SQLException
	{
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM PATIENTS WHERE ID=? ");
		stmt.setString(3, patient.getpId());
		return stmt;
	}
}
