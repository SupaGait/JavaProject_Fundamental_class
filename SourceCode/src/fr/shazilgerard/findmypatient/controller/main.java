/**
 * 
 */
package fr.shazilgerard.findmypatient.controller;

import java.awt.EventQueue;

import fr.shazilgerard.findmypatient.dao.exceptions.DaoInitializationException;
import fr.shazilgerard.findmypatient.view.PatientOverviewView;
import fr.shazilgerard.findmypatient.view.loginview;

/**
 * @author Gerard
 *
 */
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Controller
	
		IdentityController controller = new IdentityController();
		try {
			controller.setupDatabase("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
		} catch (DaoInitializationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Start GUI
		System.out.println("Starting GUI");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//PatientOverviewView frame = new PatientOverviewView(controller);
					//frame.setVisible(true);
					
					loginview obj = new loginview(controller);
					
					
					System.out.println("Program running");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
