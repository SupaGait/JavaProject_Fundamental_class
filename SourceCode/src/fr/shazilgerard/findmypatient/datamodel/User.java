/**
 * 
 */
package fr.shazilgerard.findmypatient.datamodel;

/**
 * @author Gerard
 *
 */
public class User {
	
	// TODO : Implement correct user class
	private String name;
	private String password;
	private String id;

	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
		this.id = "New";
	}
	public User(String name, String password, String id)
	{
		this.name = name;
		this.password = password;
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

}
