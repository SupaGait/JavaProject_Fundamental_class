//The match patient front name file
package fr.shazilgerard.findmypatient.helpers;

import fr.shazilgerard.findmypatient.datamodel.Patient;

public class MatchPatientfrontName implements IMatcher<Patient> {

	@Override
	public String getSQLMatchStatement(String tableName, Patient patient) {
		return "select * from "+tableName+" where FNAME='"+patient.getfName()+"'";
	}

	@Override
	public String toString() {
		return "First name";
	}
}
