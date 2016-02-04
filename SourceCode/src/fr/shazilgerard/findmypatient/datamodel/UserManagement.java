
package fr.shazilgerard.findmypatient.datamodel;

import java.util.List;
import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.datamodel.UserAuthority.UserRights;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;
import fr.shazilgerard.findmypatient.helpers.IMatcher;
import fr.shazilgerard.findmypatient.helpers.MatchUserName;

public class UserManagement {
	
	private IDataDAO<User> userDAO;
	private UserAuthority userAuthority;
	private MatchUserName userMatcher = new MatchUserName();

	public UserManagement(IDataDAO<User> userDAO, UserAuthority userAuthority)
	{
		this.userDAO = userDAO;
		this.userAuthority = userAuthority;
	}
	
	/**
	 * Add a new user
	 * Username should be unique
	 * @param user User to be added
	 */
	public void add(User user)
	{
		// TODO: check existence of username, then throw exception.

	}
	/**
	 * Delete the user
	 * @param user User to be deleted
	 */
	public void delete(User user)
	{
		
	}
	/**
	 * Update the User
	 * @param user User to be updated
	 */
	public void update(User user)
	{
		
	}
	
	/**
	 * Find a user using a specific matcher
	 * @param user User to be find
	 * @param matcher Matched containing match logic
	 * @return a List of found Users
	 */
	public List<User> find(User user, IMatcher<User> matcher)
	{
		return null;
	}
	
	public void checkRights(UserRights rights) throws NoAuthorityException
	{
		if(this.userAuthority.getUserRights().getValue() >= rights.getValue()){
			return;
		}
		else{
			throw new NoAuthorityException();
		}
	}
}
