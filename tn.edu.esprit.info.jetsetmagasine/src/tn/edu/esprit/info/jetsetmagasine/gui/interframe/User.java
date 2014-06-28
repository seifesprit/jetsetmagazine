package tn.edu.esprit.info.jetsetmagasine.gui.interframe;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class User extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public User() {
		setClosable(true);
		setResizable(true);

		setMaximizable(true);
		setIconifiable(true);
		setTitle("User");
		setBounds(100, 100, 450, 300);
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnAddUser = new JButton("Add user");
		toolBar.add(btnAddUser);
		
		JButton btnUpdateUser = new JButton("Update user");
		toolBar.add(btnUpdateUser);
		
		JButton btnDeleteUser = new JButton("Delete user");
		toolBar.add(btnDeleteUser);

	}

}
