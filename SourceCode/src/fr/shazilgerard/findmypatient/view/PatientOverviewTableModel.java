/**
 * 
 */
package fr.shazilgerard.findmypatient.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.shazilgerard.findmypatient.datamodel.Patient;

/**
 * @author Gerard
 *
 */
public class PatientOverviewTableModel extends AbstractTableModel {

	private List<Patient> patients;
	
	PatientOverviewTableModel()
	{
		this.patients = new ArrayList<Patient>();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return this.patients.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 5;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(rowIndex < this.patients.size())
		{
			switch(columnIndex)
			{
				case 0: value = this.patients.get(rowIndex).getName(); break;
				case 1: value = this.patients.get(rowIndex).getRoom(); break;
				case 2: value = this.patients.get(rowIndex).getId(); break;
			}			
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "Name";
                break;
            case 1:
                name = "Room";
                break;
            case 2:
                name = "ID";
                break;
            case 3:
                name = "Patient info 3";
                break;
            case 4:
                name = "Patient info 4";
                break;
        }
        return name;
	}

	/**
	 * @param allPatients list of patients which will be set as table data
	 */
	public void setPatients(List<Patient> allPatients) {
		// Add data and update table
		this.patients = allPatients;
		this.fireTableDataChanged();
	}
}
