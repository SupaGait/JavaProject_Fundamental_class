
package fr.shazilgerard.findmypatient.datamodel;

import java.util.List;
import fr.shazilgerard.findmypatient.dao.IDataDAO;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoLoadObjectException;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoSaveObjectException;
import fr.shazilgerard.findmypatient.datamodel.UserAuthority.UserRights;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;
import fr.shazilgerard.findmypatient.datamodel.exceptions.UserAlreadyExistsException;
import fr.shazilgerard.findmypatient.helpers.IMatcher;
import fr.shazilgerard.findmypatient.helpers.MatchUserName;

/**
 * Contains the logic for modifying the users in the system.
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
	 * @throws NoAuthorityException
	 * @throws UserAlreadyExistsException
	 * @throws DaoSaveObjectException  
	 */
	public void add(User user) throws NoAuthorityException, UserAlreadyExistsException, DaoSaveObjectException
	{
		checkMinimalRights(UserRights.ReadWriteAndUserManagement);
		
		List<User> foundUsers;
		// Check if the uses does not already exists
		try {
			foundUsers = this.userDAO.search(user, userMatcher);
		} catch (DaoLoadObjectException e) {
			// There was a problem with searching, so saving is impossible.
			throw new DaoSaveObjectException(user,e);
		}
		
		// Exception if the user already exists
		if(foundUsers.size() > 0){
			throw new UserAlreadyExistsException();
		}
		
		// User does not exists, so add it
		this.userDAO.create(user);
	}
	/**
	 * Delete the user
	 * @param user User to be deleted
	 * @throws NoAuthorityException
	 * @throws DaoSaveObjectException 
	 */
	public void delete(User user) throws NoAuthorityException, DaoSaveObjectException
	{
		checkMinimalRights(UserRights.ReadWriteAndUserManagement);
		this.userDAO.delete(user);
	}
	/**
	 * Update the User
	 * @param user User to be updated
	 * @throws NoAuthorityException
	 * @throws DaoSaveObjectException 
	 */
	public void update(User user) throws NoAuthorityException, DaoSaveObjectException
	{
		checkMinimalRights(UserRights.ReadWriteAndUserManagement);
		this.userDAO.update(user);
	}
	
	/**
	 * Find a user using a specific matcher
	 * @param user User to be find
	 * @param matcher Matched containing match logic
	 * @return a List of found Users
	 * @throws NoAuthorityException
	 * @throws DaoLoadObjectException 
	 */
	public List<User> find(User user, IMatcher<User> matcher) throws NoAuthorityException, DaoLoadObjectException
	{
		checkMinimalRights(UserRights.ReadWriteAndUserManagement);
		return this.userDAO.search(user, userMatcher);
	}

	/**
	 * Checks if the current user has the minimal authority level
	 * @param rights minimal right level
	 * @throws NoAuthorityException
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
