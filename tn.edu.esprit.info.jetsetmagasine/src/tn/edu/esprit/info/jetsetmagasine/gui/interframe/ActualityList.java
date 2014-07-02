package tn.edu.esprit.info.jetsetmagasine.gui.interframe;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.ActualityDao;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActualityList extends JInternalFrame {
	private JTextField textField_filtre;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActualityList frame = new ActualityList();
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
	public ActualityList() {
		setBounds(100, 100, 707, 496);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, BorderLayout.NORTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualityAdd actualityAdd = new ActualityAdd();
				actualityAdd.setVisible(true);
			}
		});
		toolBar.add(btnAdd);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblFiltre = new JLabel("Filtre :");
		panel_1.add(lblFiltre);
		
		textField_filtre = new JTextField();
		panel_1.add(textField_filtre);
		textField_filtre.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		
		panel_2.add(table, BorderLayout.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_2.add(table.getTableHeader(), BorderLayout.PAGE_START);
		refreshTable();

		getContentPane().add(panel_2, BorderLayout.CENTER);
		
	}
	
	public void refreshTable() {

		System.out.println("Refresh table");
		table.removeAll();

		List<Actuality> actualities = ActualityDao.getInstanceof().findAll();

		Object[][] objects = new Object[actualities.size()][6];
		int i = 0;

		for (Actuality actuality : actualities) {

			objects[i][0] = actuality.getId_auto();
			objects[i][1] = actuality.getTitre();
			objects[i][2] = actuality.getDate_redaction();
			objects[i][3] = actuality.getType();
			objects[i][4] = actuality.getCategory();
			objects[i][5] = actuality.isValide();
			i++;
		}
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"id", "Titre", "Date / Heure", "Type", "Categorie", "Valide"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

}
