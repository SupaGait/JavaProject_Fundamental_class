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

	@Override
	protected void readAllQuery(Connection connection, List<Patient> dataResult) throws SQLException {

		PreparedStatement prepareStatement = connection.prepareStatement("select * from PATIENTS");
		
		// Parse the query
		ResultSet rs = prepareStatement.executeQuery();
		while (rs.next()) {
			String name = rs.getString("NAME");
			String id = rs.getString("ID");
			String room = rs.getString("ROOM");

			Patient patient = new Patient(name, id, room);
			dataResult.add(patient);
		}
	}

	@Override
	protected void writeDataField() {
		// TODO Auto-generated method stub
		
	}

	}
