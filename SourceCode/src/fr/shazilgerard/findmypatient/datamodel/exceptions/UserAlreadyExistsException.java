/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel.exceptions;

/**
 * @author Gerard
 *
 */
public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException() {
		super("The user already exists in the system.");
	}
}
