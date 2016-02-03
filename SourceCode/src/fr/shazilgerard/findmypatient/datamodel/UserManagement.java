/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel;

import java.util.List;

import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.helpers.IMatcher;
import fr.shazilgerard.findmypatient.helpers.MatchUserName;

/**
 * @author Gerard
 *
 */
public class UserManagement {
	
	private IDataDAO<User> userDAO;
	private MatchUserName userMatcher = new MatchUserName();

	/**
	 * @param userDAO The DAO responsible for saving and loading user data
	 */
	public UserManagement(IDataDAO<User> userDAO)
	{
		this.userDAO = userDAO;
	}
	
	public void login(String userName, String password)
	{
		User searchUser = new User(userName, password);
		
		// Search for the user
		List<User> foundUsers = this.userDAO.search(searchUser, userMatcher);
		if(foundUsers.size() > 0)
		{
			// TODO: Find user
		}
	}
	
	///////////////////////////////////////////////////////////////
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
