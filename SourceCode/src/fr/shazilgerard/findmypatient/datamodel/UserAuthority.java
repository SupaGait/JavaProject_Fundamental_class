/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel;

import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;
import fr.shazilgerard.findmypatient.helpers.MatchUserName;

import java.util.List;

/**
 * @author Gerard
 *
 */
public class UserAuthority {
	
	private final MatchUserName userMatcher = new MatchUserName();
	private IDataDAO<User> userDAO;
	private User currentUser = null;
	
	public enum UserRights{
		None,
		ReadOnly,
		ReadWrite
	}
	
	public UserAuthority(IDataDAO<User> userDAO)
	{
		this.userDAO = userDAO;
	}
	
	/**
	 * @param userName name to log in with
	 * @param password password to log in with
	 * @throws NoAuthorityException User not found, no authority
	 */
	public void login(String userName, String password) throws NoAuthorityException
	{
		User searchUser = new User(userName, password);
		
		// Search for the user
		List<User> foundUsers = this.userDAO.search(searchUser, userMatcher);
		if(foundUsers.size() > 0)
		{
			this.currentUser = foundUsers.get(0);
		}

		throw new NoAuthorityException();
	}
	
	public String getUserName()
	{
		if(this.currentUser != null)
			return this.currentUser.getName();
		else
			return "No user logged in";
	}
	
	/**
	 * @return the User rights of the current User logged in the system
	 */
	public UserRights getUserRights()
	{
		// TODO: find rights from DB
		return UserRights.None;
	}

}
