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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import tn.edu.esprit.info.jetsetmagasine.domain.Category;
import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.LeaderDao;

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
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				refreshTable();
			}
		});
		setClosable(true);
		setResizable(true);

		setMaximizable(true);
		setIconifiable(true);
		setTitle("User");
		setBounds(100, 100, 618, 358);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnAddUser = new JButton("Add");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LeaderAdd.getinstance().setVisible(true);
			}
		});

		toolBar.add(btnAddUser);

		JButton btnUpdateUser = new JButton("Update");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount() == 0)
					return;
				LeaderAdd.getinstance().setVisible(true);
				LeaderAdd.getinstance().dataToComponent(
						new Leader(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()),
													table.getValueAt(table.getSelectedRow(), 3).toString(),
													table.getValueAt(table.getSelectedRow(), 4).toString(),
													table.getValueAt(table.getSelectedRow(),1).toString(),
													table.getValueAt(table.getSelectedRow(),2).toString(),
												(Category)table.getValueAt(table.getSelectedRow(), 5)));
			}
		});
		toolBar.add(btnUpdateUser);

		JButton btnDeleteUser = new JButton("Delete");

		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(table.getSelectedRowCount() == 0)
						return;
					LeaderDao.getInstanceof().remove(
							new Leader(Integer.parseInt(table.getValueAt(
									table.getSelectedRow(), 0).toString()),
									table.getValueAt(table.getSelectedRow(), 3)
											.toString(), null, table
											.getValueAt(table.getSelectedRow(),
													1).toString(), table
											.getValueAt(table.getSelectedRow(),
													2).toString(), null));
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

	public void refreshTable() {

		System.out.println("Refresh table");
		table.removeAll();

		List<Leader> leaders = LeaderDao.getInstanceof().findAll();

		Object[][] objects = new Object[leaders.size()][6];
		int i = 0;

		for (Leader leader : leaders) {

			objects[i][0] = leader.getId_auto();
			objects[i][1] = leader.getNom_prenom();
			objects[i][2] = leader.getEmail();
			objects[i][3] = leader.getLogin();
			objects[i][4] = leader.getPassword();
			objects[i][5] = leader.getCategory();
			i++;
		}
		table.setModel(new DefaultTableModel(
			objects,
			new String[] {
				"id", "Nom Prenom", "Email", "Login", "Password", "Categorie"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}
}
