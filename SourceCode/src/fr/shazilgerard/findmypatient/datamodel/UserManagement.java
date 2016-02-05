
package fr.shazilgerard.findmypatient.datamodel;

import java.util.List;
import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.datamodel.UserAuthority.UserRights;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;
import fr.shazilgerard.findmypatient.datamodel.exceptions.UserAlreadyExistsException;
import fr.shazilgerard.findmypatient.helpers.IMatcher;
import fr.shazilgerard.findmypatient.helpers.MatchUserName;

/**
 * Contains the logic for modifying the users in the system.
 * @author Gerard
 *
 */
public class UserManagement {
	
	private IDataDAO<User> userDAO;
	private UserAuthority userAuthority;
	private final MatchUserName userMatcher = new MatchUserName();

	/**
	 * Creates a new instance of the User management
	 * @param userDAO DAO which will take care of the CRUD operations
	 * @param userAuthority authority manager
	 */
	public UserManagement(IDataDAO<User> userDAO, UserAuthority userAuthority)
	{
		this.userDAO = userDAO;
		this.userAuthority = userAuthority;
	}
	
	/**
	 * Add a new user
	 * Username should be unique
	 * @param user User to be added
	 * @throws NoAuthorityException thrown if the current user doesn't have sufficient rights
	 * @throws UserAlreadyExistsException thrown if the user alread exits in the system
	 */
	public void add(User user) throws NoAuthorityException, UserAlreadyExistsException
	{
		checkMinimalRights(UserRights.ReadWriteAndUserManagement);
		
		// Check if the uses does not already exists
		List<User> foundUsers = this.userDAO.search(user, userMatcher);
		if(foundUsers.size() > 0)
		{
			throw new UserAlreadyExistsException();
		}
		
		// User does not exists, so add it
		this.userDAO.create(user);
	}
	/**
	 * Delete the user
	 * @param user User to be deleted
	 * @throws NoAuthorityException thrown if the current user doesn't have sufficient rights
	 */
	public void delete(User user) throws NoAuthorityException
	{
		checkMinimalRights(UserRights.ReadWriteAndUserManagement);
		this.userDAO.delete(user);
	}
	/**
	 * Update the User
	 * @param user User to be updated
	 * @throws NoAuthorityException thrown if the current user doesn't have sufficient rights
	 */
	public void update(User user) throws NoAuthorityException
	{
		checkMinimalRights(UserRights.ReadWriteAndUserManagement);
		this.userDAO.update(user);
	}
	
	/**
	 * Find a user using a specific matcher
	 * @param user User to be find
	 * @param matcher Matched containing match logic
	 * @return a List of found Users
	 * @throws NoAuthorityException thrown if the current user doesn't have sufficient rights
	 */
	public List<User> find(User user, IMatcher<User> matcher) throws NoAuthorityException
	{
		checkMinimalRights(UserRights.ReadWriteAndUserManagement);
		return this.userDAO.search(user, userMatcher);
	}

	/**
	 * Checks if the current user has the minimal authority level
	 * @param rights minimal right level
	 * @throws NoAuthorityException NoAuthorityException if level of current user is not sufficient
	 */
	private void checkMinimalRights(UserRights rights) throws NoAuthorityException
	{
		if(this.userAuthority.getUserRights().getValue() >= rights.getValue()){
			return;
		}
		else{
			throw new NoAuthorityException();
		}
	}
}
