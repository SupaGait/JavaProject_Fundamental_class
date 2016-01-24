/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel;

import java.util.List;

import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * @author Gerard
 *
 */
public class UserManagement {
	
	private IDataDAO<User> userDAO;

	/**
	 * @param userDAO The DAO responsible for saving and loading user data
	 */
	public UserManagement(IDataDAO<User> userDAO)
	{
		this.userDAO = userDAO;
	}
	
	public void add(User user)
	{

	}
	public void delete(User user)
	{
		
	}
	public void modify(User user)
	{
		
	}
	public List<User> find(IMatcher<User> matcher)
	{
		return null;
	}
}
