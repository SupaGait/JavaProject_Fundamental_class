/**
 * 
 */
package fr.shazilgerard.findmypatient.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.datamodel.Patient;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Gerard
 *
 */
public class PatientOverview extends JFrame implements ActionListener
{
	
	IdentityController controller; // TODO: Create interface?

	private JPanel contentPane;
	private JTable tablePatients;
	private JTextField textField;
	private JTextField txtPassword;
	
	private JButton btnListAllPatients;
	private PatientOverviewTableModel modelPatientOverview; 

	/**
	 * Create the frame.
	 * @param identityController 
	 */
	public PatientOverview(IdentityController identityController) 
	{
		
		this.controller = identityController;
		createComponents();
	}

	private void createComponents()
	{
		
		setTitle("Patient Overview");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		contentPane.add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panelLogin = new JPanel();
		panelHeader.add(panelLogin);
		GridBagLayout gbl_panelLogin = new GridBagLayout();
		gbl_panelLogin.columnWidths = new int[] {50, 100, 0};
		gbl_panelLogin.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panelLogin.rowWeights = new double[]{0.0, 0.0};
		panelLogin.setLayout(gbl_panelLogin);
		
		JLabel lblUserName = new JLabel("User name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.WEST;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 0;
		panelLogin.add(lblUserName, gbc_lblUserName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panelLogin.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.VERTICAL;
		gbc_btnLogin.gridheight = 2;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 0;
		panelLogin.add(btnLogin, gbc_btnLogin);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panelLogin.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.anchor = GridBagConstraints.WEST;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 1;
		panelLogin.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JPanel panelCommands = new JPanel();
		FlowLayout fl_panelCommands = (FlowLayout) panelCommands.getLayout();
		fl_panelCommands.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelCommands, BorderLayout.SOUTH);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setVerticalAlignment(SwingConstants.TOP);

		
		this.btnListAllPatients = new JButton("List all patients");
		btnListAllPatients.addActionListener(this);
		panelCommands.add(btnListAllPatients);
		panelCommands.add(btnSearch);
		
		this.modelPatientOverview = new PatientOverviewTableModel();
		
		JPanel panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new GridLayout(0, 1, 0, 0));
		tablePatients = new JTable(this.modelPatientOverview);
		tablePatients.setFillsViewportHeight(true);
		
		JScrollPane scrollPanePatients = new JScrollPane(tablePatients);
		panelData.add(scrollPanePatients);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(btnListAllPatients))
		{
			// Retrieve the pations
			java.util.List<Patient> allPatients = this.controller.getPatientManagement().readAll();
			
			//Update the table list
			modelPatientOverview.setPatients(allPatients);
			
		}
		
	}
}
