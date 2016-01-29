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
public class PatientEditView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldRoom;
	/**
	 * Create the frame.
	 */
	public PatientEditView(IdentityController controller, Patient patient) {
		setTitle("Modifiy patient details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label = new JLabel("Name");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_1.add(label, gbc_label);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setText(patient.getName());
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		panel_1.add(textFieldName, gbc_textFieldName);
		
		JLabel label_1 = new JLabel("Room");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panel_1.add(label_1, gbc_label_1);
		
		textFieldRoom = new JTextField();
		textFieldRoom.setColumns(10);
		textFieldRoom.setText(patient.getRoom());
		GridBagConstraints gbc_textFieldRoom = new GridBagConstraints();
		gbc_textFieldRoom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRoom.gridx = 1;
		gbc_textFieldRoom.gridy = 1;
		panel_1.add(textFieldRoom, gbc_textFieldRoom);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Retrieve the values
				patient.setName(textFieldName.getText());
				patient.setRoom(textFieldRoom.getText());
				
				// Modify the patient
				controller.getPatientManagement().modify(patient);
				dispose();
			}
		});
		panel.add(btnModify);
	}

}
