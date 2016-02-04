/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel.exceptions;

/**
 * @author Gerard
 *
 */
public class NoAuthorityException extends Exception {
	public NoAuthorityException() {
		super("Not sufficient rights to access.");
	}
}
