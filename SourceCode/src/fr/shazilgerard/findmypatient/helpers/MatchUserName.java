/**
 * 
 */
package fr.shazilgerard.findmypatient.helpers;
import fr.shazilgerard.findmypatient.datamodel.User;

/**
 * @author Gerard
 *
 */
public class MatchUserName implements IMatcher<User> {
	
	@Override
	public String getSQLMatchStatement(String tableName, User user) {
		return "select * from "+tableName+" where NAME='"+user.getName()+"'";
	}
}
