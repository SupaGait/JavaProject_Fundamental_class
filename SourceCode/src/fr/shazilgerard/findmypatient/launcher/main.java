/**
 * 
 */
package fr.shazilgerard.findmypatient.launcher;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoInitializationException;
import fr.shazilgerard.findmypatient.view.loginview;


public class main {
	public static void main(String[] args) {
		
		// Currently hard-coded Database string
		String dbUrl = "jdbc:derby://localhost:1527/PatientsDB;create=true";
		
		// Create the controller
		IdentityController controller = new IdentityController();
		
		// Try to setup the database connection
		try {
			controller.setupDatabase(dbUrl, "root", "root");
		} catch (DaoInitializationException e1) {
			JOptionPane.showMessageDialog(null, "Could not connect to the database. Make sure the database is running at: " + dbUrl
					, "Database connection error", JOptionPane.ERROR_MESSAGE);
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
