/**
 * 
 */
package fr.shazilgerard.findmypatient.helpers;

import fr.shazilgerard.findmypatient.datamodel.Patient;

public class MatchPatientName implements IMatcher<Patient> {

	@Override
	public String getSQLMatchStatement(String tableName, Patient patient) {
		return "select * from "+tableName+" where DisplayName='"+patient.getDisplayName()+"'";
	}
}
