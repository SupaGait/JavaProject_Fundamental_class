/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel.exceptions;

/**
 * The user already exists in the system.
 * @author Gerard
 */
public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException() {
		super("The user already exists in the system.");
	}
}
