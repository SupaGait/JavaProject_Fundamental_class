/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import fr.shazilgerard.findmypatient.dao.exceptions.DaoInitializationException;

/**
 * @author Gerard
 *
 */
public interface IDAOManagement {
	/**
	 * Connect/initialize the DAO
	 *  - ! disconnect() should always be called when ready with the DAO.
	 * @throws DaoInitializationException 
	 */
	public void connect() throws DaoInitializationException;
	
	/**
	 * Disconnect the DAO, all resources will be released.
	 */
	public void disconnect();

}
