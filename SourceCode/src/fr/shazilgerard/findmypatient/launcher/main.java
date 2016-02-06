/**
 * 
 */
package fr.shazilgerard.findmypatient.launcher;

import java.awt.EventQueue;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoInitializationException;
import fr.shazilgerard.findmypatient.view.loginview;


public class main {
	public static void main(String[] args) {
		
		// Create the controller
		IdentityController controller = new IdentityController();
		
		// Try to setup the database connection
		try {
			controller.setupDatabase("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
		} catch (DaoInitializationException e1) {
			e1.printStackTrace();
		}

		// Start GUI
		System.out.println("Starting GUI");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginview obj = new loginview(controller);
					System.out.println("Program running");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
