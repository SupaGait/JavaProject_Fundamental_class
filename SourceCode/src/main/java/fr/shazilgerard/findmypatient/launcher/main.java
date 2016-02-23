/**
 * 
 */
package fr.shazilgerard.findmypatient.launcher;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import fr.shazilgerard.findmypatient.config.exceptions.ConfigurationFileException;
import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoInitializationException;
import fr.shazilgerard.findmypatient.view.loginview;


public class main {
	public static void main(String[] args) {
		
		// Startup
		try {
			// Create the controller
			IdentityController controller = new IdentityController();
			
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
		// Configuration loading failed
		catch (ConfigurationFileException e1) {
			JOptionPane.showMessageDialog(null, "Error loading configuration files", "Startup failed", JOptionPane.ERROR_MESSAGE);
		}
	}

}
