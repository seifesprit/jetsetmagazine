package tn.edu.esprit.info.jetsetmagasine.gui.interframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import tn.edu.esprit.info.jetsetmagasine.domain.Category;
import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.LeaderDao;
import javax.swing.JDialog;

public class LeaderList extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeaderList frame = new LeaderList();
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
	public LeaderList() {
		setClosable(true);
		setResizable(true);

		setMaximizable(true);
		setIconifiable(true);
		setTitle("User");
		setBounds(100, 100, 618, 358);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnAddUser = new JButton("Add");

		toolBar.add(btnAddUser);

		JButton btnUpdateUser = new JButton("Update");
		toolBar.add(btnUpdateUser);

		JButton btnDeleteUser = new JButton("Delete");
		
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LeaderDao.getInstanceof().remove(new Leader(Integer.parseInt(table.getValueAt(table.getSelectedRow(),0).toString()),
																table.getValueAt(table.getSelectedRow(),3).toString(), 
																null, 
																table.getValueAt(table.getSelectedRow(),1).toString(), 
																table.getValueAt(table.getSelectedRow(),2).toString(), 
																null));
					refreshTable();
				} catch (Exception ee) {

				}
			}
		});
		toolBar.add(btnDeleteUser);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		panel_1.add(table, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_1.add(table.getTableHeader(), BorderLayout.PAGE_START);
		refreshTable();

	}
	
	public void refreshTable(){
				table.removeAll();
				
				List<Leader> leaders = LeaderDao.getInstanceof().findAll();

				Object[][] objects = new Object[leaders.size()][5];
				int i = 0;

				for (Leader leader : leaders) {

					objects[i][0] = leader.getId_auto();
					objects[i][1] = leader.getNom_prenom();
					objects[i][2] = leader.getEmail();
					objects[i][3] = leader.getLogin();
					objects[i][4] = leader.getCategory().getLibelle();
					i++;
				}
				table.setModel(new DefaultTableModel(objects, new String[] { "id",
				"Nom Prenom", "Email", "Login", "Categorie" }));
	}
}
