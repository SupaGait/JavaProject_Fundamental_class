/**
 * 
 */
package fr.shazilgerard.findmypatient.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.datamodel.Patient;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Gerard
 *
 */
public class PatientAddView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientRoom;

	/**
	 * Create the frame.
	 * @param identityController 
	 */
	public PatientAddView(IdentityController controller) {
		
		setTitle("Add a patient");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelFields = new JPanel();
		contentPane.add(panelFields, BorderLayout.SOUTH);
		
		JButton btnAddPatient = new JButton("Add patient");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.getPatientManagement().add( new Patient(textFieldPatientName.getText(),textFieldPatientRoom.getText(),textFieldPatientRoom.getText(),textFieldPatientRoom.getText(),textFieldPatientRoom.getText(),textFieldPatientRoom.getText(),textFieldPatientRoom.getText(),textFieldPatientRoom.getText()));
				} catch (NoAuthorityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		panelFields.add(btnAddPatient);
		
		JPanel panelControl = new JPanel();
		contentPane.add(panelControl, BorderLayout.CENTER);
		GridBagLayout gbl_panelControl = new GridBagLayout();
		gbl_panelControl.columnWidths = new int[]{0, 0, 0};
		gbl_panelControl.rowHeights = new int[]{0, 0, 0};
		gbl_panelControl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelControl.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelControl.setLayout(gbl_panelControl);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panelControl.add(lblName, gbc_lblName);
		
		textFieldPatientName = new JTextField();
		GridBagConstraints gbc_textFieldPatientName = new GridBagConstraints();
		gbc_textFieldPatientName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPatientName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPatientName.gridx = 1;
		gbc_textFieldPatientName.gridy = 0;
		panelControl.add(textFieldPatientName, gbc_textFieldPatientName);
		textFieldPatientName.setColumns(10);
		
		JLabel lblRoom = new JLabel("Room");
		GridBagConstraints gbc_lblRoom = new GridBagConstraints();
		gbc_lblRoom.anchor = GridBagConstraints.EAST;
		gbc_lblRoom.insets = new Insets(0, 0, 0, 5);
		gbc_lblRoom.gridx = 0;
		gbc_lblRoom.gridy = 1;
		panelControl.add(lblRoom, gbc_lblRoom);
		
		textFieldPatientRoom = new JTextField();
		GridBagConstraints gbc_textFieldPatientRoom = new GridBagConstraints();
		gbc_textFieldPatientRoom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPatientRoom.gridx = 1;
		gbc_textFieldPatientRoom.gridy = 1;
		panelControl.add(textFieldPatientRoom, gbc_textFieldPatientRoom);
		textFieldPatientRoom.setColumns(10);
	}

}
