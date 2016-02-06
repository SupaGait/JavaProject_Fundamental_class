/**
 * 
 */
package fr.shazilgerard.findmypatient.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.helpers.IMatcher;
import fr.shazilgerard.findmypatient.helpers.MatchPatientDisplayName;
import fr.shazilgerard.findmypatient.helpers.MatchPatientfrontName;

/**
 *
 */
public class PatientSearchComboBoxModel implements ComboBoxModel  {

	private List< IMatcher<Patient> > searchMethods = new ArrayList< IMatcher<Patient>>();
	private IMatcher<Patient> selectedMatcher = null;
	public PatientSearchComboBoxModel()
	{
		searchMethods.add(new MatchPatientfrontName());
		searchMethods.add(new MatchPatientDisplayName());
		
		selectedMatcher = searchMethods.get(0);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return searchMethods.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return searchMethods.get(index);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#addListDataListener(javax.swing.event.ListDataListener)
	 */
	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#removeListDataListener(javax.swing.event.ListDataListener)
	 */
	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	@Override
	public void setSelectedItem(Object anItem) {
		this.selectedMatcher = (IMatcher<Patient>) anItem;
	}

	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	@Override
	public Object getSelectedItem() {
		return this.selectedMatcher;
	}
}
