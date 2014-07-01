package tn.edu.esprit.info.jetsetmagasine.gui.interframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

import tn.edu.esprit.info.jetsetmagasine.domain.Category;
import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.CategoryDao;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.LeaderDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class LeaderAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_name;
	private JTextField textField_login;
	private JPasswordField passwordField;
	private JTextField textField_email;
	private JComboBox comboBox;
	private boolean isUpadte = false;
	private int id_auto = 0;
	private static LeaderAdd dialog;

	/**
	 * Create the dialog.
	 */
	private LeaderAdd() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				
				Category category = (Category) comboBox.getSelectedItem();
				
				comboBox.removeAllItems();
				List<Category> categories = CategoryDao.getInstanceof().findAll();
				for(Category category2 : categories){
					comboBox.addItem(category2);
				}
				
				if(category != null){
					comboBox.setSelectedItem(category);
				}
			}
		});
		setType(Type.UTILITY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		dialog = this;
		setResizable(false);
		setTitle("Add Leader");
		setBounds(100, 100, 625, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Name : ");
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Login :");
		textField_login = new JTextField();
		textField_login.setColumns(10);
		JLabel lblPassword = new JLabel("Password :");
		
		passwordField = new JPasswordField();
		
		JLabel lblEmail = new JLabel("Email :");
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		
		comboBox = new JComboBox();
		
		JLabel lblCategory = new JLabel("Category :");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblEmail)
							.addGap(26))
						.addComponent(lblPassword)
						.addComponent(lblCategory))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.LEADING)
						.addComponent(textField_email, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(textField_login, Alignment.LEADING)
						.addComponent(textField_name, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
					.addContainerGap(275, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategory))
					.addContainerGap(179, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Category category = (Category) comboBox.getSelectedItem();
						//add leader
						
						//System.out.println(category.getId_auto());
						if(isUpadte){
						LeaderDao.getInstanceof().update(new Leader(id_auto, textField_login.getText(), passwordField.getText(), textField_name.getText(), textField_email.getText(), category));
						
						}else{
							LeaderDao.getInstanceof().add(new Leader(id_auto, textField_login.getText(), passwordField.getText(), textField_name.getText(), textField_email.getText(), category));
						}
						dialog.dispose();
						dialog = null;
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
						dialog=null;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void dataToComponent(Leader leader){
		textField_name.setText(leader.getNom_prenom());
		textField_login.setText(leader.getLogin());
		textField_email.setText(leader.getEmail());
		passwordField.setText(leader.getPassword());
		comboBox.setSelectedItem(leader.getCategory());
		id_auto = leader.getId_auto();
		isUpadte = true;
	}
	
	/**
	 * Get instance LeaderAdd
	 */
	public static LeaderAdd getinstance(){
		if(dialog == null)
			dialog = new LeaderAdd();
		
		return dialog;
	}
}
