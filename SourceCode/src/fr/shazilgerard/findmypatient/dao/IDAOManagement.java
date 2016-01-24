/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

/**
 * @author Gerard
 *
 */
public interface IDAOManagement {
	/**
	 * Connect the DAO to the back-end
	 */
	public void connect();
	
	/**
	 * Disconnect the DAO from the back-end.
	 */
	public void disconnect();

}
