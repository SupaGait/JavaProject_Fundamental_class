package fr.shazilgerard.findmypatient.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.dao.exceptions.DaoLoadObjectException;
import fr.shazilgerard.findmypatient.datamodel.exceptions.NoAuthorityException;
import sun.misc.BASE64Decoder;

import java.io.*;


public class loginview
{
	private  JFrame obj;
	private  JTextField loginField;
	private  JTextField passField;
	
	private  JLabel username;
	private  JLabel password;
	private  JTextArea pta;
	
	private  JButton log_in;
	private  JButton cancel;
	
	private  JPanel panel;
	private  JPanel panel1;
	private  JPanel panel2;
	
	private final IdentityController identityController;
    
	public loginview(final IdentityController identityController)
	{
		this.identityController = identityController;
		
	    
		
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    obj = new JFrame("Find  My Patient");
		

		JPanel panel  = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
   
    	
    	
    	JLabel label = new JLabel(new ImageIcon("./images/hospital.jpg"));
        panel.add(label);
		obj.add(panel,BorderLayout.NORTH);
	
		
		
		panel1.setLayout(new GridLayout(3,2));
		username = new JLabel(" **** Username **** :");
		password = new JLabel(" **** Password **** :");
		loginField = new JTextField("");
		passField = new JPasswordField("");
		panel1.add(username);
		panel1.add(loginField);
		panel1.add(password);
		panel1.add(passField);
		log_in = new JButton("Login");
		cancel = new JButton("Cancel");
		panel1.add(log_in);
		panel1.add(cancel);
		obj.add(panel1,BorderLayout.CENTER);
		
	
	
		
		
		log_in.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			try {
				final String stringName = loginField.getText();
				System.out.println(stringName);
				final String stringPass = passField.getText();
				
				System.out.println(stringPass);
				identityController.getUserAuthority().login(stringPass, stringPass);
			} catch (NoAuthorityException | DaoLoadObjectException e1) {
				
				e1.printStackTrace();
			}
				
				
			}		
		});
		
		
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent sig)
			{
				
				System.exit(0);
				
		
			}});

	
	obj.setBounds(100, 100, 550, 300);
	obj.setResizable(false);
	obj.setVisible(true);			
	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}}