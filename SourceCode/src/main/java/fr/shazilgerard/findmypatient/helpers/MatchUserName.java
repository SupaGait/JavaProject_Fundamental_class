// The matcher username file
package fr.shazilgerard.findmypatient.helpers;
import fr.shazilgerard.findmypatient.datamodel.User;

public class MatchUserName implements IMatcher<User> {
	
	@Override
	public String getSQLMatchStatement(String tableName, User user) {
		return "select * from "+tableName+" where NAME='"+user.getName()+"'";
	}
	
	@Override
	public String toString() {
		return "user name";
	}
}
