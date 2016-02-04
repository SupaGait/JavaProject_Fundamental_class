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
		None(0),
		ReadOnly(1),
		ReadWrite(2),
		ReadWriteAndUserManagement(3);
		
		private final int rightValue;
		UserRights(int value){
			this.rightValue = value;
		}
		public int getValue(){
			return this.rightValue;
		}
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
			return;
		}

		throw new NoAuthorityException();
	}
	
	/**
	 * Get the username of the current user
	 * @return The current user
	 */
	public String getUserName()
	{
		if(this.currentUser != null)
			return this.currentUser.getName();
		else
			return "No user logged in";
	}
	
	/**
	 * Get the rights of the current user
	 * @return user rights
	 */
	public UserRights getUserRights()
	{
		UserRights rights = UserRights.None;
		if(this.currentUser != null)
		{
			switch(this.currentUser.getRights())
			{
				case "ReadOnly":
					rights = UserRights.ReadOnly;
					break;
				case "ReadWrite":
					rights = UserRights.ReadWrite;
					break;
				case "ReadWriteAndUserManagement":
					rights = UserRights.ReadWriteAndUserManagement;
					break;
				default:
					rights = UserRights.None;
					break;
			}
		}
		return rights;
	}
}
