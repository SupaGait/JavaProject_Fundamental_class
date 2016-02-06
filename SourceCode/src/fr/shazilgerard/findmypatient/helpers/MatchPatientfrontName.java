/**
 * 
 */
package fr.shazilgerard.findmypatient.helpers;

import fr.shazilgerard.findmypatient.datamodel.Patient;

public class MatchPatientfrontName implements IMatcher<Patient> {

	@Override
	public String getSQLMatchStatement(String tableName, Patient patient) {
		return "select * from "+tableName+" where FNAME='"+patient.getfName()+"'";
	}
}