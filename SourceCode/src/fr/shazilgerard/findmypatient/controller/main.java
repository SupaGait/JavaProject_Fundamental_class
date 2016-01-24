/**
 * 
 */
package fr.shazilgerard.findmypatient.controller;

import java.awt.EventQueue;

import fr.shazilgerard.findmypatient.view.PatientOverview;

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
		
		// Start GUI
		System.out.println("Starting GUI");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientOverview frame = new PatientOverview(controller);
					frame.setVisible(true);
					
					System.out.println("Program running");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
