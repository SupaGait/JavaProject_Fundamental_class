/**
 * 
 */
package fr.shazilgerard.findmypatient.testcases;

import java.util.List;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.datamodel.Patient;

/**
 * @author Gerard
 *
 */
public class ProtoTypeTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ProtoTypeTesting tests = new ProtoTypeTesting();
		tests.runTests();
		
		System.out.println("Program ended.");
	}
	
	public void runTests()
	{
		testPatientDAO();
	}
	
	public void testPatientDAO()
	{
		IdentityController identityController = new IdentityController();
		
		List<Patient> patients = identityController.getPatientManagement().readAll();
		
		for (Patient patient : patients) {
			System.out.println( String.format("%s %s %s", patient.getName(), patient.getId(), patient.getRoom()) );
		}
	}

}
