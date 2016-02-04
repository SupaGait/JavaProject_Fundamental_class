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
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Gerard
 *
 */
public class PatientOverviewView extends JFrame implements ActionListener
{
	
	IdentityController identityController; // TODO: Create interface?

	private JPanel contentPane;
	private JTable tablePatients;
	private JTextField textField;
	private JTextField txtPassword;
	
	private JButton btnListAllPatients;
	private JButton btnAddNewPatient;
	private PatientOverviewTableModel modelPatientOverview; 
	
	private PatientAddView patientAddView;

	/**
	 * Create the frame.
	 * @param identityController 
	 */
	public PatientOverviewView(IdentityController identityController) 
	{
		this.identityController = identityController;
		
		createComponents();
		
		// For testing, hardcode the address of DB now
		identityController.setupDatabase("jdbc:derby://localhost:1527/PatientsDB;create=true", "root", "root");
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
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Login
			}
		});
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
		contentPane.add(panelCommands, BorderLayout.SOUTH);
		panelCommands.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelGet = new JPanel();
		panelCommands.add(panelGet);
				panelGet.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
				
				this.btnListAllPatients = new JButton("List all patients");
				panelGet.add(btnListAllPatients);
				
				JButton btnSearch = new JButton("Search");
				panelGet.add(btnSearch);
				btnListAllPatients.addActionListener(this);
		
		JPanel panelAdd = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelAdd.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCommands.add(panelAdd);
		
		this.btnAddNewPatient = new JButton("Add new patient");
		panelAdd.add(btnAddNewPatient);
		this.btnAddNewPatient.addActionListener(this);
		
		this.modelPatientOverview = new PatientOverviewTableModel();
		
		JPanel panelData = new JPanel();
		contentPane.add(panelData, BorderLayout.CENTER);
		panelData.setLayout(new GridLayout(0, 1, 0, 0));
		tablePatients = new JTable(this.modelPatientOverview);
		tablePatients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablePatients.rowAtPoint(e.getPoint());
		        if(row > 0)
		        {
					Patient patient = modelPatientOverview.getPatient(row);
					PatientEditView patientEditeView = new PatientEditView(identityController, patient, modelPatientOverview);
					patientEditeView.setVisible(true);
		        }
			}
		});
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
			java.util.List<Patient> allPatients = null;
			try {
				allPatients = this.identityController.getPatientManagement().readAll();
			} catch (NoAuthorityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Update the table list
			modelPatientOverview.setPatients(allPatients);
			
		}
		else if(source.equals(btnAddNewPatient))
		{
			if(patientAddView == null)
			{
				patientAddView = new PatientAddView(this.identityController);
			}
			patientAddView.setVisible(true);
		}
	}
}
