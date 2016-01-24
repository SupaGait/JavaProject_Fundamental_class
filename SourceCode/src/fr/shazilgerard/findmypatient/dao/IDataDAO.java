/**
 * 
 */
package fr.shazilgerard.findmypatient.dao;

import java.util.List;

import fr.shazilgerard.findmypatient.helpers.IMatcher;

/**
 * @author Gerard
 *
 */
public interface IDataDAO<DataType> {
	/**
	 * Add a new element to the DAO
	 * @param data the new element
	 */
	public void create(DataType data);
	/**
	 * Retrieve all elements in the DAO
	 * @return A list containing all elements
	 */
	public List<DataType> readAll();
	/**
	 * @param Data search for a specific field of data in the DAO
	 * @param Matcher defines which search method should be used
	 * @return A list of found elements
	 */
	public List<DataType> search(DataType data, IMatcher<DataType> matcher);
	/**
	 * Update a specific element in the DAO
	 * @param data Element to be updated
	 */
	public void update(DataType data);
	/**
	 * Delete a specific element in the DAO
	 * @param data element to be deleted
	 */
	public void delete(DataType data);
}
