/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel.exceptions;

/**
 * Current user doesn't have sufficient rights
 * @author Gerard
 */
public class NoAuthorityException extends Exception {
	public NoAuthorityException() {
		super("Not sufficient rights to access.");
	}
}
