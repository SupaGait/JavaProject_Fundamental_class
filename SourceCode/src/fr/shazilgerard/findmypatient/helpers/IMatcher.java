/**
 * 
 */
package fr.shazilgerard.findmypatient.helpers;

/**
 * @author Gerard
 *
 */
public interface IMatcher<DataType> {

	/**
	 * @return The SQL match statement to be executed.
	 */
	public String getSQLMatchStatement(String tableName, DataType data);
}
