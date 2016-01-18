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
	public void create(DataType data);
	public List<DataType> readAll();
	public List<DataType> search(DataType data, IMatcher<DataType> matcher);
	public boolean update(DataType data);
	public void delete(DataType data);
}
