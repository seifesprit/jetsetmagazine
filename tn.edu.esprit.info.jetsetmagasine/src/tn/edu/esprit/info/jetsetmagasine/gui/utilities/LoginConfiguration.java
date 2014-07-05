package tn.edu.esprit.info.jetsetmagasine.gui.utilities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Window.Type;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.border.LineBorder;

import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
import tn.edu.esprit.info.jetsetmagasine.gui.Principale;
import tn.edu.esprit.info.jetsetmagasine.gui.PrincipaleTwo;
import tn.edu.esprit.info.jetsetmagasine.services.business.impl.LeaderBusiness;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginConfiguration extends JFrame {

	private JPanel contentPane;
	private JTextField textField_login;
	private JTextField textField_password;
	private Principale parent;
	private static LoginConfiguration instancesof;
	/**
	 * Launch the application.
	 */
	public static LoginConfiguration getinstanceof(Principale parent){
		
		if(instancesof == null) instancesof = new LoginConfiguration(parent);
		return instancesof;
	}

	/**
	 * Create the frame.
	 */
	private LoginConfiguration(Principale parent2) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				textField_login.setText("");
				textField_password.setText("");
			}
		});
		this.parent = parent2;
		setResizable(false);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 347, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblBackofficejetsetmagasine = new JLabel("BackOffice 'Crystalium Magasine'");
		lblBackofficejetsetmagasine.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(lblBackofficejetsetmagasine);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JLabel lblLogin = new JLabel("Login :");
		
		JLabel lblPassword = new JLabel("Password : ");
		
		textField_login = new JTextField();
		textField_login.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Leader leader = LeaderBusiness.getIntanceof().verifLoginPassword(textField_login.getText(), textField_password.getText());
				if( leader != null){
					PrincipaleTwo principaleTwo = new PrincipaleTwo(leader);
					principaleTwo.setVisible(true);
					dispose();
				}else{
					textField_login.setText("");
					textField_password.setText("");
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(162)
							.addComponent(btnConnect)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(33)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblLogin)
									.addGap(34))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblPassword)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_password)
								.addComponent(textField_login, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnConnect))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Magasine By Crystalium 2014");
		panel_2.add(lblNewLabel);
	}
}
