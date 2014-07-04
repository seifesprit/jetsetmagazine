package tn.edu.esprit.info.jetsetmagasine.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
import tn.edu.esprit.info.jetsetmagasine.gui.interframe.ActualityList;
import tn.edu.esprit.info.jetsetmagasine.gui.interframe.CategoryList;
import tn.edu.esprit.info.jetsetmagasine.gui.interframe.LeaderList;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

public class PrincipaleTwo extends JFrame {

	private JPanel contentPane;
	private Leader leader;


	/**
	 * Create the frame.
	 */
	public PrincipaleTwo(Leader leader) {
		this.leader = leader;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 377);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);
		
		JMenuItem jmenu_category = new JMenuItem("Category");
		jmenu_category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CategoryList categoryList = new CategoryList();
				categoryList.setVisible(true);
				contentPane.add(categoryList);
			}
		});
		mnData.add(jmenu_category);
		
		JMenuItem jmenu_user = new JMenuItem("Leader");
		jmenu_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeaderList leaderList = new LeaderList();
				leaderList.show();leaderList.setVisible(true);
				contentPane.add(leaderList);
			}
		});
		mnData.add(jmenu_user);
		
		JMenuItem mntmActuality = new JMenuItem("Actuality");
		mntmActuality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualityList actualityList = new ActualityList();
				actualityList.setVisible(true);
				contentPane.add(actualityList);
			}
		});
		mnData.add(mntmActuality);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JLabel lblName = new JLabel("Name");
		toolBar.add(lblName);
		
		//loading info leader
		lblName.setText(leader.getNom_prenom());
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
