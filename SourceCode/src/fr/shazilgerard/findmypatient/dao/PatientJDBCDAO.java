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

import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

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
			String id = rs.getString("ID");
			String room = rs.getString("ROOM");

			Patient patient = new Patient(name, id, room);
			patientList.add(patient);
		}
		
		return patientList;
	}

	@Override
	protected void writeDataField() {
		// TODO Auto-generated method stub
		
	}

	}
